package com.example.farmlink

//import android.app.Instrumentation.ActivityResult
import androidx.activity.result.ActivityResult


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.activity.result.ActivityResultLauncher

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

import com.example.farmlink.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import java.io.ByteArrayOutputStream
import android.util.Base64
import android.util.EventLogTags.Description
import com.google.firebase.database.FirebaseDatabase

import java.util.Calendar


class HomeFragment : Fragment() {
     private lateinit var binding: FragmentHomeBinding
     private lateinit var db:DatabaseReference
     var nodeId=""
     var sImage:String?=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
         nodeId=it.getString("itm_id").toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        val root:View=binding.root
        binding.button3.setOnClickListener(){
            var myfileintent=Intent(Intent.ACTION_GET_CONTENT )
            myfileintent.setType("image/*")

            ActivityResultLauncher.launch(myfileintent)
        }
         binding.button4.setOnClickListener(){
             add_Data()
         }
        binding.button5.setOnClickListener(){
            val fragment=ListFragment()
            val fragmentManager=activity?.supportFragmentManager
            val fragmentTrasaction=fragmentManager!!.beginTransaction()
            fragmentTrasaction.replace(R.id.frameLa,fragment).addToBackStack(HomeFragment().toString()).commit()


          }
        if(nodeId!=""){
            disply_data()
        }
        binding.button8.setOnClickListener(){
            update_data()
        }
        binding.button9.setOnClickListener(){
            delete_data()
        }
        return root
    }

    private fun delete_data() {
        db=FirebaseDatabase.getInstance().getReference("items")
        db.child(nodeId).removeValue().addOnSuccessListener {
            binding.etName.text.clear()
            binding.etPrice.text.clear()
            binding.etDes.text.clear()
            sImage=""
            binding.imageView.setImageBitmap(null)

            binding.button8.visibility=View.INVISIBLE
            binding.button9.visibility=View.INVISIBLE
            binding.button4.visibility=View.VISIBLE

            Toast.makeText(context,"item Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener() {
            Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun update_data() {
       val itemName=binding.etName.text.toString()
        val itemPrice=binding.etPrice.text.toString()
        val description=binding.etDes.text.toString()
        val dtclass=Dtclass()

        db=FirebaseDatabase.getInstance().getReference("items")
        val item=itemDs(itemName,itemPrice,sImage,description)

        db.child(nodeId).setValue(item).addOnSuccessListener {
            binding.etName.text.clear()
            binding.etPrice.text.clear()
            binding.etDes.text.clear()
            binding.imageView.setImageBitmap(null)
            sImage=""
            binding.button8.visibility=View.INVISIBLE
            binding.button9.visibility=View.INVISIBLE
            binding.button4.visibility=View.VISIBLE
            Toast.makeText(context,"data Updated",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(context,"not inserted",Toast.LENGTH_SHORT).show()

        }



    }

    private fun disply_data() {
        db=FirebaseDatabase.getInstance().getReference("items")
        db.child(nodeId).get().addOnSuccessListener {
            if(it.exists()){
                val dtclass=Dtclass()
                val itemDs=itemDs()
                binding.etName.setText(it.child("itemName").value.toString())
                binding.etPrice.setText(it.child("itemPrice").value.toString())
                binding.etDes.setText(it.child("description").value.toString())

                sImage=it.child("itemImg").value.toString()
                val bytes=Base64.decode(sImage,Base64.DEFAULT)
                val bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.size)

                binding.imageView.setImageBitmap(bitmap)
                binding.button8.visibility=View.VISIBLE
                binding.button9.visibility=View.VISIBLE
                binding.button4.visibility=View.INVISIBLE

            }
        }

    }

    private fun add_Data() {
        val itemName=binding.etName.text.toString()
        val itemPrice=binding.etPrice.text.toString()
        val description=binding.etDes.text.toString()
      db=FirebaseDatabase.getInstance().getReference("items")
        val item=itemDs(itemName,itemPrice, sImage,description)
        val databaseReference=FirebaseDatabase.getInstance().reference
        val id=databaseReference.push().key
        db.child(id.toString()).setValue(item).addOnSuccessListener {
            binding.etName.text.clear()
            binding.etPrice.text.clear()
            sImage=""
            binding.etDes.text.clear()
            binding.imageView.setImageBitmap(null)
            Toast.makeText(context,"Data Inserted",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private val ActivityResultLauncher=registerForActivityResult<Intent,ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult->
        if(result.resultCode==AppCompatActivity.RESULT_OK){
            val uri =result.data!!.data
            try{
                val inputStream=context?.contentResolver?.openInputStream(uri!!)
                val myBitmap=BitmapFactory.decodeStream(inputStream)
                val stream= ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes=stream.toByteArray()
                sImage=Base64.encodeToString(bytes,Base64.DEFAULT)
                binding.imageView.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(context,"Image Selected",Toast.LENGTH_SHORT).show()
            }catch (ex:Exception){
                Toast.makeText(context,ex.message.toString(),Toast.LENGTH_LONG).show()
            }
        }

    }

}