package dev.emrizkiem.covid19.ui.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R

class ConfirmedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val txtConfirmed = itemView.findViewById(R.id.text_confirmed) as TextView

    fun bindContent(overview: String) {
        txtConfirmed.text = overview
    }
}