package dev.emrizkiem.covid19.ui.detail

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.emrizkiem.covid19.AppController.Companion.context
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.ui.detail.adapter.DetailAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.swipeRefresh
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.jamshid.library.progress_bar.CircleProgressBar

@ExperimentalCoroutinesApi
class DetailActivity : AppCompatActivity() {

    private lateinit var adapter: DetailAdapter

    private val viewModel: DetailViewModel by viewModel()
    private val listDetail: MutableList<CovidDetail> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ic_back.setOnClickListener { onBackPressed() }

        swipeRefresh.setRefreshListener {
            Handler().postDelayed({
                viewModel.getDetail()
            }, 3000)
        }
        context?.let { CircleProgressBar(it) }?.let { swipeRefresh.setCustomBar(it) }

        adapter = DetailAdapter(listDetail)
        rv_detail.setHasFixedSize(true)
        rv_detail.layoutManager = LinearLayoutManager(this)
        rv_detail.adapter = adapter
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(this, Observer {
            swipeRefresh.setRefreshing(false)
        })
        viewModel.detail.observe(this, Observer {
            it.let {
                listDetail.clear()
                listDetail.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
