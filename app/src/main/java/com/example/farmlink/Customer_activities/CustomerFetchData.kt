package com.example.farmlink.Customer_activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmlink.R
import com.example.farmlink.Customer_models.CustomerModel
import com.example.farmlink.Customer_adapters.CustomerAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CustomerFetchData : AppCompatActivity() {

    private lateinit var cusRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var cusList:ArrayList<CustomerModel>
    private lateinit var dbRef:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_fetch_data)


        cusRecyclerView=findViewById(R.id.rvCustomer)
        cusRecyclerView.layoutManager=LinearLayoutManager(this) //check meaning
        cusRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        cusList= arrayListOf<CustomerModel>()

        getCustomerData()

    }

    private fun getCustomerData() {
        cusRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef =FirebaseDatabase.getInstance().getReference("Customer") //reference the db

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cusList.clear() //avoid the duplication of data
                if(snapshot.exists()){  //if data is exists
                    for(cusSnap in snapshot.children){
                        val cusData =cusSnap.getValue(CustomerModel::class.java)
                        cusList.add(cusData!!)    //to avoid null data used!!
                    }
                    val mAdapter = CustomerAdapter(cusList)
                    cusRecyclerView.adapter=mAdapter

                    mAdapter.setOnProfileClickListener(object: CustomerAdapter.onProfileClickListener{
                        override fun onProfileClick(position: Int) {
                            val intent =Intent(this@CustomerFetchData , CustomerDetailActivity::class.java)

                            //put extras
                            intent.putExtra("cusId",cusList[position].cusId)
                            intent.putExtra("cusName",cusList[position].cusName)
                            intent.putExtra("cusEmail",cusList[position].cusEmail)
                            intent.putExtra("cusUsername",cusList[position].cusUsername)
                            startActivity(intent)
                        }
                    })

                    cusRecyclerView.visibility =View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}