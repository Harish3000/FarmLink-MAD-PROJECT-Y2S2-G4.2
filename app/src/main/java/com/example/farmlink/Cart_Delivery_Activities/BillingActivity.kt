package com.example.farmlink.Cart_Delivery_Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmlink.Cart_Delivery_Adapters.AddressAdapter
import com.example.farmlink.Cart_Delivery_Modals.AddressData
import com.example.farmlink.R
import com.google.firebase.database.*


class BillingActivity : AppCompatActivity() {

    private lateinit var addressRecyclerView: RecyclerView
    private lateinit var addressList: ArrayList<AddressData>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        addressRecyclerView = findViewById(R.id.rvAddresses)
        addressRecyclerView.layoutManager = LinearLayoutManager(this)
        addressRecyclerView.setHasFixedSize(true)

        addressList = arrayListOf<AddressData>()

        getAddressData()
    }

    private fun getAddressData(){
        addressRecyclerView.visibility = View.GONE

        val dbRef = FirebaseDatabase.getInstance().getReference("Addresses")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               addressList.clear()
                if(snapshot.exists()){
                    for (addressSnap in snapshot.children){
                        val addressesData = addressSnap.getValue(AddressData::class.java)
                        addressList.add(addressesData!!)
                    }
                    val mAdapter = AddressAdapter(addressList)
                    addressRecyclerView.adapter = mAdapter

                    addressRecyclerView.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}