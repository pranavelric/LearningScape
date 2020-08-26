package com.quiz.learningscape.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

import com.quiz.learningscape.R
import com.quiz.learningscape.activity.SetsActivity
import com.quiz.learningscape.activity.StudyActivity
import kotlinx.android.synthetic.main.categories_item.view.*
class CustomAdapterCategories(
    private var context: Context,
    private var title: MutableList<String>,
    private var htmlFile: MutableList<String>?
) : RecyclerView.Adapter<CustomAdapterCategories.CustomViewHolder>() {

    var firestore: FirebaseFirestore? = null

    constructor(
        context: Context,
        title: MutableList<String>
    ) : this(context, title, null)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        firestore = FirebaseFirestore.getInstance()
        return CustomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return title.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        if (htmlFile != null && htmlFile!!.size > 0) {
            val html: String = htmlFile!![position]

            holder.setCategoryName(title[position])
            holder.setImg("https://firebasestorage.googleapis.com/v0/b/fir-uipractice-fc87a.appspot.com/o/neon-retro-computers-by-lorenzo-herrera-wallpaper.jpg?alt=media&token=4c048981-84bf-4965-9397-d38da1aa7352" ,context)
            holder.layout.setOnClickListener {
                val i = Intent(context, StudyActivity::class.java)
                i.putExtra("file", html)
                context.startActivity(i)
                if (context is Activity) {
                    (context as Activity).overridePendingTransition(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    );

                }


            }
        } else {

            holder.setCategoryName(title[position])

            holder.layout.setOnClickListener {
                val i = Intent(context, SetsActivity::class.java)
                i.putExtra("category", title[position])
                i.putExtra("catId", "${position + 1}")
                context.startActivity(i)
                if (context is Activity) {
                    (context as Activity).overridePendingTransition(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    );

                }
            }

            firestore?.collection("Quiz")?.document("Cat${position + 1}")?.get()
                ?.addOnSuccessListener {
                    holder.setImg(it.get("cover").toString(), context)
                }


        }

    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var layout = itemView.categories_item_layout


        fun setImg(url: String, context: Context) {
            Glide.with(context).load(url).thumbnail(0.001f).into(itemView.categories_cover)
        }

        fun setCategoryName(name: String) {
            itemView.category_name.text = name
        }

    }

}
