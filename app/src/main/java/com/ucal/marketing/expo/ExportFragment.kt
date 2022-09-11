package com.ucal.marketing.expo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ucal.marketing.expo.databinding.FragmentExportBinding
import kotlinx.coroutines.*


class ExportFragment : Fragment() {

    private lateinit var binding: FragmentExportBinding
    private lateinit var appDb: AppDatabase

    //

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
            Toast.makeText(context,"CSV Export Successful", Toast.LENGTH_LONG).show()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    //Custom Methods
    @OptIn(DelicateCoroutinesApi::class)
    private fun getEntriesCount() {

        var visitors: Int
        GlobalScope.launch {
            visitors = appDb.studentDao().getRowCount()
            displayData(visitors)
        }

    }

    private suspend fun displayData(visitors: Int) {
        withContext(Dispatchers.Main){
            binding.statusEntriesId.text = visitors.toString()
        }
    }

}