package dev.emrizkiem.covid19.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.util.CaseType
import dev.emrizkiem.covid19.util.Number
import kotlinx.android.synthetic.main.item_location.view.*

class DetailAdapter(
    private val caseType: Int,
    private val listener: (data: CovidDetail) -> Unit
): RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private val items = mutableListOf<CovidDetail>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: CovidDetail) {
            with(itemView) {
                text_location.text = item.locationName
                text_data.text = when(caseType) {
                    CaseType.RECOVERED -> "Recovered ${Number.numberFormat(item.recovered)}"
                    CaseType.DEATHS -> "Deaths ${Number.numberFormat(item.deaths)}"
                    else -> "Confirmed ${Number.numberFormat(item.confirmed)}"
                }
                text_data.setTextColor(resources.getColor(getColorText(caseType)))
                text_information.text = "Last update ${Number.formatTime(item.lastUpdate)}"

                setOnClickListener { listener.invoke(item) }
            }
        }

    }

    private fun getColorText(status: Int) = when (status) {
        CaseType.RECOVERED -> R.color.textRecovered
        CaseType.DEATHS -> R.color.textDeath
        else -> R.color.textConfirmed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }
}