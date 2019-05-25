package com.example.quizapp.Stats

import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.QuizAppActivity
import com.example.quizapp.R
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.chart_item.*
import kotlinx.android.synthetic.main.content_chart.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


class Statistics : QuizAppActivity() {

    @ColorInt
    val winColor = 0xFF43A047
    @ColorInt
    val loseColor = 0xFFC5032B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        setSupportActionBar(toolbar)

        chartRecyclerView.layoutManager = LinearLayoutManager(this)
        chartRecyclerView.adapter = ChartAdapter(getDataToGraph())
    }

    /**Setting menu in window*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_statistic, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_settings) {
            showResetAlert()
        }

        return false
    }

    /**Function showing an alert before reseting the statistics*/
    fun showResetAlert() {

        AlertDialog.Builder(this)
            .setTitle("Reset the statistics")
            .setMessage("Are you sure that you want to clear the statistics?")
            .setPositiveButton(android.R.string.yes) { _: DialogInterface, _ : Int ->

                val pref = StatisticPreferences(this)
                if (pref.resetData(1, 9)) {
                    Toast.makeText(this, "Statistic has been cleared", Toast.LENGTH_SHORT).show()
                    recreate()
                }
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun getDataToGraph(): ArrayList<PieChartData> {

        val chartData = ArrayList<PieChartData>()
        val arrayCategories = DBHelper.getInstance(this).allCategories
        val pref = StatisticPreferences(this)
        var pieChartData: PieChartData
        var correct: Int
        var wrong: Int


        for (i in 0 until arrayCategories.size) {

            correct = pref.getCorrect(arrayCategories[i].id)
            wrong = pref.getWrong(arrayCategories[i].id)


            pieChartData = PieChartData(
                arrayListOf(
                    SliceValue().setLabel("Wrong $wrong")
                        .setColor(loseColor.toInt())
                        .setValue(wrong.toFloat()),

                    SliceValue().setLabel("Correct $correct")
                        .setColor(winColor.toInt())
                        .setValue(correct.toFloat())
                )
            )

            if (correct + wrong == 0) {
                pieChartData.values[0].value = 50f
                pieChartData.values[1].value = 50f
            }

            pieChartData.setHasLabels(true).valueLabelTextSize = 14
            pieChartData.setHasCenterCircle(true).centerText1 = arrayCategories[i].name
            pieChartData.isValueLabelBackgroundEnabled = true
            pieChartData.slicesSpacing = 3

            chartData.add(pieChartData)
        }

        return chartData

    }


    fun onClickBack(view: View) {
        finish()
    }
}
