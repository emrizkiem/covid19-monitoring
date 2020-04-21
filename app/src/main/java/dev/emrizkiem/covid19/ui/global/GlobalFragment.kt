package dev.emrizkiem.covid19.ui.global

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.global.Data
import dev.emrizkiem.covid19.data.model.global.DataResponse
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.util.CaseType
import kotlinx.android.synthetic.main.fragment_global.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.pow

/**
 * A simple [Fragment] subclass.
 */
class GlobalFragment : Fragment(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null
    private val viewModel: GlobalViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_global, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fragment_map) as SupportMapFragment

        mapFragment.getMapAsync(this)
        observeViewModel()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observeViewModel() {
        viewModel.state.observe(this, Observer {  })
        viewModel.overview.observe(this, Observer {
            it?.let { renderOverviewGlobal(it) }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun renderOverviewGlobal(overview: DataResponse) {
        text_confirmed_global.text = overview.confirmed?.value.toString()
        text_deaths_global.text = overview.deaths?.value.toString()
        text_recovered_global.text = overview.recovered?.value.toString()
    }

    override fun onMapReady(p0: GoogleMap?) {
        this.mGoogleMap = p0

        mGoogleMap?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                context, R.raw.style_map
            )
        )

        moveCamera(LatLng(LAT_DEFAULT, LONG_DEFAULT))
    }

    private fun moveCamera(latLng: LatLng) {
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4f))
    }


    companion object {
        private val RECOVERED_COLOR = Color.argb(70, 0, 204, 153)
        private val CONFIRMED_COLOR = Color.argb(70, 242, 185, 0)
        private val DEATH_COLOR = Color.argb(70, 226, 108, 90)

        private const val LAT_DEFAULT = 30.360227
        private const val LONG_DEFAULT = 114.8260094
        private const val DATA = "data"
        private const val TYPE = "type"
    }

}
