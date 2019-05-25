package com.example.quizapp.Settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.quizapp.QuizAppActivity
import com.example.quizapp.R
import com.example.quizapp.SoundService
import kotlinx.android.synthetic.main.activity_categories.toolbar
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : QuizAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Settings"

        toolbar.setNavigationOnClickListener { finish() }

        soundSwitch.isChecked = SettingsPreferences(this).getSoundPreferences()
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

    fun setSound(view : View) {
        val set = SettingsPreferences(this)
        set.setSoundPreferences(soundSwitch.isChecked)

        if (soundSwitch.isChecked) {
            startService(Intent(this, SoundService::class.java))
        } else {
            stopService(Intent(this, SoundService::class.java))
        }
    }

    private fun setAppTheme(theme : Int) {
        val prefs = SettingsPreferences(this)
        prefs.setTheme(theme)
        recreate()
    }
}
