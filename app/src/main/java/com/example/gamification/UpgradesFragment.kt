package com.example.gamification

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_upgrades.*
import java.math.BigDecimal

class UpgradesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_upgrades, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var coins = BigDecimal(sharedPref?.getString("coins", "0"))
        var coefficient = BigDecimal(sharedPref?.getString("coef", "1"))
        var clickCounter = BigDecimal(sharedPref?.getString("clickerScore", "0"))

        fun saveVar(){
            with (sharedPref?.edit()){
                this?.putString("coins", coins.toString())
                this?.putString("coef", coefficient.toString())
                this?.putString("clickerScore", clickCounter.toString())
                this?.apply()
            }
        }

        fun enoughCoinsCheck(){
            coinsTV.text = "$coins"

            when {
                coins >= BigDecimal("10000") -> {
                    upgrade5B.isEnabled = true
                    upgrade4B.isEnabled = true
                    upgrade3B.isEnabled = true
                    upgrade2B.isEnabled = true
                    upgrade1B.isEnabled = true
                }
                coins >= BigDecimal("1150") -> {
                    upgrade5B.isEnabled = false
                    upgrade4B.isEnabled = true
                    upgrade3B.isEnabled = true
                    upgrade2B.isEnabled = true
                    upgrade1B.isEnabled = true
                }
                coins >= BigDecimal("400") -> {
                    upgrade5B.isEnabled = false
                    upgrade4B.isEnabled = false
                    upgrade3B.isEnabled = true
                    upgrade2B.isEnabled = true
                    upgrade1B.isEnabled = true
                }
                coins >= BigDecimal("100") -> {
                    upgrade5B.isEnabled = false
                    upgrade4B.isEnabled = false
                    upgrade3B.isEnabled = false
                    upgrade2B.isEnabled = true
                    upgrade1B.isEnabled = true
                }
                coins >= BigDecimal("10") -> {
                    upgrade5B.isEnabled = false
                    upgrade4B.isEnabled = false
                    upgrade3B.isEnabled = false
                    upgrade2B.isEnabled = false
                    upgrade1B.isEnabled = true
                }
                else -> {
                    upgrade5B.isEnabled = false
                    upgrade4B.isEnabled = false
                    upgrade3B.isEnabled = false
                    upgrade2B.isEnabled = false
                    upgrade1B.isEnabled = false
                }
            }
            when(coefficient){
                BigDecimal("1") -> {
                    upgrade4B.text = "${getString(R.string.blocked)}"
                    upgrade5B.text = "${getString(R.string.blocked)}"
                    upgrade5B.isEnabled = false
                    upgrade4B.isEnabled = false
                }
                BigDecimal("2") -> {
                    upgrade3B.text = "${getString(R.string.activated)}"
                    upgrade4B.text = "${getString(R.string.buy)}"
                    upgrade5B.text = "${getString(R.string.blocked)}"
                    upgrade3B.isEnabled = false
                    upgrade5B.isEnabled = false
                }
                BigDecimal("8") -> {
                    upgrade3B.text = "${getString(R.string.activated)}"
                    upgrade4B.text = "${getString(R.string.activated)}"
                    upgrade5B.text = "${getString(R.string.buy)}"
                    upgrade4B.isEnabled = false
                    upgrade3B.isEnabled = false
                }
                BigDecimal("32") -> {
                    upgrade3B.text = "${getString(R.string.activated)}"
                    upgrade4B.text = "${getString(R.string.activated)}"
                    upgrade5B.text = "${getString(R.string.activated)}"
                    upgrade5B.isEnabled = false
                    upgrade4B.isEnabled = false
                    upgrade3B.isEnabled = false

                }
            }


        }
        enoughCoinsCheck()

        upgrade1B.setOnClickListener {
            coins -= BigDecimal("10")
            clickCounter += BigDecimal("1000") * coefficient
            saveVar()
            enoughCoinsCheck()
        }
        upgrade2B.setOnClickListener {
            coins -= BigDecimal("100")
            clickCounter += BigDecimal("15000") * coefficient
            saveVar()
            enoughCoinsCheck()
        }
        upgrade3B.setOnClickListener {
            coins -= BigDecimal("400")
            coefficient = BigDecimal("2")
            saveVar()
            enoughCoinsCheck()
        }
        upgrade4B.setOnClickListener {
            coins -= BigDecimal("1150")
            coefficient = BigDecimal("8")
            saveVar()
            enoughCoinsCheck()
        }
        upgrade5B.setOnClickListener {
            coins -= BigDecimal("10000")
            coefficient = BigDecimal("32")
            saveVar()
            enoughCoinsCheck()
        }




    }
}