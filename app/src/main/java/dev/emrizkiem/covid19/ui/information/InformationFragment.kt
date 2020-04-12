package dev.emrizkiem.covid19.ui.information

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.info.Symptoms
import dev.emrizkiem.covid19.ui.information.adapter.InformationAdapter
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_information.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InformationFragment : Fragment() {

    private lateinit var adapter: InformationAdapter

    private val viewModel: InformationViewModel by viewModel()
    private val listSymptoms: MutableList<Symptoms> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            adapter = InformationAdapter(listSymptoms)
            rv_symptoms.setHasFixedSize(true)
            rv_symptoms.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rv_symptoms.adapter = adapter
            observeViewModel()
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observeViewModel() {
        viewModel.state.observe(this, Observer {

        })
        viewModel.symptoms.observe(this, Observer {
            it.let {
                listSymptoms.clear()
                listSymptoms.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}
