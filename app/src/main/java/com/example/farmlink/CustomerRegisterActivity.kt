package com.example.farmlink

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farmlink.databinding.ActivityCustomerRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerRegisterActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityCustomerRegisterBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Signup.setOnClickListener{
            val name =binding.Name.text.toString()
            val email=binding.email.text.toString()
            val username =binding.username.text.toString()
            val password =binding.Password.text.toString()


            var database  =FirebaseDatabase.getInstance().getReference("Customer")
            val customer = customer(name,email,username,password)
            database.child(username).setValue(customer).addOnSuccessListener {

                binding.Name.text.clear()
                binding.email.text.clear()
                binding.username.text.clear()
                binding.Password.text.clear()

                Toast.makeText(this,"Sucessfully saved",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }
    }




    private fun customer(name: String, email: String, username: String, password: String): Any? {
        TODO("Not yet implemented")
    }
}

private fun Any.clear() {
    TODO("Not yet implemented")
}
