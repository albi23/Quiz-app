package com.example.quizapp.Stats

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizapp.R
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import lecho.lib.hellocharts.view.PieChartView

class Statistics : AppCompatActivity() {

    private lateinit var pieChartView :PieChartView
    private var pieChartList = ArrayList<SliceValue>()
    private lateinit var win : SliceValue
    private lateinit var lost : SliceValue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)


        val pref  = StatisticPreferences(this)
         val WIN = pref.getWIN()
         val LOST =pref.getLOST()
        Log.i("INFO!!!!!!!!!!!","WIN $WIN")
        Log.i("INFO!!!!!!!!!!!","LOST $LOST")
        pieChartView = findViewById<PieChartView>(R.id.chart)

        win = SliceValue().setLabel("Win $WIN")
        win.color = Color.BLUE
        win.value = WIN.toFloat()

        lost = SliceValue().setLabel("Lost $LOST")
        lost.color = Color.RED
        lost.value = LOST.toFloat()


        pieChartList.add(win)
        pieChartList.add(lost)

        updatePercentageOfChart()
        var pieChartData : PieChartData = PieChartData(pieChartList)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14
        pieChartData.setHasCenterCircle(true).setCenterText1("GAMES")
        pieChartView.pieChartData = pieChartData

    }

    //If data was changed, we need to update % in chart
    fun updatePercentageOfChart(){

        val win = pieChartList[0].value
        val lost = pieChartList[1].value
        // total numbers of games/question whatever
        val totalGames = win + lost
        var dupa = win/totalGames;
        Log.i("INFO!!!!!!!!!!!","LOST $dupa")

        pieChartList[0].value = win/totalGames
        pieChartList[1].value = lost/totalGames
    }

//    fun setWinAmount(newValue: Int){
//
//        if(newValue > 0) this.WIN = newValue
//        updatePercentageOfChart()
//    }
//
//    fun setLostAmount(newValue: Int){
//
//        if(newValue > 0) this.LOST = newValue
//        updatePercentageOfChart()
//
//    }

}
