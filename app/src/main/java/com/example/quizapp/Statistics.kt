package com.example.quizapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import lecho.lib.hellocharts.view.PieChartView

class Statistics : AppCompatActivity() {

    private lateinit var pieChartView :PieChartView
    private var pieChartList = ArrayList<SliceValue>()
    private lateinit var win : SliceValue
    private lateinit var lost : SliceValue
    val temporaryWin = 10
    val temporaryLost = 10



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

//        chart.isChartRotationEnabled = true;
//        chart.setChartRotation(10,true)

//        val data :PieChartData = PieChartData.generateDummyData()
//        data.hasCenterCircle()
//        data.centerCircleColor = Color.BLUE
//        data.centerText1 = "STAT"

//        var pieChartList = ArrayList<SliceValue>()
//        var value = SliceValue()
//        value.value = 40f
//        value.color = Color.RED
//        pieChartList.add(value)
//        data.values = pieChartList
//        var chart2 : PieChartView = PieChartView(this)
//        chart2.chartData = data
//        var chart222 =

        pieChartView = findViewById<PieChartView>(R.id.chart)

        win = SliceValue().setLabel("Win $temporaryWin")
        win.color = Color.BLUE
        win.value = 50f

        lost = SliceValue().setLabel("Lost $temporaryLost")
        lost.color = Color.RED
        lost.value = 50f

        pieChartList.add(win)
        pieChartList.add(lost)

        var pieChartData : PieChartData = PieChartData(pieChartList)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14
        pieChartData.setHasCenterCircle(true).setCenterText1("GAMES")
        pieChartView.pieChartData = pieChartData;

    }

    //If data was changed, we need to update % in chart


}
