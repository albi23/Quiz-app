package com.example.quizapp.Stats

import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.R
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.content_chart.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue

class Statistics : AppCompatActivity() {

    @ColorInt val winColor = 0xFF43A047
    @ColorInt val loseColor = 0xFFC5032B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        setSupportActionBar(toolbar)

        chartRecyclerView.layoutManager = LinearLayoutManager(this)
        chartRecyclerView.adapter = ChartAdapter(getDataToGraph()) {}
    }


    fun getDataToGraph():ArrayList<PieChartData>{

        val chartData = ArrayList<PieChartData>()
        val arrayCategories = DBHelper.getInstance(this).allCategories
        val pref = StatisticPreferences(this)
        var pieChartData : PieChartData
        var WIN : Int
        var LOST : Int


        for ( i in 0 ..  arrayCategories.size - 1) {

            WIN = pref.getWIN(arrayCategories[i].id)
            LOST = pref.getLOST(arrayCategories[i].id)


             pieChartData = PieChartData(
                arrayListOf(
           SliceValue().
                setLabel("Wrong $LOST")
                .setColor(loseColor.toInt())
                .setValue(LOST.toFloat()),

           SliceValue().
                setLabel("Correct $WIN")
                .setColor(winColor.toInt())
                .setValue(WIN.toFloat())
            ))

            if(WIN+LOST == 0 ){
                pieChartData.values[0].setValue(50f)
                pieChartData.values[1].setValue(50f)
            }

            pieChartData.setHasLabels(true).valueLabelTextSize = 14
            pieChartData.setHasCenterCircle(true).setCenterText1(arrayCategories[i].name)
            pieChartData.isValueLabelBackgroundEnabled = true;

            chartData.add(pieChartData);

        }

        return chartData

    }



    //If data was changed, we need to update % in chart_item
//    private fun updatePercentageOfChart(pieChartList : ArrayList<SliceValue>) {
//
//        val win = pieChartList[0].value
//        val lost = pieChartList[1].value
//        // total numbers of games/question whatever
//        val totalGames : Int = (win + lost).toInt()
//        if (totalGames != 0) {
//            pieChartList[0].value = win / totalGames
//            pieChartList[1].value = lost / totalGames
//        } else {
//            /*no data =  50 % in both correct and wrong */
//            pieChartList[0].value = 50f
//            pieChartList[1].value = 50f
//        }
//
//    }


    fun onClickBack(view: View){

        finish()
    }
}
