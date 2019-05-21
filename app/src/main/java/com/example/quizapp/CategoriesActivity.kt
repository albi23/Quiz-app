package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.DBModel.Category
import com.example.quizapp.Game.GameActivity
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
        categoriesRecyclerView.adapter = CategoriesAdapter(categories,this) { playGame(it) }
    }

    private fun playGame(category: Category) {
        /* Starts new game at proper category */

        val intent = Intent(this,GameActivity::class.java)
        intent.putExtra("idCategory",category.id)
        intent.putExtra("nameCategory",category.name)
        startActivity(intent)

    }
}
