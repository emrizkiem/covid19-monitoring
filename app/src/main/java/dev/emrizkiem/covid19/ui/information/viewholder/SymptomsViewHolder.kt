package dev.emrizkiem.covid19.ui.information.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.info.Symptoms

class SymptomsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val itemIcon = itemView.findViewById(R.id.icon_symptoms) as ImageView
    private val itemName = itemView.findViewById(R.id.text_symptoms) as TextView

    fun bind(item: Symptoms) {
        itemName.text = item.name

        Glide.with(itemView.context)
            .load(item.icon)
            .into(itemIcon)
    }
}