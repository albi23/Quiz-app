package com.example.quizapp.Stats

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizapp.R
import kotlinx.android.synthetic.main.chart_item.view.*
import lecho.lib.hellocharts.model.PieChartData

class ChartAdapter(private val data : List<PieChartData>) :
    RecyclerView.Adapter<ChartAdapter.ChartViewHolder>() {

    class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chart_item, parent, false)

        return ChartViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        /* Get view and set them parameters*/
        holder.itemView.chart.pieChartData = data[position]
    }

}