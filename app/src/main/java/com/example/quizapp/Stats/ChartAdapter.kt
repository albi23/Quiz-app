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

        //Remember old values
        val firstValue = data[position].values[0].value
        val secondValue = data[position].values[1].value

        //Change only for watch animation
        data[position].values[0].value = 1f
        data[position].values[1].value = 1f

        //restore correct value
        holder.itemView.chart.pieChartData = data[position]
        data[position].values[0].setTarget(firstValue)
        data[position].values[1].setTarget(secondValue)

        holder.itemView.chart.startDataAnimation(3000)


    }

}