package com.quiz.learningscape.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.quiz.learningscape.R
import com.quiz.learningscape.activity.Quiz1
import kotlinx.android.synthetic.main.set_item.view.*

class SetsAdapter(var numOfSet: Int, var context: Context) :
    RecyclerView.Adapter<SetsAdapter.SetsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.set_item, parent, false)
        return SetsViewHolder(v)

    }

    override fun getItemCount(): Int {
        return numOfSet
    }

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) {

        holder.setSetText("${position + 1}")

        holder.cardLay.setOnClickListener {
            val i = Intent(context, Quiz1::class.java)
            i.putExtra("setNo", position + 1)
            context.startActivity(i)

        }

    }

    class SetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardLay: MaterialCardView = itemView.set_item_card

        fun setSetText(name: String) {
            itemView.set_item_num.text = name
        }


    }
}