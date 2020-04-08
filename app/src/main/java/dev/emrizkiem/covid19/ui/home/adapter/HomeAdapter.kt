package dev.emrizkiem.covid19.ui.home.adapter

import android.animation.ValueAnimator
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.ui.home.HomeFragment
import dev.emrizkiem.covid19.ui.home.viewholder.ConfirmedViewHolder
import dev.emrizkiem.covid19.ui.home.viewholder.DeathViewHolder
import dev.emrizkiem.covid19.ui.home.viewholder.RecoveredViewHolder
import dev.emrizkiem.covid19.util.Number
import java.lang.IllegalArgumentException

class HomeAdapter(private val data: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        @Suppress("DUPLICATE_LABEL_IN_WHEN")
        when(data[position]) {
            is String -> ITEM_CONFIRMED
            is String -> ITEM_DEATH
            is String -> ITEM_RECOVERED
            else -> throw IllegalArgumentException("Undefined view type")
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ITEM_CONFIRMED -> ConfirmedViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_confirmed, parent, false))
            ITEM_DEATH -> DeathViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_death, parent, false))
            ITEM_RECOVERED -> RecoveredViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recovered, parent, false))
            else -> throw throw throw IllegalArgumentException("Undefined view type")
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
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

    private fun startNumberChangeAnimator(finalValue: Int?, view: TextView) {
        val initValue = Number.extractDigit(view.text.toString())
        val valueAnimator = ValueAnimator.ofInt(initValue, finalValue ?: 0)
        valueAnimator.duration = TEXT_ANIMATION_DURATION
        valueAnimator.addUpdateListener { value ->
            view.text = Number.numberFormat(value.animatedValue.toString().toInt())
        }
        valueAnimator.start()
    }

    companion object {
        private const val ITEM_CONFIRMED = 1
        private const val ITEM_DEATH = 2
        private const val ITEM_RECOVERED = 3

        private const val TEXT_ANIMATION_DURATION = 1000L
    }
}
