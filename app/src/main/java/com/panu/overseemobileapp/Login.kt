package com.panu.overseemobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser
        if(currentuser != null) {
            startActivity(Intent(this@Login, Home::class.java))
            finish()
        }
        login()
    }
    private fun login() {

        loginButton.setOnClickListener {

            if(TextUtils.isEmpty(usernameInput.text.toString())){
                usernameInput.setError("Please enter username")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(passwordInput.text.toString())){
                usernameInput.setError("Please enter password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(usernameInput.text.toString(), passwordInput.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        startActivity(Intent(this@Login, Addequipment::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@Login, "Login failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }

        }

        registerText.setOnClickListener{
            startActivity(Intent(this@Login, Register::class.java))

        }
    }
}