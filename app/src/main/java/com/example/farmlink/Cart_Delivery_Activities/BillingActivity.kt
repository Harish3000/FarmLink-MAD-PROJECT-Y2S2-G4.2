package com.example.farmlink.Cart_Delivery_Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmlink.Cart_Delivery_Adapters.AddressAdapter
import com.example.farmlink.Cart_Delivery_Modals.AddressData
import com.example.farmlink.R
import com.google.firebase.database.*


class BillingActivity : AppCompatActivity() {

    private lateinit var addressRecyclerView: RecyclerView
    private lateinit var btnAddAddresses: ImageView
    private lateinit var btn_back: ImageView
    private lateinit var btnOrder: Button
    private lateinit var addressList: ArrayList<AddressData>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        addressRecyclerView = findViewById(R.id.rvAddresses)
        addressRecyclerView.layoutManager = LinearLayoutManager(this)
        addressRecyclerView.setHasFixedSize(true)

        addressList = arrayListOf<AddressData>()

        btnAddAddresses = findViewById(R.id.btnAddAddresses)
        btnAddAddresses.setOnClickListener{
            val intent = Intent(this, AddressAcivity::class.java)
            startActivity(intent)
        }

        btn_back = findViewById(R.id.btn_back)
        btn_back.setOnClickListener{ finish() }

//        btnOrder = findViewById(R.id.btnOrder)
//        btnOrder.setOnClickListener {
//            var intent = Intent(this, )
//        }

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

                    //to click on the card and navigate
                    mAdapter.setOnItemClickListener(object : AddressAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@BillingActivity, AddressDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("addressId", addressList[position].addressId)
                            intent.putExtra("fullName", addressList[position].fullName)
                            intent.putExtra("addressLine1", addressList[position].addressLine1)
                            intent.putExtra("addressLine2", addressList[position].addressLine2)
                            intent.putExtra("city", addressList[position].city)
                            intent.putExtra("district", addressList[position].district)
                            intent.putExtra("phone", addressList[position].phone)
                            startActivity(intent)
                        }
                    })

                    addressRecyclerView.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}