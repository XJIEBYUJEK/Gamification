package com.example.gamification

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var coins = BigDecimal(sharedPref?.getString("coins", "0"))
        var coefficient = BigDecimal(sharedPref?.getString("coef", "1"))

        with (sharedPref?.edit()){
            this?.putString("coins", coins.toString())
            this?.putString("coef", coefficient.toString())
            this?.apply()
        }
        coinsTV.text = "Вышкоины: $coins"
    }
}