package dev.emrizkiem.covid19.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.util.CaseType
import dev.emrizkiem.covid19.util.Number
import kotlinx.android.synthetic.main.item_detail.view.*

class DetailAdapter(
    private val data: List<CovidDetail>
): RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: CovidDetail) {
            with(itemView) {
                text_province.text = item.province
                text_confirmed_detail.text = item.confirmed.toString()
                text_deaths_detail.text = item.death.toString()
                text_recovered_detail.text = item.recovered.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}