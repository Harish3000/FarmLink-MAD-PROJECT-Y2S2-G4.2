package com.example.farmlink

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class itmAdapter(private val itmlst:ArrayList<itemDs>):RecyclerView.Adapter<itmAdapter.itmHolder>(){
    private  lateinit var mListner:onItemClickListener
    interface onItemClickListener{
         fun onItemClick(position: Int)
     }
  fun setOnItemClickListner(listener: onItemClickListener){
      mListner=listener
  }


    class itmHolder(itmView: View,listner:onItemClickListener ):RecyclerView.ViewHolder(itmView){
        val itmname:TextView=itmView.findViewById(R.id.tv_name)
        val itmprice:TextView=itmView.findViewById(R.id.tv_price)
        val itmDescription:TextView =itmView.findViewById(R.id.tv_des)
        val itmimg:ImageView=itmView.findViewById(R.id.imageView2)

        init{
            itmView.setOnClickListener(){
                listner.onItemClick(adapterPosition)
            }
    }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itmHolder {
      val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return itmHolder(itemView,mListner)


    }

    override fun getItemCount(): Int {
     return itmlst.size
    }

    override fun onBindViewHolder(holder: itmHolder, position: Int) {
       val curretItem=itmlst[position]
        holder.itmname.text=curretItem.itemName.toString()
        holder.itmprice.text=curretItem.itemPrice.toString()
        holder.itmDescription.text=curretItem.description.toString()
        val bytes=android.util.Base64.decode(curretItem.itemImg,android.util.Base64.DEFAULT)
        val bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.itmimg.setImageBitmap(bitmap)

    }
}