package com.example.farmlink

import android.app.Dialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CustomerEditProfile : AppCompatActivity() {

    private lateinit var storageReference: StorageReference
    private lateinit var imageUri:Uri
    private lateinit var dialog: Dialog
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_edit_profile)
    }
//    uploadProfilePic()

    private fun uploadProfilePic() {
        imageUri= Uri.parse("android.resource://$packageName/${R.drawable.user}")
        storageReference= FirebaseStorage.getInstance().getReference("Customers/"+auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {
            hideProgressBar()
            Toast.makeText(this, "profile updated Successfully..", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            hideProgressBar()
            Toast.makeText(this, "failed to upload the profile!!", Toast.LENGTH_LONG).show()
        }

    }

    private fun showProgressBar(){
        dialog= Dialog(this@CustomerEditProfile)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        dialog.dismiss()
    }
}