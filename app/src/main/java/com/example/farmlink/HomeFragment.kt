package com.example.farmlink

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.farmlink.activities.FeedbackActivity
import com.example.farmlink.activities.ProductPageActivity

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val productButton = view.findViewById<Button>(R.id.my_button2)
        productButton.setOnClickListener {
            val intent = Intent(activity, ProductPageActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}