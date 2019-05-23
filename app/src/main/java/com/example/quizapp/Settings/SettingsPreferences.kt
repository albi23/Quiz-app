package com.example.quizapp.Settings

import android.content.Context
import com.example.quizapp.R

class SettingsPreferences(context : Context) {
    private val SETTINGS_PREFS_NAME = "SettingPreferences"
    private val SOUND_PREF = "Sound"
    private val THEME_PREF = "Theme"
    private var preferences = context.getSharedPreferences(SETTINGS_PREFS_NAME, Context.MODE_PRIVATE)

    fun getSoundPreferences() : Boolean {
        return  preferences.getBoolean(SOUND_PREF, true)
    }

    fun setSoundPreferences(pref : Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(SOUND_PREF, pref)
        editor.apply()
    }

    fun getTheme() : Int {
        return preferences.getInt(THEME_PREF, R.style.OceanBlueTheme)
    }

    fun setTheme(theme : Int) {
        val editor = preferences.edit()
        editor.putInt(THEME_PREF, theme)
        editor.apply()
    }
}