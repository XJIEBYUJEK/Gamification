package com.example.gamification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class QuestionsAdapter ( private val questionsModalArrayList: ArrayList<QuestionsModal>, private val context: Context
) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.questions_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val questionsModal = questionsModalArrayList[position]
        holder.subjectTV.text = questionsModal.subject
    }
    override fun getItemCount(): Int {
        return questionsModalArrayList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val subjectTV: TextView = itemView.findViewById(R.id.subject)
    }

}