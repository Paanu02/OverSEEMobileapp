package com.panu.overseemobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.aachartmodel.aainfographics.aatools.AAGradientColor
import com.google.firebase.database.*
import java.lang.Math.cos
import java.lang.Math.sin

class testdata : AppCompatActivity() {

    private var aaChartModel = AAChartModel()
    private var aaChartView: AAChartView? = null
    private var updateTimes: Int = 0
   

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testdata)

        setUpAAChartView()
        repeatUpdateChartData()


    }









    fun setUpAAChartView() {
        aaChartView = findViewById(R.id.aa_chart_view)
        aaChartModel = configureAAChartModel()
        val aaOptions: AAOptions = aaChartModel.aa_toAAOptions()
        if (aaChartModel.chartType == AAChartType.Column) {
            aaOptions.plotOptions?.column!!
                .groupPadding(0f)
                .pointPadding(0f)
                .borderRadius(5f)
        } else if (aaChartModel.chartType == AAChartType.Bar) {
            aaOptions.plotOptions?.bar!!
                .groupPadding(+0f)
                .pointPadding(0f)
                .borderRadius(5f)
        }
        aaChartView?.aa_drawChartWithChartOptions(aaOptions)
    }

    fun configureAAChartModel(): AAChartModel {
        val aaChartModel = configureChartBasicContent()
        aaChartModel.series(this.configureChartSeriesArray())
        return aaChartModel
    }
    fun configureChartBasicContent(): AAChartModel {


        return AAChartModel()
            .chartType(AAChartType.Area)
            .xAxisVisible(true)
            .yAxisVisible(true)
            .title("Energy")
            .yAxisTitle("Watt")
            .colorsTheme(arrayOf(
                AAGradientColor.Sanguine,
                AAGradientColor.DeepSea,
                AAGradientColor.NeonGlow,
                AAGradientColor.WroughtIron
            ))
            .stacking(AAChartStackingType.Normal)
    }
    @Suppress("UNCHECKED_CAST")
    fun configureChartSeriesArray(): Array<AASeriesElement> {

        val Range = 40
        val numberArr1 = arrayOfNulls<Any>(Range)
        val numberArr2 = arrayOfNulls<Any>(Range)
        var y1: Double
        var y2: Double
        val max = 38
        val min = 1
        val random = (Math.random() * (max - min) + min).toInt()
        for (i in 0 until Range) {
            y1 = sin(random * (i * Math.PI / 180)) + i * 2 * 0.01
            y2 = cos(random * (i * Math.PI / 180)) + i * 3 * 0.01
            numberArr1[i] = y1
            numberArr2[i] = y2




        }

        return arrayOf(
            AASeriesElement()
                .name("2017")
                .data(arrayOf(1, numberArr2 as Array<Any>,3)),
                       AASeriesElement()
                                 .name("2018")
                                 .data(numberArr1 as Array<Any>),
                         AASeriesElement()
                                 .name("2019")
                                 .data(numberArr1 as Array<Any>),
                         AASeriesElement()
                                 .name("2020")
                                 .data(numberArr2 as Array<Any>)
                )


    }
    fun repeatUpdateChartData() {
        val mStartVideoHandler = Handler()

        val mStartVideoRunnable: Runnable = object : Runnable {

            override fun run() {
                val seriesArr = configureChartSeriesArray()
                aaChartView!!.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(seriesArr)

                mStartVideoHandler.postDelayed(this, 1000)
                updateTimes += 1

                print("$updateTimes")
            }
        }

        mStartVideoHandler.postDelayed(mStartVideoRunnable, 2000)
    }
}

