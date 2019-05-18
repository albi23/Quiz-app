package com.example.quizapp.Stats

import android.content.Context

class StatisticPreferences(context: Context) {

    private val PREFERENCES_NAME = "SharedPreferences"
    private val PREFERENCES_WIN = "WIN_COUNT"
    private val PREFERENCES_LOST = "LOST_COUNT"
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE)


    fun getLOST():Int{

        return preferences.getInt(PREFERENCES_LOST,0)
    }

    fun setLOST(newValue :Int){

        val edited = preferences.edit()
        edited.putInt(PREFERENCES_LOST,newValue)
        edited.apply()
    }

    fun getWIN():Int{

        return preferences.getInt(PREFERENCES_WIN,0)
    }

    fun setWIN(newValue :Int){

        val edited = preferences.edit()
        edited.putInt(PREFERENCES_WIN,newValue)
        edited.apply()
    }
}