package com.example.quizapp.DBModel

class Question(
    var  id : Int,
    var questiontext : String?,
    var questionImage : String?,
    var answerA:String?,
    var answerB:String?,
    var answerC:String?,
    var answerD:String?,
    var correctAnswer:String?,
    var isImagequestion:Boolean,
    var categoryId:Int
    )