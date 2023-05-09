package com.example.finalfarmlinkapp.Customer_activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.finalfarmlinkapp.R

class GetStarted : AppCompatActivity() {
    private lateinit var btnGetStarted: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        btnGetStarted=findViewById(R.id.GetStarted)

        btnGetStarted.setOnClickListener{
            val intent =Intent(this, CustomerRegister::class.java)
            startActivity(intent)
        }
    }
}