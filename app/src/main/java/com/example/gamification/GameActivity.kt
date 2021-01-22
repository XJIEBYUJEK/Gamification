package com.example.gamification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val upgradesButton = findViewById<Button>(R.id.upgradesButton)
        val gameButton = findViewById<Button>(R.id.gameButton)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(
                R.id.frameLayoutGame,
                ClickFragment()
            ).commit()
        }

        upgradesButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayoutGame,
                UpgradesFragment()
            ).commit()
        }
        gameButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayoutGame,
                ClickFragment()
            ).commit()
        }

    }
}