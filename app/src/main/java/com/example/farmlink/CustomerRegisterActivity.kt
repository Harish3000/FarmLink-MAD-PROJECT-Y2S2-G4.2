package com.example.farmlink

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farmlink.databinding.ActivityCustomerRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerRegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_register)



            var firebase :DatabaseReference =FirebaseDatabase.getInstance().getReference("customer")
//            val customer = customer(name,email,username,password)
//            firebase.child(username).setValue(customer).addOnSuccessListener {
//
//                binding.Name.text.clear()
//                binding.email.text.clear()
//                binding.username.text.clear()
//                binding.Password.text.clear()
//
//                Toast.makeText(this,"Sucessfully saved",Toast.LENGTH_SHORT).show()
//            }.addOnFailureListener{
//                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
//            }

        }
    }

    private fun customer(name: String, email: String, username: String, password: String): Any? {
        TODO("Not yet implemented")
    }
}

private fun CharSequence.clear() {
    TODO("Not yet implemented")
}
