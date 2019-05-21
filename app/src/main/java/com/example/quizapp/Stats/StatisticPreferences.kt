package com.example.quizapp.Stats

import android.content.Context


//There we stored data to chart_item for class statistic
class StatisticPreferences(context: Context) {

    private val PREFERENCES_NAME = "SharedPreferences"
    private var PREFERENCES_WIN = "WIN_COUNT_"
    private var PREFERENCES_LOST = "LOST_COUNT_"
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    fun getLOST(idCategory: Int): Int {
        return preferences.getInt( PREFERENCES_LOST + idCategory, 0)
    }

    fun setLOST(newValue: Int, idCategory: Int) {

        val edited = preferences.edit()
        edited.putInt(PREFERENCES_LOST + idCategory, newValue)
        edited.apply()
    }

    fun getWIN(idCategory: Int): Int {

        return preferences.getInt(PREFERENCES_WIN + idCategory, 0)
    }

    fun setWIN(newValue: Int, idCategory: Int) {

        val edited = preferences.edit()
        edited.putInt(PREFERENCES_WIN + idCategory, newValue)
        edited.apply()
    }
}