package com.ucal.marketing.expo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucal.marketing.expo.databinding.FragmentVisitorsBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VisitorsFragment : Fragment() {

    private lateinit var binding: FragmentVisitorsBinding
    private lateinit var appDb: AppDatabase

    private lateinit var adapter : VisitorsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var visitorsList: List<Visitor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize Database
        appDb = context?.let { AppDatabase.getDatabase(it) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisitorsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = VisitorsAdapter(visitorsList)
        recyclerView.adapter = adapter
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun dataInitialize() {

        GlobalScope.launch(Dispatchers.IO){
            visitorsList = appDb.visitorDao().getAll()
            println(visitorsList)
            print("Testing Function")
        }
    }

}