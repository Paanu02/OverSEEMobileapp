package com.panu.overseemobileapp

import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_graph.*

class Graph : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        getdata()

    }

    fun getdata() {

        var datatest = FirebaseDatabase.getInstance().reference

        var getdata = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

               var  rt = StringBuilder()
                for (i in snapshot.children) {

                    var voltage = i.child("Voltage").getValue()
                     var current = i.child("Current").getValue()
                     var watt = i.child("Watt").getValue()
                      var energy = i.child("Energy").getValue()
                      var frequency = i.child("Frequency").getValue()
                      var powerfactor = i.child("Powerfactor").getValue()
                    rt.append("${i.key}\n $voltage\n  $current\n  $watt\n $energy\n $frequency\n $powerfactor\n ")


                }
             textView.setText(rt)


            }
        }
        datatest.addValueEventListener(getdata)
        datatest.addListenerForSingleValueEvent(getdata)


    }
}

