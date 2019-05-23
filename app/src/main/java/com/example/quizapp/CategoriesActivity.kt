package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.DBModel.Category
import com.example.quizapp.Game.GameActivity
import com.example.quizapp.Game.ResultActivity
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.content_categories.*

class CategoriesActivity : AppCompatActivity() {

    private val START_GAME = 1
    private var chooseCategoryID: Int = 0
    private var chooseCategoryName: String? = ""
    lateinit var categories: List<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val database = DBHelper.getInstance(this)
        categories = database.allCategories

        categoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        categoriesRecyclerView.adapter = CategoriesAdapter(categories, this) { playGame(it) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == START_GAME && resultCode == Activity.RESULT_OK) {
            showResult(data!!.data.toString().toInt())
        }

    }


    private fun playGame(category: Category) {
        /* Starts new game at proper category */

        this.chooseCategoryID = category.id
        this.chooseCategoryName = category.name

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("idCategory", category.id)
        intent.putExtra("nameCategory", category.name)
        startActivityForResult(intent, START_GAME)


    }

    /* Run result activities */
    private fun showResult(correctAnswer: Int) {

        val result = Intent(this, ResultActivity::class.java)
        result.putExtra("nameCategory", chooseCategoryName)
        result.putExtra("idCategory", chooseCategoryID)
        result.putExtra("correctAnswer", correctAnswer)
        startActivity(result)

    }


}
