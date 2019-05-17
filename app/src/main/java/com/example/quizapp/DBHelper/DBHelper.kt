package com.example.quizapp.DBHelper

import android.content.Context
import com.example.quizapp.DBModel.Category
import com.example.quizapp.DBModel.Question
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DBHelper (context : Context): SQLiteAssetHelper(context,DB_NAME,null,DB_VER){

    companion object{
        private var instance : DBHelper ?= null
        private  val DB_NAME = "QuizDB.db";
        private val DB_VER = 1;

        @Synchronized
        fun getInstance(context: Context):DBHelper{
            if (instance == null)
                instance = DBHelper(context)
            return instance!!
        }
    }

    //getting all categories
    val  allCategories : MutableList<Category>
    get() {
        val db = instance!!.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM Category",null)
        val  categories  = ArrayList<Category>()
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast){
                val category = Category(cursor.getInt(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(cursor.getColumnIndex("Image")))
                categories.add(category)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return categories
    }

    // GEt all Question in category
    fun getQuestionBycategory(categoryId:Int):MutableList<Question>{
        val db = instance!!.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM Question WHERE categoryID = $categoryId ORDER BY RANDOM() LIMIT 30",null)
        val  questionList  = ArrayList<Question>()

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast){
                val question = Question(cursor.getInt(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("QuestionText")),
                    cursor.getString(cursor.getColumnIndex("QuestionImage")),
                    cursor.getString(cursor.getColumnIndex("AnswerA")),
                    cursor.getString(cursor.getColumnIndex("AnswerB")),
                    cursor.getString(cursor.getColumnIndex("AnswerC")),
                    cursor.getString(cursor.getColumnIndex("AnswerD")),
                    cursor.getString(cursor.getColumnIndex("CorrectAnswer")),
                    if (cursor.getInt(cursor.getColumnIndex("IsImageQuestion"))== 0 ) java.lang.Boolean.FALSE else java.lang.Boolean.TRUE,
                    cursor.getInt(cursor.getColumnIndex("CategoryID"))
                    )
                questionList.add(question)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return questionList
    }
}