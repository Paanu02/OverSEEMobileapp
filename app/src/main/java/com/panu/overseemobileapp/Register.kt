package com.panu.overseemobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Profile")

        register()

    }
    private fun register() {


        registerButton.setOnClickListener {

            if (TextUtils.isEmpty(userInput.text.toString())) {
                userInput.setError("Please enter user name ")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(emailInput.text.toString())) {
                userInput.setError("Please enter email name ")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(passInput.text.toString())) {
                userInput.setError("Please enter password ")
                return@setOnClickListener

            }


            auth.createUserWithEmailAndPassword(
                emailInput.text.toString(),
                passInput.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUSerDb = databaseReference?.child((currentUser?.uid!!))
                        currentUSerDb?.child("Username")?.setValue(userInput.text.toString())
                        currentUSerDb?.child("Email")?.setValue(emailInput.text.toString())

                        Toast.makeText(this@Register, "Registration Success. ", Toast.LENGTH_LONG)
                            .show()
                        finish()

                    } else {
                        Toast.makeText(
                            this@Register,
                            "Registration failed, please try again! ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        logtext.setOnClickListener {
            startActivity(Intent(this@Register, Login::class.java))
        }
    }
}