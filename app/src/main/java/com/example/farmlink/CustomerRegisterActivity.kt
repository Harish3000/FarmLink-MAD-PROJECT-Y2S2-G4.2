package com.example.farmlink

import android.os.Bundle
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


            // Write a message to the database
            // Write a message to the database
            var database = FirebaseDatabase.getInstance().getReference("Customer")

            myRef.setValue("Hello, World!")

        }
    }
}