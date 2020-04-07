package dev.emrizkiem.covid19.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.CovidOverview

class ConfirmedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val txtConfirmed = itemView.findViewById(R.id.text_confirmed) as TextView

    fun bindContent(overview: CovidOverview) {
        txtConfirmed.text = overview.confirmed.toString()
    }
}