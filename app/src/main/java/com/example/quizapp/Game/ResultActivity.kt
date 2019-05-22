package com.example.quizapp.Game

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.quizapp.R
import com.example.quizapp.Stats.StatisticPreferences
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import kotlinx.android.synthetic.main.activity_categories.toolbar
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    private val cups = arrayOf("cup_bronze", "cup_silver", "cup_gold")
    private val finalTexts = arrayOf("Try Harder", "Not Bad", "You Did Well")
    private val ANSWERS : Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Result of the game"

        toolbar.setNavigationOnClickListener { onFinish() }

        category_text.text = intent.getStringExtra("nameCategory").toString()
        val idCategory =  intent.getIntExtra("idCategory",1)
        val correctAnswer = intent.getIntExtra("correctAnswer",0)

        progressBar.progress = correctAnswer
        correct_result.text = "$correctAnswer"
        wrong_result.text = "${ANSWERS-correctAnswer}"

        /** Specyfication of choosed cup*/

        var imageToShow = cups[0]
        var textToShow = finalTexts[0]

       if (correctAnswer in 3..4){
            imageToShow =cups[1]
           textToShow =finalTexts[1]
        }else if (correctAnswer > 4){
           imageToShow = cups[2]
           textToShow = finalTexts[2]
       }

        result_text.text = textToShow
        findViewById<ImageView>(R.id.image_result).setImageResource(resources.getIdentifier(imageToShow, "drawable", packageName))

        /** Update date in played games*/
        val preferences = StatisticPreferences(this)

        if (correctAnswer != 0) preferences.setCorrect(preferences.getCorrect(idCategory) + correctAnswer,idCategory)
        if (ANSWERS - correctAnswer != 0) preferences.setWrong(preferences.getWrong(idCategory) + (ANSWERS - correctAnswer),idCategory)
    }


    private fun onFinish(){
        finish()
    }

    /***
     * Do rozbudowy
     */
    fun postStatusToFacebook(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Share Link")

        val input = EditText(this@ResultActivity)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp
        builder.setView(input)

        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            val link = input.text
            var isValid = true
            if (link.isBlank()) {
                isValid = false
            }

            if (isValid) {

                val content = ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://github.com/albi23/Quiz-app")) // testowe dane
                    .build()
                ShareDialog.show(this, content)
            }

            dialog.dismiss()
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }

}
