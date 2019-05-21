package com.example.quizapp.Stats

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizapp.R
import kotlinx.android.synthetic.main.chart_item.view.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue

class ChartAdapter(private val data : List<PieChartData>, val listener: (PieChartData) -> Unit) :
    RecyclerView.Adapter<ChartAdapter.ChartViewHolder>() {

    class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chart_item, parent, false)

        return ChartViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        /* Get viev and set them parameters*/
        holder.itemView.chart.pieChartData = data[position]
    }

}