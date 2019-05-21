package com.example.quizapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizapp.DBModel.Category
import kotlinx.android.synthetic.main.category_item_layout.view.*


class CategoriesAdapter(private val data : List<Category>, val conttext : Context, val listener: (Category) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_layout, parent, false)

        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categoryItem = data[position]
        holder.itemView.categoryName.text = categoryItem.name

        val id = conttext.resources.getIdentifier(categoryItem.image, "drawable", conttext.packageName)
        holder.itemView.categoryImage.setImageResource(id)
        holder.itemView.setOnClickListener { listener(categoryItem) }
    }

}