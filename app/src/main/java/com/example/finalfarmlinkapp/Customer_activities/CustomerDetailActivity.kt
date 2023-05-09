package com.example.finalfarmlinkapp.Customer_activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.finalfarmlinkapp.Customer_models.CustomerModel
import com.example.finalfarmlinkapp.R
import com.google.firebase.database.FirebaseDatabase


class CustomerDetailActivity : AppCompatActivity() {
    private lateinit var tvCusId:TextView
    private lateinit var tvCusName:TextView
    private lateinit var tvCusEmail:TextView
    private lateinit var tvCusUsername:TextView
    private lateinit var btnSave: Button
    private lateinit var btnDelete:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_detail)

        initView()
        setValuesToViews()

        btnSave.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cusId").toString(),
                intent.getStringExtra("cusName").toString()

            )
        }

        btnDelete.setOnClickListener {
            deleteProfile(
                intent.getStringExtra("cusId").toString()
            )
        }
    }

    private fun deleteProfile(id: String) {
        val dbRef =FirebaseDatabase.getInstance().getReference("Customer").child(id)
        val mTask =dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Account is deleted!!",Toast.LENGTH_LONG).show()
            val intent= Intent(this, GetStarted::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error->
            Toast.makeText(this,"Account cannot deleted...Err${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvCusId= findViewById(R.id.tvCusId)
        tvCusName = findViewById(R.id.tvCusName)
        tvCusEmail = findViewById(R.id.tvCusEmail)
        tvCusUsername = findViewById(R.id.tvCusUsername)

        btnSave = findViewById(R.id.SaveBtn)
        btnDelete = findViewById(R.id.DeleteBtn)
    }



    private fun setValuesToViews(){
        tvCusId.text=intent.getStringExtra("cusId")
        tvCusName.text=intent.getStringExtra("cusName")
        tvCusEmail.text=intent.getStringExtra("cusEmail")
        tvCusUsername.text=intent.getStringExtra("cusUsername")
    }

    private fun openUpdateDialog(
        cusId: String,
        cusName: String
    ){
        val mDialog =AlertDialog.Builder(this)
        val inflater =layoutInflater
        val mDialogView=inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etCusName=mDialogView.findViewById<EditText>(R.id.etCusName)
        val etCusEmail=mDialogView.findViewById<EditText>(R.id.etCusEmail)
        val etCusUsername=mDialogView.findViewById<EditText>(R.id.etCusUsername)

        val btnUpdateData=mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etCusName.setText(intent.getStringExtra("cusName").toString())  //passing intend data from profile to update dialog
        etCusEmail.setText(intent.getStringExtra("cusEmail").toString())
        etCusUsername.setText(intent.getStringExtra("cusUsername").toString())

        mDialog.setTitle("Updating $cusName's Profile")
        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateCusData(
                cusId,
                etCusName.text.toString(),
                etCusEmail.text.toString(),
                etCusUsername.text.toString()
            )
            Toast.makeText(applicationContext,"Profile Updated!!" ,Toast.LENGTH_LONG).show()

            //set updated data to text views
            tvCusName.text=etCusName.text.toString()
            tvCusEmail.text=etCusEmail.text.toString()
            tvCusUsername.text=etCusUsername.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateCusData(id: String, name: String, email: String, username: String) {
        val dbRef=FirebaseDatabase.getInstance().getReference("Customer").child(id)
        val cusInfo = CustomerModel(id,name,email,username)
        dbRef.setValue(cusInfo)
    }


}