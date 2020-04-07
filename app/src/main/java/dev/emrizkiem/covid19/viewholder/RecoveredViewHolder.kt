package dev.emrizkiem.covid19.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.CovidOverview

class RecoveredViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val txtRecovered = itemView.findViewById(R.id.text_recovered) as TextView

    fun bindContent(overview: CovidOverview) {
        txtRecovered.text = overview.recovered.toString()
    }
}