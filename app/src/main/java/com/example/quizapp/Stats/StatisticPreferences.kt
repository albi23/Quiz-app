package com.example.quizapp.Stats

import android.content.Context


//There we stored data to chart_item for class statistic
class StatisticPreferences(context: Context) {

    private val PREFERENCES_NAME = "SharedPreferences"
    private var PREFERENCES_WIN = "WIN_COUNT_"
    private var PREFERENCES_LOST = "LOST_COUNT_"
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    fun getWrong(idCategory: Int): Int {
        return preferences.getInt( PREFERENCES_LOST + idCategory, 0)
    }

    fun setWrong(newValue: Int, idCategory: Int) {

        val edited = preferences.edit()
        edited.putInt(PREFERENCES_LOST + idCategory, newValue)
        edited.apply()
    }

    fun getCorrect(idCategory: Int): Int {

        return preferences.getInt(PREFERENCES_WIN + idCategory, 0)
    }

    fun setCorrect(newValue: Int, idCategory: Int) {

        val edited = preferences.edit()
        edited.putInt(PREFERENCES_WIN + idCategory, newValue)
        edited.apply()
    }
}