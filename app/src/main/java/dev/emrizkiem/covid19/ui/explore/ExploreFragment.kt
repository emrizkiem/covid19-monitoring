package dev.emrizkiem.covid19.ui.explore

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.explore.ArticlesItem
import dev.emrizkiem.covid19.ui.explore.adapter.ExploreAdapter
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.jamshid.library.progress_bar.CircleProgressBar

class ExploreFragment : Fragment() {

    private lateinit var adapter: ExploreAdapter

    private val exploreViewModel: ExploreViewModel by viewModel()
    private val listExplore: MutableList<ArticlesItem> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            adapter = ExploreAdapter(listExplore) {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(it.url))
            }
            swipeRefresh.setRefreshListener {
                Handler().postDelayed({
                    exploreViewModel.getExplore()
                }, 3000)
            }
            context?.let { CircleProgressBar(it) }?.let { swipeRefresh.setCustomBar(it) }

            rv_explore.setHasFixedSize(true)
            rv_explore.layoutManager = LinearLayoutManager(context)
            rv_explore.adapter = adapter
            observeViewModel()
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observeViewModel() {
        exploreViewModel.state.observe(this, Observer {
            swipeRefresh.setRefreshing(it)
        })
        exploreViewModel.explore.observe(this, Observer {
            it?.let {
                listExplore.clear()
                listExplore.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
        exploreViewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}
