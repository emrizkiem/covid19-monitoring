package dev.emrizkiem.covid19.ui.home.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.ui.home.viewholder.ConfirmedViewHolder
import dev.emrizkiem.covid19.ui.home.viewholder.DeathViewHolder
import dev.emrizkiem.covid19.ui.home.viewholder.RecoveredViewHolder
import java.lang.IllegalArgumentException

class HomeAdapter(private val data: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_CONFIRMED = 0
        private const val ITEM_DEATH = 1
        private const val ITEM_RECOVERED = 2
    }

    override fun getItemViewType(position: Int): Int {
        //@Suppress("DUPLICATE_LABEL_IN_WHEN")
        return when(data[position]) {
            is String -> ITEM_CONFIRMED
            is String -> ITEM_DEATH
            is String -> ITEM_RECOVERED
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_CONFIRMED -> ConfirmedViewHolder(parent.inflate(R.layout.item_confirmed))
            ITEM_DEATH -> DeathViewHolder(parent.inflate(R.layout.item_death))
            ITEM_RECOVERED -> RecoveredViewHolder(parent.inflate(R.layout.item_recovered))
            else -> throw throw throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_CONFIRMED -> {
                val confirmedHolder = holder as ConfirmedViewHolder
                confirmedHolder.bindContent(data[position] as String)
            }
            ITEM_DEATH -> {
                val deathHolder = holder as DeathViewHolder
                deathHolder.bindContent(data[position] as String)
            }
            ITEM_RECOVERED -> {
                val recoveredHolder = holder as RecoveredViewHolder
                recoveredHolder.bindContent(data[position] as String)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }
}

private fun ViewGroup.inflate(resource: Int) = LayoutInflater.from(context).inflate(resource, this, false) as View

