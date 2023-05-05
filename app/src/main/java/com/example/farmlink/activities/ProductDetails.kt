package com.example.farmlink.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.farmlink.models.FeedbackModel
import com.example.farmlink.R
import com.google.firebase.database.FirebaseDatabase

class ProductDetails : AppCompatActivity() {
    private lateinit var tvSellerId: TextView
    private lateinit var tvSellerName: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnToFeedback: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        initView()
        setValuesToViews()

        btnToFeedback = findViewById(R.id.feedback)


        btnToFeedback.setOnClickListener {
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }


    }


    private fun initView() {
        tvSellerId = findViewById(R.id.tvSellerId)
        tvSellerName = findViewById(R.id.tvSellerName)
        tvRating = findViewById(R.id.tvRating)
        tvDescription = findViewById(R.id.tvDescription)

    }

    private fun setValuesToViews() {
        tvSellerId.text = intent.getStringExtra("sellerId")
        tvSellerName.text = intent.getStringExtra("sellerName")
        tvRating.text = intent.getStringExtra("rating")
        tvDescription.text = intent.getStringExtra("description")

    }
}