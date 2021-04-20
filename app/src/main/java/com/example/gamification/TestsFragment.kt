package com.example.gamification

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.fragment_tests.*


class TestsFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tests, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        easy.setOnClickListener {
            val intent = Intent(activity, QuestionsActivity::class.java)
            intent.putExtra("difficulty", "easy")
            startActivity(intent)
        }
        medium.setOnClickListener {
            val intent = Intent(activity, QuestionsActivity::class.java)
            intent.putExtra("difficulty", "medium")
            startActivity(intent)
        }
        hard.setOnClickListener {
            val intent = Intent(activity, QuestionsActivity::class.java)
            intent.putExtra("difficulty", "hard")
            startActivity(intent)
        }
    }
}