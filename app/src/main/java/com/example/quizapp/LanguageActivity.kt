package com.example.quizapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
    }

    fun setLanguage(view: View) {
        // TODO ("language change implementation")

        /*val language = view.contentDescription.toString()
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = resources.configuration
        configuration.setLocale(locale)*/
        finish()
    }

}
