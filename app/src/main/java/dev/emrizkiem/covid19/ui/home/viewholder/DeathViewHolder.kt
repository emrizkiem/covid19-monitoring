package dev.emrizkiem.covid19.ui.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R

class DeathViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val txtDeath = itemView.findViewById(R.id.text_death) as TextView

    fun bindContent(overview: String) {
        txtDeath.text = overview
    }
}