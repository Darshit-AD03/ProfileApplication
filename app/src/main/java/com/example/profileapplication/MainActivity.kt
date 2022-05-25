package com.example.profileapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.profileapplication.R
import com.example.profileapplication.databinding.ActivityMainBinding
import com.example.profileapplication.Extensions.toast
import com.example.profileapplication.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.lang.ref.Reference


class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding : ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//// sign out a user
//
//        btnSignOut.setOnClickListener {
//            firebaseAuth.signOut()
//            startActivity(Intent(this, CreateAccountActivity::class.java))
//            toast("signed out")
//            finish()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate (layoutInflater)

        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val uid = auth.currentUser?.uid

        database = Firebase.database.reference

        //databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        fun writeNewUser(userId: String, name: String, email: String, location: String) {
            val user = User(name, email, location)

            database.child("users").child(userId).setValue(user)
        }

        binding.btnSave.setOnClickListener{
            val name1 = binding.txtFirstName1.text.toString()
            val email1 = binding.txtEmail1.text.toString()
            val location1 = binding.txtLocation1.text.toString()

            val user = User (name1, email1, location1)

            if (uid != null){
                database.child("users").child(uid).child("username").setValue(user)
            }
        }


        btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, CreateAccountActivity::class.java))
            toast("signed out")
            finish()
        }

        btnUpload.setOnClickListener{
            startActivity(Intent(this, ImageUploadActivity::class.java))
        }


    }


}
