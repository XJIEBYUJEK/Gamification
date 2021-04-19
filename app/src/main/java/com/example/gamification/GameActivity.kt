package com.example.gamification

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import java.math.BigDecimal

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val bundle = intent.extras
        val difficultyFlag = bundle!!.getString("difficulty")
        val wrongAnswers = bundle.getInt("wrongAns")
        var coins = BigDecimal(sharedPref?.getString("coins", "0"))
        val coefficient = BigDecimal(sharedPref?.getString("coef", "1"))

        when(difficultyFlag){
            "easy" -> when(wrongAnswers){
                0 -> {
                    coins += BigDecimal("1") * coefficient
                }
                1 -> {
                    coins += BigDecimal("0")
                }
                2 -> {
                    coins += BigDecimal("-1")
                }
                3 -> {
                    coins += BigDecimal("-2")
                }
            }
            "medium" -> when(wrongAnswers){
                0 -> {
                    coins += BigDecimal("2") * coefficient
                }
                1 -> {
                    coins += BigDecimal("0")
                }
                2 -> {
                    coins += BigDecimal("-2")
                }
                3 -> {
                    coins += BigDecimal("-3")
                }
            }
            "hard" -> when(wrongAnswers){
                0 -> {
                    coins += BigDecimal("3") * coefficient
                }
                1 -> {
                    coins += BigDecimal("0")
                }
                2 -> {
                    coins += BigDecimal("-3")
                }
                3 -> {
                    coins += BigDecimal("-4")
                }
            }
        }

        with (sharedPref?.edit()){
            this?.putString("coins", coins.toString())
            this?.apply()
        }

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