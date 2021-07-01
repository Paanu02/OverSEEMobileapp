package com.panu.overseemobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_on_off.*

class DetaillsP : AppCompatActivity() {

    lateinit var DVname1 : TextView
    lateinit var DVtype1 : TextView
    lateinit var DVnote1 : TextView
    lateinit var DVbill1 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaills_p)

        OnOffPlug()

        DVname1 = findViewById(R.id.dtname1)
        DVtype1 = findViewById(R.id.dttype1)
        DVnote1 = findViewById(R.id.dtnote1)
        DVbill1 = findViewById(R.id.dtbill1)


        var  databasedtt1 = FirebaseDatabase.getInstance().reference
        databasedtt1.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetaillsP,error.code, Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                var dvna1 = snapshot.child("MyDevice/02/devicename").value.toString()
                var dvt1 = snapshot.child("MyDevice/02/type").value.toString()
                var dvno1 = snapshot.child("MyDevice/02/note").value.toString()
                var dvb1 = snapshot.child("Realtime/Plug/amount").value.toString()

                DVname1.text = dvna1
                DVtype1.text = dvt1
                DVnote1.text = dvno1
                DVbill1.text = dvb1
            }

        })
    }

    fun OnOffPlug(){

        val databaseplug = FirebaseDatabase.getInstance().reference
        var df = 3
        databaseplug.child("Realtime/Plug/On-Off").setValue(df)
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
                if (toggleButtonPlug.checkedButtonId== View.NO_ID){

                    Toast.makeText(this, "Plug off", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}