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
        stat.setLOST(22)
        stat.setWIN(22)
        //tutaj potrzeba zajebistej implementacji :P
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
