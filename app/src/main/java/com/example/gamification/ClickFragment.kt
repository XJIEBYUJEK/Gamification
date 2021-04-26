package com.example.gamification

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_click.*
import java.math.BigDecimal


class ClickFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_click, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        var clickCounter = BigDecimal(sharedPref?.getString("clickerScore", "0"))
        val coins = BigDecimal(sharedPref?.getString("coins", "0"))
        val coefficient = BigDecimal(sharedPref?.getString("coef", "1"))


        clickerScore.text = "$clickCounter"

        coinsTV.text = "$coins"

        clickerButton.setOnClickListener {
            clickCounter += BigDecimal("1") * coefficient
            clickerScore.text = "$clickCounter"

            with (sharedPref?.edit()){
                this?.putString("clickerScore", clickCounter.toString())
                this?.apply()
            }
        }

    }





}