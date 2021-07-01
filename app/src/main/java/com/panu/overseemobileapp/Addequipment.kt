package com.panu.overseemobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.panu.overseemobileapp.databinding.ActivityAddequipmentBinding
import kotlinx.android.synthetic.main.activity_addequipment.*
import kotlinx.android.synthetic.main.activity_mainapp.*
import kotlinx.android.synthetic.main.activity_welcome.*

class Addequipment : AppCompatActivity() {

    private lateinit var binding : ActivityAddequipmentBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddequipmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.AddDVBtn.setOnClickListener {


            val Devicename = binding.DVName.text.toString()
            val Type = binding.TYName.text.toString()
            val Note = binding.note.text.toString()

            database = FirebaseDatabase.getInstance().getReference("MyDevice")
            val MyDevice = DataDevice(Type,Devicename,Note)
            database.child(Devicename).setValue(MyDevice).addOnSuccessListener {


                binding.DVName.text.clear()
                binding.TYName.text.clear()
                binding.note.text.clear()


                Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Addequipment, Home::class.java))

            }.addOnFailureListener{

                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()


            }


        }
        nextdvtext.setOnClickListener {
            startActivity(Intent(this@Addequipment, Home::class.java))
        }

    }
}