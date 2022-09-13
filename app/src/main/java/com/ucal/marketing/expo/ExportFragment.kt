package com.ucal.marketing.expo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.DocumentsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.ucal.marketing.expo.databinding.FragmentExportBinding
import kotlinx.coroutines.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


@Suppress("DEPRECATION")
class ExportFragment : Fragment() {

    private lateinit var binding: FragmentExportBinding
    private lateinit var appDb: AppDatabase
    private var visitors: MutableList<Any> = mutableListOf()
    private val fileUri: Uri = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toUri()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Database
        appDb = context?.let { AppDatabase.getDatabase(it) }!!

        getEntriesCount()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExportBinding.inflate(inflater,container,false)

        //Export button
        binding.btnExportToCSV.setOnClickListener {
            exportToCsv()
            Toast.makeText(context,getString(R.string.csv_export_successful), Toast.LENGTH_LONG).show()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        if (requestCode == CREATE_FILE && resultCode == Activity.RESULT_OK) {
            if (resultData != null){
                resultData.data?.also { uri ->
                    writeFileContent(uri)
                }
                Toast.makeText(context, "CSV File Created Successful", Toast.LENGTH_SHORT).show()
            }
        }

    }


    //Custom Methods
    @OptIn(DelicateCoroutinesApi::class)
    private fun getEntriesCount() {

        var visitors: Int
        GlobalScope.launch {
            visitors = appDb.visitorDao().getRowCount()
            displayData(visitors)
        }

    }

    private suspend fun displayData(visitors: Int) {
        withContext(Dispatchers.Main){
            binding.statusEntriesId.text = visitors.toString()
        }
    }

        @OptIn(DelicateCoroutinesApi::class)
        private fun exportToCsv() {
                GlobalScope.launch(Dispatchers.IO){
                val temp = appDb.visitorDao().getAll()
                val header = listOf("Id", "Full Name", "Designation", "Company Name", "Visitor Category", "Phone", "Email", "Products Interested", "Rating", "Time and Date"+'\n' )
                visitors.add(header.joinToString())
                for (Visitor in temp){
                    val tempVisitor = listOf(
                        Visitor.id,
                        Visitor.fullName,
                        Visitor.designation,
                        Visitor.companyName,
                        Visitor.visitorCategory,
                        Visitor.phoneNumber,
                        Visitor.email,
                        Visitor.productInterest,
                        Visitor.rating,
                        Visitor.timeStamp+'\n',
                    )
                    visitors.add(tempVisitor.joinToString())
                }
            }
            createFile(fileUri)
        }

        private fun createFile(pickerInitialUri: Uri) {
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/csv"
                putExtra(Intent.EXTRA_TITLE, "visitors.csv")
                putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
            }
            startActivityForResult(intent, CREATE_FILE)
        }

        private fun writeFileContent(uri: Uri) {
            try {
                val pfd: ParcelFileDescriptor? = context?.contentResolver?.openFileDescriptor(uri,"w")
                val fileOutputStream = FileOutputStream(pfd!!.fileDescriptor)
                val textContent: String = visitors.joinToString("")
                fileOutputStream.write(textContent.toByteArray())
                fileOutputStream.close()
                pfd.close()
            } catch (e: IOException){
                e.printStackTrace()
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }
        }

        companion object{
            const val CREATE_FILE = 1
        }
    }