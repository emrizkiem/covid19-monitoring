package dev.emrizkiem.covid19.ui.home

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.home.CovidOverviewResponse
import dev.emrizkiem.covid19.util.Number
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val listOverview: MutableList<CovidOverviewResponse> = mutableListOf()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
//            observeViewModel()
        }
    }

//    private fun observeViewModel() {
//        homeViewModel.state.observe(this, Observer {
//
//        })
//        homeViewModel.overview.observe(this, Observer {
//            it.let {
//
//            }
//        })
//        homeViewModel.error.observe(this, Observer {
//            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//        })
//    }

    private fun startNumberChangeAnimator(finalValue: Int?, view: TextView) {
        val initValue = Number.extractDigit(view.text.toString())
        val valueAnimator = ValueAnimator.ofInt(initValue, finalValue ?: 0)
        valueAnimator.duration = TEXT_ANIMATION_DURATION
        valueAnimator.addUpdateListener { value ->
            view.text = Number.numberFormat(value.animatedValue.toString().toInt())
        }
        valueAnimator.start()
    }

//    private fun renderDashboard(overview: CovidOverviewResponse) {
//        renderDashboardValue(
//            overview.confirmed?.value.toString()
//        )
//    }
//
//    private fun renderDashboardValue(vararg value: String) {
//        text_confirmed.text = value[CONFIRMED_INDEX]
//    }

    companion object {
        private const val TEXT_ANIMATION_DURATION = 1000L
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 75f

        const val CONFIRMED_INDEX = 0
        const val RECOVERED_INDEX = 1
        const val DEATH_INDEX = 2
    }

}
