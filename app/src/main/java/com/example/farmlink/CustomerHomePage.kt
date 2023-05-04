package com.example.farmlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerHomePage : AppCompatActivity() {

    private lateinit var btnStart:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val firebase :DatabaseReference=FirebaseDatabase.getInstance().getReference()

        btnStart =findViewById(R.id.GetStarted)


        btnStart.setOnClickListener {
            val intent = Intent(this,CustomerLogin::class.java)
            startActivity(intent)
        }
    }
}