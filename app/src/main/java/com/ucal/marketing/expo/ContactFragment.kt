package com.ucal.marketing.expo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ucal.marketing.expo.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentContactBinding.inflate(inflater,container, false)

        //Reset the form data


        // Inflate the layout for this fragment
        return binding.root
    }

}