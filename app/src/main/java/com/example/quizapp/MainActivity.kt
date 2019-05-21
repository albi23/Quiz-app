package com.example.quizapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizapp.Stats.StatisticPreferences
import com.example.quizapp.Stats.Statistics

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var stat = StatisticPreferences(this) //TEST
        stat.setLOST(7,1)
        stat.setWIN(3,1)

        stat.setLOST(12,5)
        stat.setWIN(13,5)

        stat.setLOST(35,2)
        stat.setWIN(7,2)

        stat.setLOST(18,3)
        stat.setWIN(12,3)

        stat.setWIN(2,4)
        stat.setLOST(56,4)
    }

    fun playNewGame(view: View) {
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
    }

    fun showStatistics(view: View) {
        val intent = Intent(this, Statistics::class.java)

        startActivity(intent)
    }

    fun changeLanguage(view: View) {
        val intent = Intent(this, LanguageActivity::class.java)
        startActivity(intent)
    }
}
