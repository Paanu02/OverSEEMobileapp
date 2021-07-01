package com.panu.overseemobileapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_on_off.*

class OnOff : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_off)
        OnOffSwitch()
        OnOffPlug()

    }
    fun OnOffSwitch(){
        val databaseswitch = FirebaseDatabase.getInstance().reference
        var createset = 3
        databaseswitch.child("Realtime/Switch/On-Off").setValue(createset)


        toggleButtonSwitch.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if(isChecked){
                var on = 0
                databaseswitch.child("Realtime/Switch/On-Off").setValue(on)
                when(checkedId){

                    R.id.btnSwitch -> Toast.makeText(this, "Switch on", Toast.LENGTH_SHORT).show()
                }
            }else{
                var off = 1
                databaseswitch.child("Realtime/Switch/On-Off").setValue(off)
                if (toggleButtonSwitch.checkedButtonId==View.NO_ID){

                    Toast.makeText(this, "Switch off", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun OnOffPlug(){

        val databaseplug = FirebaseDatabase.getInstance().reference
        var off = 1
        databaseplug.child("Realtime/Plug/On-Off").setValue(off)
        toggleButtonPlug.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked){
                var on = 0
                databaseplug.child("Realtime/Plug/On-Off").setValue(on)
                when(checkedId){

                    R.id.btnPlug -> Toast.makeText(this, "Plug on", Toast.LENGTH_SHORT).show()
                }
            }else{
                var off =1
                databaseplug.child("Realtime/Plug/On-Off").setValue(off)
                if (toggleButtonPlug.checkedButtonId==View.NO_ID){

                    Toast.makeText(this, "Plug off", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }



}