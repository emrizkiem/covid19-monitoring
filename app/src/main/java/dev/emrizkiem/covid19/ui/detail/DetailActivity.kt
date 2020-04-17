package dev.emrizkiem.covid19.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.ui.maps.MapsFragment
import dev.emrizkiem.covid19.util.CaseType
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var mapsFragment: MapsFragment? = null

    private val caseType by lazy {
        intent.getIntExtra(CASE_TYPE, CaseType.CONFIRMED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        fab_back.setOnClickListener { onBackPressed() }
        attachMaps()
    }

    private fun attachMaps() {
        //mapsFragment = MapsFragment.newInstance(ArrayList(data), caseType)
        mapsFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, it)
                .commit()
        }
    }

    companion object {
        private const val CASE_TYPE = "case_type"
    }
}
