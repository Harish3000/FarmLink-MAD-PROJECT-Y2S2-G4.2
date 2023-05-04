package com.example.farmlink

import android.annotation.SuppressLint
import android.app.Dialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CustomerSignUpPage : AppCompatActivity() {

    private lateinit var etCusName :EditText
    private lateinit var etCusEmail :EditText
    private lateinit var etCusUsername :EditText
    private lateinit var etCusPassword :EditText
    private lateinit var btnSignUp :Button
    private lateinit var dbRef:DatabaseReference
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_sign_up_page)

        etCusName =findViewById(R.id.entertheName)
        etCusEmail =findViewById(R.id.entertheEmail)
        etCusUsername =findViewById(R.id.entertheUsername)
        etCusPassword =findViewById(R.id.enterthePassword)
        btnSignUp =findViewById(R.id.SignupBtn)

        //get the reference from customer node
        dbRef =FirebaseDatabase.getInstance().getReference("Customers")

        btnSignUp.setOnClickListener {
            showProgressBar()
            saveCustomerData()
        }


    }

    private fun saveCustomerData(){
        // getting input values
        val cusName =etCusName.text.toString()
        val cusEmail=etCusEmail.text.toString()
        val cusUsername =etCusUsername.text.toString()
        val cusPassword =etCusPassword.text.toString()

        //if the fields are empty an error message will display
        if(cusName.isEmpty()){
            etCusName.error="Please enter your Name"
        }
        if(cusEmail.isEmpty()){
            etCusEmail.error="Please enter your Email"
        }
        if(cusUsername.isEmpty()){
            etCusUsername.error="Please enter the Username"
        }
        if(cusPassword.isEmpty()){
            etCusPassword.error="Please enter the Password"
        }

        //create unique ID
        val cusId =dbRef.push().key!!
        val customer =CustomerModel(cusId,cusName,cusEmail,cusUsername,cusPassword)

        //send the data to firebase database
        dbRef.child(cusId).setValue(customer)  //create child using the cusId
            .addOnCompleteListener{
                if(it.isSuccessful) {

                    Toast.makeText(this, "Registered Successfully..", Toast.LENGTH_LONG).show()

                    //clear the data after register to the system
                    etCusName.text.clear()
                    etCusEmail.text.clear()
                    etCusUsername.text.clear()
                    etCusPassword.text.clear()
                }
            }.addOnFailureListener{err ->
                hideProgressBar()
                Toast.makeText(this,"Couldn't register!! Error ${err.message}",Toast.LENGTH_LONG).show()

            }

    }



    private fun showProgressBar(){
        dialog=Dialog(this@CustomerSignUpPage)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        dialog.dismiss()
    }
}