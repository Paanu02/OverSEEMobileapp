package com.panu.overseemobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        nextlog.setOnClickListener {
            startActivity(Intent(this@Welcome, Login::class.java))
        }
        nextregis.setOnClickListener {
            startActivity(Intent(this@Welcome, Register::class.java))
        }
    }
}