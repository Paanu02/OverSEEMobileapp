package com.panu.overseemobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_mainapp.*
import kotlinx.android.synthetic.main.activity_profile.*

class Mainapp : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainapp)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        gohome.setOnClickListener {

            startActivity(Intent(this@Mainapp, Home::class.java))

        }



        goParemeter.setOnClickListener {



        }

        logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@Mainapp, Welcome::class.java))
            finish()
        }

    }
}