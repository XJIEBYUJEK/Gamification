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
import java.util.ArrayList

class QuestionsActivity : AppCompatActivity() {
    private var questionsModalArrayList: ArrayList<QuestionsModal>? = null
    private var questionsAdapter: QuestionsAdapter? = null
    private var questionsRV: RecyclerView? = null
    private var loadingPB: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        questionsModalArrayList = ArrayList()

        questionsRV = findViewById(R.id.idRVQuestions)
        loadingPB = findViewById(R.id.idPBLoading)

        dataFromAPI
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
                        for (i in 0 until entryArray.length()) {
                            val entryObj = entryArray.getJSONObject(i)

                            val subject = entryObj.getJSONObject("gsx\$subject").getString("\$t")
                            questionsModalArrayList!!.add(QuestionsModal(subject))
                            questionsAdapter = QuestionsAdapter(questionsModalArrayList!!, this@QuestionsActivity)
                            questionsRV!!.layoutManager = LinearLayoutManager(this@QuestionsActivity)
                            questionsRV!!.adapter = questionsAdapter

                        }
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