package com.example.farmlink.Cart_Delivery_Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.farmlink.HomeFragment
import com.example.farmlink.R

class SuccessDeliveryActivity : AppCompatActivity() {

    lateinit var btnHome : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartdelivery_success)

        btnHome = findViewById(R.id.btnHome)
//        btnHome.setOnClickListener {
//        val intent = Intent(this, HomeFragment::class.java)
//        startActivity(intent)
//        }
    }
}