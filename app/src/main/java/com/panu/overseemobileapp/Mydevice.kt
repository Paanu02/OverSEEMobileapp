package com.panu.overseemobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.panu.overseemobileapp.databinding.ActivityMydeviceBinding
import kotlinx.android.synthetic.main.activity_addequipment.*
import kotlinx.android.synthetic.main.activity_mydevice.*

class Mydevice : AppCompatActivity() {

    private lateinit var binding: ActivityMydeviceBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var deviceRecyclerview : RecyclerView
    private lateinit var deviceArrayList : ArrayList<DataDevice>

    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMydeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)
            getUserData()
        deviceRecyclerview = findViewById(R.id.DeviceList)
        deviceRecyclerview.layoutManager = LinearLayoutManager(this)
        deviceRecyclerview.setHasFixedSize(true)
        deviceArrayList = arrayListOf<DataDevice>()


        toadddv.setOnClickListener {
            startActivity(Intent(this@Mydevice, Addequipment::class.java))
        }



    }
    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("MyDevice")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val mydevicename = userSnapshot.getValue(DataDevice::class.java)
                        deviceArrayList.add(mydevicename!!)
                    }
                    var adapter = MyAdapter(deviceArrayList)
                   deviceRecyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            Toast.makeText(this@Mydevice,"Clicked $position",Toast.LENGTH_SHORT).show()
                           /* val intent = Intent(this@Mydevice,DetailsDV::class.java)
                            startActivity(intent)*/
                        }

                    })
                }

            }

            })

    }

}