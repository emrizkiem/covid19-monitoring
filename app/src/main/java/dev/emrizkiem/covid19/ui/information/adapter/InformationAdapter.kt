package dev.emrizkiem.covid19.ui.information.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.info.Symptoms
import kotlinx.android.synthetic.main.item_symptoms.view.*

class InformationAdapter(
    private val data: List<Symptoms>
): RecyclerView.Adapter<InformationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_symptoms, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Symptoms) {
            with(itemView) {
                text_symptoms.text = item.name

                Glide.with(itemView.context)
                    .load(item.icon)
                    .into(icon_symptoms)
            }
        }
    }
}