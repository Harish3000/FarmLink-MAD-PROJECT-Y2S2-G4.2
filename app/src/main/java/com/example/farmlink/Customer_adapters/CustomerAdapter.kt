package com.example.farmlink.Customer_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmlink.R
import com.example.farmlink.Customer_models.CustomerModel



class CustomerAdapter (private val cusList:ArrayList<CustomerModel>)
    :RecyclerView.Adapter<CustomerAdapter.ViewHolder>(){

    private lateinit var mListener: onProfileClickListener

    interface onProfileClickListener{
        fun onProfileClick(position: Int)
    }

    fun setOnProfileClickListener(clickListener: onProfileClickListener){
        mListener=clickListener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //inflating the layout
        val cusView =LayoutInflater.from(parent.context).inflate(R.layout.cus_list_item,parent,false)
        return ViewHolder(cusView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCus =cusList[position]
        holder.tvCusName.text="Hello,"+currentCus.cusName//name display
    }

    override fun getItemCount(): Int {
        return cusList.size
    }

    class ViewHolder(cusView: View,clickListener: onProfileClickListener) :RecyclerView.ViewHolder(cusView){
        val tvCusName :TextView=cusView.findViewById(R.id.tvCusName)
        init{
            cusView.setOnClickListener{
                clickListener.onProfileClick(adapterPosition)
            }
        }

    }


}