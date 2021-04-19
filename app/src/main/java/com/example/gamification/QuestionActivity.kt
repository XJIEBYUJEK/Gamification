package com.example.gamification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_question.*
import kotlin.random.Random

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        val bundle = intent.extras
        val questionArrayList = bundle!!.getStringArrayList("question")
        val answerArrayList = bundle.getStringArrayList("answer")
        val answer2ArrayList = bundle.getStringArrayList("answer2")
        val answer3ArrayList = bundle.getStringArrayList("answer3")
        val answer4ArrayList = bundle.getStringArrayList("answer4")

        var wrongAnswers = 0
        var numberOfQuestion = 0

        fun beforeButtonPressed(numberOfQuestion: Int) : Int{
            questionText.text = questionArrayList?.get(numberOfQuestion) ?: "null"
            val rightAnswerFlag = Random.nextInt(4)
            val questionAnswersList =
                listOf(
                    answerArrayList?.get(numberOfQuestion) ?: "null",
                    answer2ArrayList?.get(numberOfQuestion) ?: "null",
                    answer3ArrayList?.get(numberOfQuestion) ?: "null",
                    answer4ArrayList?.get(numberOfQuestion) ?: "null")
            answer1.text = questionAnswersList[(0 + rightAnswerFlag) % 4]
            answer2.text = questionAnswersList[(1 + rightAnswerFlag) % 4]
            answer3.text = questionAnswersList[(2 + rightAnswerFlag) % 4]
            answer4.text = questionAnswersList[(3 + rightAnswerFlag) % 4]
            return rightAnswerFlag
        }

        var rightAnswerFlag = beforeButtonPressed(numberOfQuestion)

        fun buttonPressed(numberOfButton: Int){


            if(0 == (numberOfButton + rightAnswerFlag) % 4){
                Toast.makeText(
                    applicationContext,
                    "Верно!", Toast.LENGTH_SHORT
                ).show()
            }
            else{
                Toast.makeText(
                    applicationContext,
                    "Неерно :(", Toast.LENGTH_SHORT
                ).show()
                wrongAnswers += 1
            }
            numberOfQuestion += 1

        }

        fun results(){
            Toast.makeText(
                applicationContext,
                "Вы совершили $wrongAnswers ошибок", Toast.LENGTH_SHORT
            ).show()
            startActivity(
                Intent(this, GameActivity::class.java)
            )
            finish()
        }



        answer1.setOnClickListener {
            buttonPressed(0)
            if (numberOfQuestion < 3){
                rightAnswerFlag = beforeButtonPressed(numberOfQuestion)
            }
            else{
                results()
            }
        }
        answer2.setOnClickListener {
            buttonPressed(1)
            if (numberOfQuestion < 3){
                rightAnswerFlag = beforeButtonPressed(numberOfQuestion)
            }
            else{
                results()
            }
        }
        answer3.setOnClickListener {
            buttonPressed(2)
            if (numberOfQuestion < 3){
                rightAnswerFlag = beforeButtonPressed(numberOfQuestion)
            }
            else{
                results()
            }
        }
        answer4.setOnClickListener {
            buttonPressed(3)
            if (numberOfQuestion < 3){
                rightAnswerFlag = beforeButtonPressed(numberOfQuestion)
            }
            else{
                results()
            }
        }






    }



}