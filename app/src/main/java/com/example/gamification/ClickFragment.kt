package com.example.gamification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_click.*
import java.math.BigDecimal



class ClickFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_click, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var clickCounter = BigDecimal("0")

        clickerButton.setOnClickListener {
            clickCounter += BigDecimal("1")
            clickerScore.text = "$clickCounter"
        }



        Log.wtf("ok", "okkk")
    }


}