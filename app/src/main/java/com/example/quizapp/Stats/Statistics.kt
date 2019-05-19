package com.example.quizapp.Stats

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.ColorInt
import android.view.View
import com.example.quizapp.R
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import lecho.lib.hellocharts.view.PieChartView

class Statistics : AppCompatActivity() {

    private lateinit var pieChartView: PieChartView
    private var pieChartList = ArrayList<SliceValue>()
    private lateinit var win: SliceValue
    private lateinit var lost: SliceValue


    @ColorInt val winColor = 0xFF43A047
    @ColorInt val loseColor = 0xFFC5032B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)


        val pref = StatisticPreferences(this)
        val WIN = pref.getWIN()
        val LOST = pref.getLOST()

        pieChartView = findViewById<PieChartView>(R.id.chart)

        win = SliceValue().setLabel("Win $WIN") // Add first part
        win.color = winColor.toInt()
        win.value = WIN.toFloat()

        lost = SliceValue().setLabel("Lost $LOST") // fill second part
        lost.color = loseColor.toInt()
        lost.value = LOST.toFloat()


        pieChartList.add(win)
        pieChartList.add(lost)

        updatePercentageOfChart() //

        val pieChartData: PieChartData = PieChartData(pieChartList)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14
        pieChartData.setHasCenterCircle(true).setCenterText1("GAMES")
        pieChartView.pieChartData = pieChartData

    }

    //If data was changed, we need to update % in chart
    private fun updatePercentageOfChart() {

        val win = pieChartList[0].value
        val lost = pieChartList[1].value
        // total numbers of games/question whatever
        val totalGames = win + lost
        pieChartList[0].value = win / totalGames
        pieChartList[1].value = lost / totalGames
    }


    fun onClickBack(view: View){

        finish()
    }
}
