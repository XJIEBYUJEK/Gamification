package com.example.gamification

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

class QuestionsActivity : AppCompatActivity() {
    private var questionsModalArrayList: ArrayList<QuestionsModal>? = null
    private var questionDataArrayList: ArrayList<QuestionData>? = null
    private var questionsAdapter: QuestionsAdapter? = null
    private var loadingPB: ProgressBar? = null
    private var questionsRV: RecyclerView? = null
    private var difficultyFlag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        val bundle = intent.extras
        difficultyFlag = bundle!!.getString("difficulty")

        questionsModalArrayList = ArrayList()
        questionDataArrayList = ArrayList()

        questionsRV = findViewById(R.id.idRVQuestions)
        loadingPB = findViewById(R.id.idPBLoading)

        dataFromAPI


        questionsRV!!.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                questionsRV,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        Toast.makeText(
                            applicationContext,
                            questionsModalArrayList!![position].subject, Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
                    }

                })
        )
    }

    private val dataFromAPI: Unit
        get() {

            // creating a string variable for URL.
            val urlId = "1AnU6HSjjLYWRV42X7SuDbn2UbgmjAfMuQAtLZ4xOCwU"
            val url = "https://spreadsheets.google.com/feeds/list/$urlId/od6/public/values?alt=json"


            // creating a new variable for our request queue
            val queue = Volley.newRequestQueue(this@QuestionsActivity)

            // creating a variable for our JSON object request and passing our URL to it.
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    loadingPB!!.visibility = View.GONE
                    try {
                        val feedObj = response.getJSONObject("feed")
                        val entryArray = feedObj.getJSONArray("entry")
                        //var listOfSubjects = listOf<String>()
                        val map = mutableMapOf<String, Int>()
                        for (i in 0 until entryArray.length()) {
                            val entryObj = entryArray.getJSONObject(i)

                            val difficulty =
                                entryObj.getJSONObject("gsx\$difficulty").getString("\$t") ?: "null"
                            if (difficulty == difficultyFlag){
                                val subject = entryObj.getJSONObject("gsx\$subject").getString("\$t") ?: "null"
                                //val tempList = listOf(subject)
                                //listOfSubjects = listOfSubjects.union(tempList).toList()
                                map += subject to map.getOrDefault(subject, 0) + 1
                                val question = entryObj.getJSONObject("gsx\$question").getString("\$t") ?: "null"
                                val answer = entryObj.getJSONObject("gsx\$answer").getString("\$t") ?: "null"
                                val answer2 = entryObj.getJSONObject("gsx\$answer2").getString("\$t") ?: "null"
                                val answer3 = entryObj.getJSONObject("gsx\$answer3").getString("\$t") ?: "null"
                                val answer4 = entryObj.getJSONObject("gsx\$answer4").getString("\$t") ?: "null"
                                questionDataArrayList!!.add(QuestionData(subject, difficulty, question, answer, answer2, answer3, answer4))
                            }

                        }
                        //listOfSubjects.forEach { questionsModalArrayList!!.add(QuestionsModal(it)) }
                        map.forEach { if (it.value >= 3) questionsModalArrayList!!.add(QuestionsModal(it.key)) }
                        questionsAdapter = QuestionsAdapter(
                            questionsModalArrayList!!,
                            this@QuestionsActivity
                        )
                        questionsRV!!.layoutManager =
                            LinearLayoutManager(this@QuestionsActivity)
                        questionsRV!!.adapter = questionsAdapter
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            ) { // handline on error listner method.
                Toast.makeText(this, "Fail to get data..", Toast.LENGTH_SHORT).show()
            }
            // calling a request queue method
            // and passing our json object
            queue.add(jsonObjectRequest)
        }

}