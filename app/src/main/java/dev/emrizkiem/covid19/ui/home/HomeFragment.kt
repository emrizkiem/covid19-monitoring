package dev.emrizkiem.covid19.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.home.CovidOverview
import dev.emrizkiem.covid19.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val list: ArrayList<CovidOverview> = ArrayList()

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
//            layout_confirmed.setOnClickListener {
//                activity?.let {
//                    val intent = Intent(it, DetailActivity::class.java)
//                    it.startActivity(intent)
//                }
//            }

//            observeViewModel()
        }
    }

//    @SuppressLint("FragmentLiveDataObserve")
//    private fun observeViewModel() {
//        homeViewModel.state.observe(this, Observer {
//
//        })
//        homeViewModel.confirmed.observe(this, Observer {
//            it?.let { renderConfirmed(it) }
//        })
//        homeViewModel.recovered.observe(this, Observer {
//            it?.let { renderRecovered(it) }
//        })
//        homeViewModel.death.observe(this, Observer {
//            it?.let { renderDeath(it) }
//        })
//        homeViewModel.error.observe(this, Observer {
//            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//        })
//    }

//    private fun renderConfirmed(overview: CovidOverview) {
//        text_confirmed.text = overview.value.toString()
//    }
//
//    private fun renderRecovered(overview: CovidOverview) {
//        text_recovered.text = overview.value.toString()
//    }
//
//    private fun renderDeath(overview: CovidOverview) {
//        text_death.text = overview.value.toString()
//    }

//    companion object {
//        const val CONFIRMED = 0
//        const val RECOVERED = 1
//        const val DEATH = 2
//    }

}
