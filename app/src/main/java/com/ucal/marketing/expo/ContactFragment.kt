package com.ucal.marketing.expo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ucal.marketing.expo.databinding.FragmentContactBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding
    private var ratingGroup: RadioGroup? = null
    private lateinit var appDb: AppDatabase


    override fun onResume() {
        super.onResume()
        //Visitor Spinner
        val visitorType = resources.getStringArray(R.array.visitor_type)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.visitor_type_item, visitorType)
        binding.textVisitorTypeField.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate the layout for this fragment
        binding = FragmentContactBinding.inflate(inflater,container, false)

        // Initialize Database
        appDb = context?.let { AppDatabase.getDatabase(it) }!!

        //Save the form data
        binding.visitorFormSubmitButton.setOnClickListener{
            writeData()
        }

        //Reset the form data
        binding.visitorFormResetButton.setOnClickListener {
            resetData()
            Toast.makeText(context,"Form Reset",Toast.LENGTH_SHORT).show()
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun resetData() {
        binding.textNameField.text?.clear()
        binding.textDesignationField.text?.clear()
        binding.textCompanyField.text?.clear()
        binding.textVisitorTypeField.text?.clear()
        binding.textPhoneField.text?.clear()
        binding.textEmailField.text?.clear()

        binding.productCarburettors.isChecked=false
        binding.productOilPumps.isChecked=false
        binding.productVacuumPumps.isChecked=false
        binding.productFuelRails.isChecked=false
        binding.productECoolantPump.isChecked=false
        binding.productEVacuumPump.isChecked=false
        binding.productDosingSystem.isChecked=false
        binding.productElectronics.isChecked=false
        binding.productOther.isChecked=false
        binding.textProductOtherSpecifyInputField.text?.clear()

        binding.radioGroupRating.clearCheck()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun writeData(){

        val name = binding.textNameField.text.toString()
        val designation = binding.textDesignationField.text.toString()
        val company = binding.textCompanyField.text.toString()
        val visitorType = binding.textVisitorTypeField.text.toString()
        val phone = binding.textPhoneField.text.toString()
        val email = binding.textEmailField.text.toString()

        //Product
        var products = mutableListOf<String>()
        val productCarburettors =binding.productCarburettors
        if (productCarburettors.isChecked){ products = addElement(products, binding.productCarburettors.text.toString())}
        val productOilPumps = binding.productOilPumps
        if (productOilPumps.isChecked){addElement(products, binding.productOilPumps.text.toString())}
        val productVacuumPumps = binding.productVacuumPumps
        if (productVacuumPumps.isChecked){addElement(products, binding.productVacuumPumps.text.toString())}
        val productFuelRail = binding.productFuelRails
        if (productFuelRail.isChecked){addElement(products, binding.productFuelRails.text.toString())}
        val producteCoolantPump = binding.productECoolantPump
        if (producteCoolantPump.isChecked){addElement(products, binding.productECoolantPump.text.toString())}
        val producteVacuumPump = binding.productEVacuumPump
        if (producteVacuumPump.isChecked){addElement(products, binding.productEVacuumPump.text.toString())}
        val productDosingSystem = binding.productDosingSystem
        if (productDosingSystem.isChecked){addElement(products, binding.productDosingSystem.text.toString())}
        val productElectronics = binding.productElectronics
        if (productElectronics.isChecked){addElement(products, binding.productElectronics.text.toString())}
        val productOther = binding.productOther
        val productOtherSpecify = binding.textProductOtherSpecifyInputField.text.toString()
        if (productOther.isChecked){addElement(products, productOtherSpecify)}
        val separator = ";"
        val productsString = products.joinToString(separator)

        //Rating
        ratingGroup = binding.radioGroupRating
        var rating: String? = null
        when(ratingGroup!!.checkedRadioButtonId){
            R.id.radioRatingOne -> {
                rating = binding.radioRatingOne.text.toString()
            }
            R.id.radioRatingTwo -> {
                rating = binding.radioRatingTwo.text.toString()
            }
            R.id.radioRatingThree -> {
                rating = binding.radioRatingThree.text.toString()
            }
            R.id.radioRatingFour -> {
                rating = binding.radioRatingFour.text.toString()
            }
            R.id.radioRatingFive -> {
                rating = binding.radioRatingFive.text.toString()
            }
            R.id.radioRatingSix -> {
                rating = binding.radioRatingSix.text.toString()
            }
            R.id.radioRatingSeven -> {
                rating = binding.radioRatingSeven.text.toString()
            }
            R.id.radioRatingEight -> {
                rating = binding.radioRatingEight.text.toString()
            }
            R.id.radioRatingNine -> {
                rating = binding.radioRatingNine.text.toString()
            }
            R.id.radioRatingTen -> {
                rating = binding.radioRatingTen.text.toString()
            }
        }

        if(name.isEmpty()){
            Toast.makeText(context, "Name can not be Blank",Toast.LENGTH_SHORT).show()
        }
        else if(designation.isEmpty()){
            Toast.makeText(context, "Designation can not be Blank",Toast.LENGTH_SHORT).show()
        }
        else if(company.isEmpty()){
            Toast.makeText(context, "Company can not be Blank",Toast.LENGTH_SHORT).show()
        }
        else if(visitorType.isEmpty()){
            Toast.makeText(context, "Visitor Type has to be selected",Toast.LENGTH_SHORT).show()
        }
        else if(phone.isEmpty()){
            Toast.makeText(context, "Phone can not be Blank",Toast.LENGTH_SHORT).show()
        }
        else if(email.isEmpty()){
            Toast.makeText(context, "Email can not be Blank",Toast.LENGTH_SHORT).show()
        }
        else if(productsString.isEmpty()){
            Toast.makeText(context, "Products has to be checked",Toast.LENGTH_SHORT).show()
        }
        else if(rating.toString().isEmpty()){
            Toast.makeText(context, "Rating can not be Blank",Toast.LENGTH_SHORT).show()
        }
        else {
            val visitor = Visitor(
                null,name,designation,company,visitorType,phone,email,productsString, rating?.toInt(), timeDate()
            )
            GlobalScope.launch(Dispatchers.IO){
                appDb.visitorDao().insert(visitor)
            }
            resetData()
            Toast.makeText(context,"Visitor data has been successfully saved",Toast.LENGTH_LONG).show()
        }

    }

    private fun addElement(products: MutableList<String>, element: String): MutableList<String> {
        products.add(element)
        return products
    }

    private fun timeDate(): String {
        val simpleDate = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.ENGLISH)
        return simpleDate.format(Date())
    }

}