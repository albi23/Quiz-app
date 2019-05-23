package com.example.quizapp.Settings

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import com.example.quizapp.QuizAppActivity
import com.example.quizapp.R
import kotlinx.android.synthetic.main.activity_categories.*

class SettingsActivity : QuizAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Settings"

        toolbar.setNavigationOnClickListener { finish() }
    }

    fun setOceanBlueTheme(view : View) {
        setAppTheme(R.style.OceanBlueTheme)
    }

    fun setSexOnTheBeachTheme(view : View) {
        setAppTheme(R.style.SOTBTheme)
    }

    fun setMiamiSunsetTheme(view : View) {
        setAppTheme(R.style.MiamiSunsetTheme)
    }

    fun setRaspberryDaydreamTheme(view : View) {
        setAppTheme(R.style.RaspberryDaydreamTheme)
    }

    fun setSound(switch : View) {

    }

    private fun setAppTheme(theme : Int) {
        val prefs = SettingsPreferences(this)
        Log.d("theme", "setApptheme: "+theme)
        prefs.setTheme(theme)
        recreate()
    }
}
