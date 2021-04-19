package com.example.gamification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("wrongAns", -1 )
        intent.putExtra("difficulty", "null")
        startActivity(intent)
        finish()
    }
}