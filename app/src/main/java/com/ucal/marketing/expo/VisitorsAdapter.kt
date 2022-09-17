package com.ucal.marketing.expo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VisitorsAdapter(private val visitorsList: List<Visitor>) :  RecyclerView.Adapter<VisitorsAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
            parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = visitorsList[position]
        holder.visitorName.text = currentItem.fullName
        holder.visitorCompany.text = currentItem.companyName
    }

    override fun getItemCount(): Int {
        return visitorsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val visitorName : TextView = itemView.findViewById(R.id.visitor_name)
        val visitorCompany : TextView = itemView.findViewById(R.id.visitor_company)

    }

}