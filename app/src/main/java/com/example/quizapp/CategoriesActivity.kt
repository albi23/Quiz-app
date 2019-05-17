package com.example.quizapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.DBModel.Category
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.content_categories.*

class CategoriesActivity : AppCompatActivity() {

    lateinit var categories : List<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val database = DBHelper.getInstance(this)
        categories = database.allCategories

        categoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        categoriesRecyclerView.adapter = CategoriesAdapter(categories) { playGame(it) }
    }

    private fun playGame(category: Category) {
        /* Starts new game at proper category */
        Log.d("newGame", "new game start")
    }
}
