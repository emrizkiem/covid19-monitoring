package dev.emrizkiem.covid19.ui.information.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.info.Prevention
import dev.emrizkiem.covid19.data.model.info.Symptoms
import dev.emrizkiem.covid19.ui.information.viewholder.PreventionViewHolder
import dev.emrizkiem.covid19.ui.information.viewholder.SymptomsViewHolder
import kotlinx.android.synthetic.main.item_symptoms.view.*
import java.lang.IllegalArgumentException

class InformationAdapter(
    private val data: List<Any>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is Symptoms -> ITEM_SYMPTOMS
            is Prevention -> ITEM_PREVENTION
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_SYMPTOMS -> SymptomsViewHolder(parent.inflate(R.layout.item_symptoms))
            ITEM_PREVENTION -> PreventionViewHolder(parent.inflate(R.layout.item_prevention))
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_SYMPTOMS -> {
                val symptomsViewHolder = holder as SymptomsViewHolder
                symptomsViewHolder.bind(data[position] as Symptoms)
            }
            ITEM_PREVENTION -> {
                val preventionViewHolder = holder as PreventionViewHolder
                preventionViewHolder.bind(data[position] as Prevention)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    companion object {
        private const val ITEM_SYMPTOMS = 0
        private const val ITEM_PREVENTION = 1
    }
}

fun ViewGroup.inflate(resource: Int) = LayoutInflater.from(context).inflate(resource, this, false) as View
