package com.example.farmlink.Cart_Delivery_Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmlink.Cart_Delivery_Modals.AddressData
import com.example.farmlink.R

class AddressAdapter (private val addressList: ArrayList<AddressData>) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.address_list_item,parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAddress = addressList[position]
        holder.tvAddressName.text = currentAddress.fullName //change here to view name
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAddressName : TextView = itemView.findViewById(R.id.tvAddressName)
    }

}