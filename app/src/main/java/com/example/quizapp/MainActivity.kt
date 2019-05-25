package com.example.quizapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.quizapp.Settings.SettingsActivity
import com.example.quizapp.Settings.SettingsPreferences
import com.example.quizapp.Stats.Statistics

class MainActivity : QuizAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (SettingsPreferences(this).getSoundPreferences()) {
            startService(Intent(this, SoundService::class.java))
        }
    }

    fun playNewGame(view: View) {
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
    }

    fun showStatistics(view: View) {
        val intent = Intent(this, Statistics::class.java)
        startActivity(intent)
    }

    fun showSettings(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()

        if (SettingsPreferences(this).getSoundPreferences()) {
            stopService(Intent(this, SoundService::class.java))
        }
    }
}
