package dev.emrizkiem.covid19.ui.home

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.util.Number

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        rv_statistic.hasFixedSize()
//        rv_statistic.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
//        rv_statistic.adapter = homeAdapter
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
        private const val TEXT_ANIMATION_DURATION = 1000L
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 75f
    }

}
