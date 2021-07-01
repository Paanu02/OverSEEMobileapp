package com.panu.overseemobileapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.lineChart
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.nav_header.*
import org.jetbrains.anko.internals.AnkoInternals.createAnkoContext
import org.w3c.dom.Text

class Home : AppCompatActivity() {

    private val channelid = "channelEX"
    private val notificationId = 109
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setLineChartRealtime()
        barcharrealtime()
        createNotificationChannel()
        checkcase()
        loadProfile()

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Profile")


        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item1 -> {startActivity(Intent(this@Home, Home::class.java))
                    Toast.makeText(applicationContext,"Home ",Toast.LENGTH_SHORT).show()}
                R.id.item2 -> {startActivity(Intent(this@Home, Mydevice::class.java))
                    Toast.makeText(applicationContext,"My device",Toast.LENGTH_SHORT).show()}
                R.id.item3 -> {startActivity(Intent(this@Home, DetailsDV::class.java))
                    Toast.makeText(applicationContext,"Switch",Toast.LENGTH_SHORT).show()}
                R.id.item4 -> {startActivity(Intent(this@Home,DetaillsP::class.java))
                    Toast.makeText(applicationContext,"Plug",Toast.LENGTH_SHORT).show()}
                R.id.item5 -> {
                    auth.signOut()
                    startActivity(Intent(this@Home, Welcome::class.java))
                    finish()
                    Toast.makeText(applicationContext,"Logout ",Toast.LENGTH_SHORT).show()}
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if (toggle.onOptionsItemSelected(item)){
          true
      }
        return super.onOptionsItemSelected(item)
    }


    fun setLineChartRealtime(){
        var databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                var swrt0 = snapshot.child("Realtime/Switch/0Minute/Watt").value.toString().toFloat()
                var swrt1 = snapshot.child("Realtime/Switch/1Minute/Watt").value.toString().toFloat()
                var swrt2 = snapshot.child("Realtime/Switch/2Minute/Watt").value.toString().toFloat()
                var swrt3 = snapshot.child("Realtime/Switch/3Minute/Watt").value.toString().toFloat()
                var swrt4 = snapshot.child("Realtime/Switch/4Minute/Watt").value.toString().toFloat()
                var swrt5 = snapshot.child("Realtime/Switch/5Minute/Watt").value.toString().toFloat()

                var plugrt0= snapshot.child("Realtime/Plug/0Minute/Watt").value.toString().toFloat()
                var plugrt1= snapshot.child("Realtime/Plug/1Minute/Watt").value.toString().toFloat()
                var plugrt2= snapshot.child("Realtime/Plug/2Minute/Watt").value.toString().toFloat()
                var plugrt3= snapshot.child("Realtime/Plug/3Minute/Watt").value.toString().toFloat()
                var plugrt4= snapshot.child("Realtime/Plug/4Minute/Watt").value.toString().toFloat()
                var plugrt5= snapshot.child("Realtime/Plug/5Minute/Watt").value.toString().toFloat()





                val xvalue = ArrayList<String>()

                xvalue.add("0 ")
                xvalue.add("1.5 ")
                xvalue.add("1 ")
                xvalue.add("1.5 ")
                xvalue.add("2 ")
                xvalue.add("2.5 ")
                xvalue.add("3 ")
                xvalue.add("3. ")
                xvalue.add("4 ")
                xvalue.add("4.5 ")
                xvalue.add("5 ")


                var lineentry = ArrayList<Entry>();
                lineentry.add(Entry(0f,swrt0,"0"))
                lineentry.add(Entry(1f,swrt1,"1"))
                lineentry.add(Entry(2f,swrt2,"2"))
                lineentry.add(Entry(3f,swrt3,"3"))
                lineentry.add(Entry(4f,swrt4,"4"))
                lineentry.add(Entry(5f,swrt5,"5"))
                lineentry.add(Entry(6f,swrt5,"5"))

                var lineentry1 = ArrayList<Entry>();
                lineentry1.add(Entry(0f,plugrt0,""))
                lineentry1.add(Entry(1f,plugrt1,"1"))
                lineentry1.add(Entry(2f,plugrt2,"2"))
                lineentry1.add(Entry(3f,plugrt3,"3"))
                lineentry1.add(Entry(4f,plugrt4,"4"))
                lineentry1.add(Entry(5f,plugrt5,"5"))
                lineentry1.add(Entry(6f,plugrt5,"5"))


                val linedataset = LineDataSet(lineentry, "Solar cell ( W )")
                linedataset.color = resources.getColor(R.color.bluelight)
                linedataset.setDrawValues(true)
                linedataset.circleRadius = 5f
                linedataset.setDrawFilled(true)
                linedataset.fillColor = resources.getColor(R.color.teal_700)
                linedataset.fillAlpha = 36
                linedataset.valueTextSize = 15f
                linedataset.lineWidth = 15f

                val linedataset1 = LineDataSet(lineentry1, "Grid ( W )")
                linedataset1.color = resources.getColor(R.color.OR)
                linedataset1.setDrawValues(true)
                linedataset1.circleRadius = 5f
                linedataset1.setDrawFilled(true)
                linedataset1.fillColor = resources.getColor(R.color.teal_700)
                linedataset1.fillAlpha = 36
                linedataset1.valueTextSize = 15f
                linedataset1.lineWidth = 15f




                val data = LineData(linedataset, linedataset1)
                lineChart.data = data
                lineChart.setBackgroundColor(resources.getColor(R.color.white))
                lineChart.animateXY(9, 9)
                lineChart.description.text = "Second"
                lineChart.description.textSize = 15f






                val xAxis = lineChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.textSize = 15F


            }
        })
    }


      fun barcharrealtime(){

          var databasebarrealtime =FirebaseDatabase.getInstance().reference
          databasebarrealtime.addValueEventListener(object : ValueEventListener {
              override fun onCancelled(error: DatabaseError) {
                  TODO("Not yet implemented")
              }

              override fun onDataChange(snapshot: DataSnapshot) {
                  var pvrt = snapshot.child("Realtime/PV/Parameter/Voltage").value.toString().toFloat()
                  var grrt = snapshot.child("Realtime/Grid/Parameter/Voltage").value.toString().toFloat()

                  //x axis
                  val xvalues = ArrayList<String>()
                  xvalues.add("Solar cell")
                  xvalues.add(("Grid"))
                  //y
                  var berentry = ArrayList<BarEntry>()
                  berentry.add(BarEntry(0f,pvrt,"Solar cell"))
                  var berentry1 = ArrayList<BarEntry>()
                  berentry1.add(BarEntry(1f,grrt,"Grid"))




                  //bardata set
                  val bardataset = BarDataSet(berentry,"Solar cell ( V )")
                  bardataset.color = resources.getColor(R.color.teal_700)
                  bardataset.valueTextSize = 24F



                  val bardataset1 = BarDataSet(berentry1,"Grid ( V )")
                  bardataset1.color =resources.getColor(R.color.purple_500)
                  bardataset1.valueTextSize = 24F





                  //make a bar data

                  val data = BarData(bardataset,bardataset1)
                  barChart.data = data
                  barChart.setBackgroundColor(resources.getColor(R.color.white))
                  barChart.animateXY(9,9)
                  barChart.description.text = ""
                  barChart.description.textSize = 15f



                  val yAxis = barChart.axisLeft
                  yAxis.axisMinimum = 0f
                  yAxis.axisMaximum = 240f
                  val yAxis1 = barChart.axisRight
                  yAxis1.axisMinimum = 0f
                  yAxis1.axisMaximum = 240f


                  val xAxis = barChart.xAxis
                  xAxis.position = XAxis.XAxisPosition.BOTTOM
                  xAxis.setDrawGridLines(false)
                  xAxis.setDrawAxisLine(true)
              }
          })
      }


    fun checkcase (){

        var datacheck = FirebaseDatabase.getInstance().reference
        datacheck.addValueEventListener(object:ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                var checkdata = snapshot.child("Realtime/PV/Notification").value.toString().toInt()

                if (checkdata == 0) {
                    sendNotification ()
                }
                if (checkdata == 1) {
                    sendNotification1 ()

                }

            }

        })
    }

    fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelid, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
    fun sendNotification (){

        val builder = NotificationCompat.Builder(this, channelid)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Solar cell")
            .setContentText("Off")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())
        }

    }
    fun sendNotification1 (){

        val builder = NotificationCompat.Builder(this, channelid)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Solar cell")
            .setContentText("On")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())
        }

    }

   private fun loadProfile() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Profile")

        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        // emailText.text = "Email  -- > "+user?.email

        userreference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                nvUsernameText.text = "Username - - > "+snapshot.child("Username").value.toString()
                nvEmailnameText.text = "Email - -> "+snapshot.child("Email").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }



}