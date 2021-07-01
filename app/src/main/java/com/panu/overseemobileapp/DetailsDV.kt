package com.panu.overseemobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_on_off.*

class DetailsDV : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    lateinit var DVname : TextView
    lateinit var DVtype : TextView
    lateinit var DVnote : TextView
    lateinit var DVbill : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_details_dv)

        OnOffSwitch()

        DVname = findViewById(R.id.dtname)
        DVtype = findViewById(R.id.dttype)
        DVnote = findViewById(R.id.dtnote)
        DVbill = findViewById(R.id.dtbill)

      var  databasedtt = FirebaseDatabase.getInstance().reference
        databasedtt.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailsDV,error.code,Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var dvna = snapshot.child("MyDevice/01/devicename").value.toString()
                var dvt = snapshot.child("MyDevice/01/type").value.toString()
                var dvno = snapshot.child("MyDevice/01/note").value.toString()
                var dvb = snapshot.child("Realtime/Switch/amount").value.toString()

                DVname.text = dvna
                DVtype.text = dvt
                DVnote.text = dvno
                DVbill.text = dvb

            }




        })
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
                if (toggleButtonSwitch.checkedButtonId== View.NO_ID){

                    Toast.makeText(this, "Switch off", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}