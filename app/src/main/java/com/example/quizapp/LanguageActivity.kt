package com.example.quizapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_language.*

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

//        val linearLayout = findViewById<LinearLayout>(R.id.linear)
//        val imageView = imageView2 // FAZA TESTÓW
        Glide.with(this).load("https://github.com/albi23/TestPhoto/blob/master/photos/rihana.png?raw=true").into(imageView2)
//        linearLayout.addView(imageView)
    }

    fun setLanguage(view: View) {

        /*val language = view.contentDescription.toString()
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = resources.configuration
        configuration.setLocale(locale)*/
        finish()
    }

}
