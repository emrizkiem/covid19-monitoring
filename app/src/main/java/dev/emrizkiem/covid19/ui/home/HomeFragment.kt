package dev.emrizkiem.covid19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.util.CustomSpinnerAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listItemText = arrayOf("Indonesia", "Italy", "Singapore", "Malaysia", "Spain", "Japan")

        var spinnerAdapter: CustomSpinnerAdapter = CustomSpinnerAdapter(context!!, listItemText)
        var spinner: Spinner = view.findViewById(R.id.spinner) as Spinner
        spinner.adapter = spinnerAdapter


    }

private operator fun AdapterView.OnItemSelectedListener?.invoke(any: Any) {
    TODO("Not yet implemented")
}

}
