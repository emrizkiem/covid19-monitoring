package dev.emrizkiem.covid19.ui.maps

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dev.emrizkiem.covid19.R
import dev.emrizkiem.covid19.data.model.home.CovidDetail
import dev.emrizkiem.covid19.util.CaseType
import kotlin.math.pow

/**
 * A simple [Fragment] subclass.
 */
class MapsFragment : Fragment(), OnMapReadyCallback {

    private val markers = mutableListOf<Marker>()
    private var mGoogleMap: GoogleMap? = null
    private var pulseCircle: Circle? = null

    private val detailData by lazy {
        arguments?.getParcelableArrayList<CovidDetail>(DATA).orEmpty()
    }

    private val caseType by lazy {
        arguments?.getInt(TYPE) ?: CaseType.CONFIRMED
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fragment_map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        this.mGoogleMap = p0

        mGoogleMap?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                context, R.raw.style_map
            )
        )

        moveCamera(LatLng(LAT_DEFAULT, LONG_DEFAULT))
        initMarker()
    }

    private fun initMarker() {
        mGoogleMap?.clear()
        markers.clear()
        detailData.forEach {
            val marker = mGoogleMap?.addMarker(
                MarkerOptions().position(LatLng(it.lat, it.long))
                    .title(it.locationName)
                    .icon(BitmapDescriptorFactory.fromResource(
                        when(caseType) {
                            CaseType.DEATHS -> R.drawable.ic_marker_death
                            CaseType.RECOVERED -> R.drawable.ic_marker_recovered
                            else -> R.drawable.ic_marker_confirmed
                        }
                    ))
            )
            marker?.let { m -> markers.add(m) }
        }
    }

    private fun moveCamera(latLng: LatLng) {
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4f))
    }

    fun selectItem(data: CovidDetail) {
        mGoogleMap?.let {
            moveCamera(LatLng(data.lat, data.long))
            startPulsAnimation(LatLng(data.lat, data.long))
        }
    }

    private val valueAnimator by lazy {
        ValueAnimator.ofFloat(
            0f,
            calculatePulseRadius(mGoogleMap?.cameraPosition?.zoom ?: 4f).apply { }
        ).apply {
            startDelay = 100
            duration = 800
            interpolator = AccelerateDecelerateInterpolator()
        }
    }

    private fun calculatePulseRadius(zoomLevel: Float): Float {
        return 2.0.pow(16 - zoomLevel.toDouble()).toFloat() * 160
    }

    private fun startPulsAnimation(latLng: LatLng) {
        valueAnimator?.apply {
            removeAllUpdateListeners()
            removeAllListeners()
            end()
        }

        pulseCircle?.remove()
        pulseCircle = mGoogleMap?.addCircle(
            CircleOptions().center(
                latLng
            ).radius(0.0).strokeWidth(0f)
        )

        valueAnimator.addUpdateListener {
            pulseCircle?.fillColor = when (caseType) {
                CaseType.RECOVERED -> RECOVERED_COLOR
                CaseType.DEATHS -> DEATH_COLOR
                else -> CONFIRMED_COLOR

            }
            pulseCircle?.radius = (valueAnimator.animatedValue as Float).toDouble()
        }

        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                valueAnimator.startDelay = 100
                valueAnimator.start()
            }
        })

        valueAnimator.start()
    }

    companion object {
        private val RECOVERED_COLOR = Color.argb(70, 0, 204, 153)
        private val CONFIRMED_COLOR = Color.argb(70, 242, 185, 0)
        private val DEATH_COLOR = Color.argb(70, 226, 108, 90)

        private const val LAT_DEFAULT = 30.360227
        private const val LONG_DEFAULT = 114.8260094
        private const val DATA = "data"
        private const val TYPE = "type"

        @JvmStatic
        fun newInstance(data: ArrayList<CovidDetail>, caseType: Int) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(DATA, data)
                    putInt(TYPE, caseType)
                }
            }
    }

}
