package com.example.farmlink.Customer_activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.farmlink.Customer_models.CustomerModel
import com.example.farmlink.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerRegister : AppCompatActivity() {

    private lateinit var etCusName :EditText
    private lateinit var etCusEmail :EditText
    private lateinit var etCusUsername :EditText
    private lateinit var etCusPassword :EditText
    private lateinit var btnSignUp :Button

    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_register)

        etCusName =findViewById(R.id.entertheName)
        etCusEmail=findViewById(R.id.entertheEmail)
        etCusUsername =findViewById(R.id.entertheUsername)
        etCusPassword =findViewById(R.id.enterthePassword)
        btnSignUp =findViewById(R.id.SignupBtn)

        dbRef=FirebaseDatabase.getInstance().getReference("Customer")

        btnSignUp.setOnClickListener{
            saveCustomerData()
            val intent = Intent(this, CustomerFetchData::class.java)
            startActivity(intent)
        }


    }

    private fun saveCustomerData() {
        //getting values

        val cusName =etCusName.text.toString()
        val cusEmail =etCusEmail.text.toString()
        val cusUsername=etCusUsername.text.toString()
        val cusPassword =etCusPassword.text.toString()

        if(cusName.isEmpty()){
            etCusName.error ="Please Enter the name"
        }
        if(cusEmail.isEmpty()){
            etCusEmail.error ="Please Enter an Email"
        }
        if(cusUsername.isEmpty()){
            etCusUsername.error ="Please Enter the Username"
        }
        if(cusPassword.isEmpty()){
            etCusPassword.error ="Please Enter a password"
        }

        val cusId =dbRef.push().key!!

        val customer= CustomerModel(cusId,cusName,cusEmail,cusUsername,cusPassword)

        dbRef.child(cusId).setValue(customer)
            .addOnCompleteListener {
                Toast.makeText(this,"Registered Successfully", Toast.LENGTH_LONG).show()

                etCusName.text.clear()
                etCusEmail.text.clear()
                etCusUsername.text.clear()
                etCusPassword.text.clear()


            }.addOnFailureListener { err->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

    }
}