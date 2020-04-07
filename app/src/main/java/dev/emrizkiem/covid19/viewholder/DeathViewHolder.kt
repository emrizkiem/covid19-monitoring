package dev.emrizkiem.covid19.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.CovidOverview

class DeathViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val txtDeath = itemView.findViewById(R.id.text_death) as TextView

    fun bindContent(overview: CovidOverview) {
        txtDeath.text = overview.deaths.toString()
    }
}