package dev.emrizkiem.covid19.ui.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R

class RecoveredViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val txtRecovered = itemView.findViewById(R.id.text_recovered) as TextView

    fun bindContent(overview: String) {
        txtRecovered.text = overview
    }
}