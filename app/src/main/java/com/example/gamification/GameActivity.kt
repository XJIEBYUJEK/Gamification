package com.example.gamification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(
                R.id.frameLayoutGame,
                ClickFragment()
            ).commit()
        }

        testsButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayoutGame,
                TestsFragment()
            ).commit()
        }
        gameButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
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

    }
}