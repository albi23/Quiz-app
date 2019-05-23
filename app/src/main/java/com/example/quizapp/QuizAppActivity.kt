package com.example.quizapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.quizapp.Settings.SettingsPreferences

open class QuizAppActivity : AppCompatActivity() {

    var currentTheme : Int = R.style.OceanBlueTheme

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = SettingsPreferences(this).getTheme()
        Log.d("theme", "onCreate currentTheme: "+currentTheme)
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val newTheme = SettingsPreferences(this).getTheme()
        theme.applyStyle(newTheme, true)

        Log.d("theme", "onResume currentTheme: "+currentTheme)
        Log.d("theme", "onResume newTheme: "+newTheme)


        if (currentTheme != newTheme) {
            recreate()
        }
    }

}