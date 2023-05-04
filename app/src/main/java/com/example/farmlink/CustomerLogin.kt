package com.example.farmlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CustomerLogin : AppCompatActivity() {

    private lateinit var btnSignUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)

        btnSignUp =findViewById(R.id.Signup)


        btnSignUp.setOnClickListener {
            val intent = Intent(this,CustomerSignUpPage::class.java)
            startActivity(intent)
        }
    }
}