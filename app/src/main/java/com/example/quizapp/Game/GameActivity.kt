package com.example.quizapp.Game

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.DBModel.Question
import com.example.quizapp.R
import kotlinx.android.synthetic.main.activity_game.*
import java.lang.Exception
import java.util.ArrayList

class GameActivity : AppCompatActivity() {

    private var categoryNumber = 0
    private var roundNumber = 0
    private var currQuestionNumber = 0 //actually displayed question id
    private var usedQuestionNumbers = arrayListOf<Int>() //stores question number from previous rounds to prevent repetitions of questions

    private var areButtonsLocked = false
    private lateinit var  baseBtnColor: Drawable //button color before repainting
    private var  questionList : MutableList<Question>  = ArrayList<Question>()


    private lateinit var timer: CountDownTimer
    private val timeToAnswer = 30000L
    private val timerRefreshFrequency  = 40L
    private val interval = 1000L //one animation time interval in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        /** Get from intent choosed category*/
        val idCategory = intent.getIntExtra("idCategory",1)
        val databaseRequest = DBHelper(this)
        questionList  = databaseRequest.getQuestionBycategory(idCategory)

        categoryNumber = 1

        baseBtnColor = answer_btn_a.background

        nextQuestion()
        timer = QuestionTimer(timeToAnswer, timerRefreshFrequency)
        timer.start()
    }

    fun onAnswerChoose(v: View){
        if(areButtonsLocked) return

        areButtonsLocked = true //lock all buttons till the next round
        timer.cancel() //stop the timer

        val clickedBtn = (v as Button)
        val correctAnswer = getStringByName("category_${categoryNumber}_question_${currQuestionNumber}_correct_answer")
        val chosenAnswer = clickedBtn.text.toString()

        var color = getDrawable(R.color.incorrectAnswerColor) //color for clicked button and answer history btn
        val correctAnswerColor = getDrawable(R.color.correctAnswerColor)

        if(chosenAnswer.compareTo(correctAnswer) == 0){
            color = correctAnswerColor
            moveToNextQuestionAfterDelay(3 * interval)
            setAnswerHistoryColor(color)
        }else {
            handleIncorrectAnswer()
        }

        clickedBtn.background = color
    }

    private fun handleIncorrectAnswer(){
        val correctAnswer = getStringByName("category_${categoryNumber}_question_${currQuestionNumber}_correct_answer")
        val correctAnswerColor = getDrawable(R.color.correctAnswerColor)
        val incorrectAnswerColor = getDrawable(R.color.incorrectAnswerColor)

        var correctAnswerBtn = answer_btn_a
        when (correctAnswer) {
            answer_btn_b.text.toString() -> correctAnswerBtn = answer_btn_b
            answer_btn_c.text.toString() -> correctAnswerBtn = answer_btn_c
            answer_btn_d.text.toString() -> correctAnswerBtn = answer_btn_d
        }

        setAnswerHistoryColor(incorrectAnswerColor)

        //wrong answer animation
        correctAnswerBtn.background = correctAnswerColor
        Handler().postDelayed({ correctAnswerBtn.background = baseBtnColor }, interval)
        Handler().postDelayed({ correctAnswerBtn.background = correctAnswerColor }, 2 * interval)
        Handler().postDelayed({ correctAnswerBtn.background = baseBtnColor }, 3 * interval)
        Handler().postDelayed({ correctAnswerBtn.background = correctAnswerColor }, 4 * interval)

        moveToNextQuestionAfterDelay(7 * interval)
    }

    private fun moveToNextQuestionAfterDelay(delayTime: Long){
        Handler().postDelayed({
            nextQuestion()
            timer.start()
            areButtonsLocked = false
        }, delayTime)
    }

    private fun setAnswerHistoryColor(color: Drawable?){
        when (roundNumber){
            1 -> first_question_result_btn.background = color
            2 -> second_question_result_btn.background = color
            3 -> third_question_result_btn.background = color
            4 -> fourth_question_result_btn.background = color
            5 -> fifth_question_result_btn.background = color
        }
    }

    private fun nextQuestion(){
        if(roundNumber == 5){
            finish() //prevents moving back to the game from results activity
            return
        }

        //color answer buttons in default color
        answer_btn_a.background = baseBtnColor
        answer_btn_b.background = baseBtnColor
        answer_btn_c.background = baseBtnColor
        answer_btn_d.background = baseBtnColor

        roundNumber++
        question_number_txt.text = getString(R.string.question_number, roundNumber.toString())

        //generate question number till it is not repeated
        var randomQuestionNumber = (1 .. countNumberOfQuestionsInCurrentCategory()).random()
        while(usedQuestionNumbers.contains(randomQuestionNumber)){
            randomQuestionNumber = (1 .. countNumberOfQuestionsInCurrentCategory()).random()
        }

        usedQuestionNumbers.add(randomQuestionNumber)
        currQuestionNumber = randomQuestionNumber

        val questionsArray: ArrayList<String> = arrayListOf(
            getStringByName("category_${categoryNumber}_question_${currQuestionNumber}_answer_1"),
            getStringByName("category_${categoryNumber}_question_${currQuestionNumber}_answer_2"),
            getStringByName("category_${categoryNumber}_question_${currQuestionNumber}_answer_3"),
            getStringByName("category_${categoryNumber}_question_${currQuestionNumber}_answer_4")
        )
        questionsArray.shuffle()

        question_txt.text = getStringByName("category_${categoryNumber}_question_$currQuestionNumber")
        answer_btn_a.text = questionsArray[0]
        answer_btn_b.text = questionsArray[1]
        answer_btn_c.text = questionsArray[2]
        answer_btn_d.text = questionsArray[3]
    }

    private fun countNumberOfQuestionsInCurrentCategory(): Int{
        var count = 0
        while(true)
            try{
                getStringByName("category_${categoryNumber}_question_${count+1}")
                count++
            }catch (e : Exception){
                break
            }

        return count
    }

    //question time progress bar controller
    inner class QuestionTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            val progress = (timeToAnswer - millisUntilFinished).toInt()
            remaining_time_bar.progress = progress
        }

        override fun onFinish() {
            handleIncorrectAnswer()
            remaining_time_bar.progress = timeToAnswer.toInt()
        }
    }

    private fun getStringByName(resourceName : String): String{
        return resources.getString(resources.getIdentifier(resourceName, "string", packageName))
    }
}
