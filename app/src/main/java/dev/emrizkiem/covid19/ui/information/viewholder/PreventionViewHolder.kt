package dev.emrizkiem.covid19.ui.information.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.info.Prevention

class PreventionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val itemIcon = itemView.findViewById(R.id.icon_prevention) as ImageView
    private val itemTitle = itemView.findViewById(R.id.text_title_prevention) as TextView
    private val itemDesc = itemView.findViewById(R.id.text_description_prevention) as TextView

    fun bind(item: Prevention) {
        itemTitle.text = item.title
        itemDesc.text = item.description

        Glide.with(itemView.context)
            .load(item.image)
            .into(itemIcon)
    }
}