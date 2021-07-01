package com.panu.overseemobileapp

import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.aachartmodel.aainfographics.aatools.AAGradientColor
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.android.synthetic.main.activity_graph.lineChart
import kotlinx.android.synthetic.main.activity_home.*


class Graph : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)


        setLineChartRealtimeSwitch()
        setLineChartRealtimePlug()



    }

    fun setLineChartRealtimeSwitch(){
        var databaseplug = FirebaseDatabase.getInstance().reference

        databaseplug.addValueEventListener(object : ValueEventListener {
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
                lineentry.add(Entry(0f,swrt0))
                lineentry.add(Entry(1f,swrt1))
                lineentry.add(Entry(2f,swrt2))
                lineentry.add(Entry(3f,swrt3))
                lineentry.add(Entry(4f,swrt4))
                lineentry.add(Entry(5f,swrt5))


                val linedataset = LineDataSet(lineentry, "Watt / W")
                linedataset.color = resources.getColor(R.color.bluelight)
                linedataset.setDrawValues(true)

                linedataset.circleRadius = 5f
                linedataset.setDrawFilled(true)
                linedataset.fillColor = resources.getColor(R.color.teal_700)
                linedataset.fillAlpha = 36
                linedataset.valueTextSize = 15f
                linedataset.lineWidth = 15f



                val data = LineData(linedataset)
                lineChart.data = data
                lineChart.setBackgroundColor(resources.getColor(R.color.white))
                lineChart.animateXY(9, 9)






                val xAxis = lineChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.textSize = 15F



            }
        })


    }

    fun setLineChartRealtimePlug(){
        var databaseswitch = FirebaseDatabase.getInstance().reference
        databaseswitch.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
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
                lineentry.add(Entry(0f,plugrt0))
                lineentry.add(Entry(1f,plugrt1))
                lineentry.add(Entry(2f,plugrt2))
                lineentry.add(Entry(3f,plugrt3))
                lineentry.add(Entry(4f,plugrt4))
                lineentry.add(Entry(5f,plugrt5))



                val linedataset = LineDataSet(lineentry, "Watt / W ")
                linedataset.color = resources.getColor(R.color.OR)

                linedataset.circleRadius = 5f
                linedataset.setDrawFilled(true)
                linedataset.fillColor = resources.getColor(R.color.teal_700)
                linedataset.fillAlpha = 36
                linedataset.valueTextSize = 15f
                linedataset.lineWidth = 15f

                val data = LineData(linedataset)
                lineChart1.data = data
                lineChart1.setBackgroundColor(resources.getColor(R.color.white))
                lineChart1.animateXY(9, 9)



                val xAxis = lineChart1.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)

            }


        })


    }

}

