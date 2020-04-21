package dev.emrizkiem.covid19.ui.global

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
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
import dev.emrizkiem.covid19.data.model.global.Location
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.util.CaseType
import kotlinx.android.synthetic.main.fragment_global.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.pow

/**
 * A simple [Fragment] subclass.
 */
@ExperimentalCoroutinesApi
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
            renderOverviewGlobal(it)
        })
        viewModel.location.observe(this, Observer {
            renderCaseUpdateLocationMarker(it)
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun renderOverviewGlobal(overview: DataResponse) {
        TransitionManager.beginDelayedTransition(viewParent)

        renderValueOverviewGlobal(
            overview.confirmed?.value.toString(),
            overview.deaths?.value.toString(),
            overview.recovered?.value.toString()
        )
    }

    private fun renderValueOverviewGlobal(vararg value: String) {
        text_confirmed_global.text = value[CONFIRMED_INDEX]
        text_deaths_global.text = value[DEATH_INDEX]
        text_recovered_global.text = value[RECOVERED_INDEX]
    }

    override fun onMapReady(p0: GoogleMap?) {
        this.mGoogleMap = p0

        mGoogleMap?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                context, R.raw.style_map
            )
        )

        mGoogleMap?.setOnMarkerClickListener {
            onMarkerClick(it)

            mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position, 5f))
            true
        }

        mGoogleMap?.setOnMapClickListener { onMapClick() }

        viewModel.getDataWithLocation()
        moveCamera(LatLng(LAT_DEFAULT, LONG_DEFAULT))
    }

    private fun onMarkerClick(marker: Marker) {
        TransitionManager.beginDelayedTransition(viewParent)

        cardLocation.visibility = View.VISIBLE
        textLocation.text = marker.title

        val snippets = marker.snippet.split("::").toTypedArray()

        renderValueOverviewGlobal(*snippets)
    }

    private fun onMapClick() {
        TransitionManager.beginDelayedTransition(viewParent)

        cardLocation.visibility = View.GONE
    }

    private fun moveCamera(latLng: LatLng) {
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4f))
    }

    private fun renderCaseUpdateLocationMarker(location: Location) {
        val latLng = LatLng(location.lat ?: 0.0, location.long ?: 0.0)

        val snippets = "${location.confirmed}::${location.deaths}::${location.recovered}::${location.readableLastUpdate}"

        mGoogleMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(location.countryRegion)
                .snippet(snippets)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
        )
    }

    companion object {
        const val CONFIRMED_INDEX = 0
        const val RECOVERED_INDEX = 1
        const val DEATH_INDEX = 2
        const val LAST_UPDATE_INDEX = 3

        private const val LAT_DEFAULT = 30.360227
        private const val LONG_DEFAULT = 114.8260094
    }

}
