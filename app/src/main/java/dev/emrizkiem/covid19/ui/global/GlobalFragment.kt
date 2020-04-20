package dev.emrizkiem.covid19.ui.global

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
class GlobalFragment : Fragment(), OnMapReadyCallback {

    private val markers = mutableListOf<Marker>()
    private var mGoogleMap: GoogleMap? = null
    private var pulseCircle: Circle? = null

    private val detailData by lazy {
       // arguments?.getParcelableArrayList<CovidDetail>(DATA).orEmpty()
    }

    private val caseType by lazy {
        arguments?.getInt(TYPE) ?: CaseType.CONFIRMED
    }

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
    }

    override fun onMapReady(p0: GoogleMap?) {
        this.mGoogleMap = p0

        mGoogleMap?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                context, R.raw.style_map
            )
        )

        moveCamera(LatLng(LAT_DEFAULT, LONG_DEFAULT))
       // initMarker()
    }

//    private fun initMarker() {
//        mGoogleMap?.clear()
//        markers.clear()
//        detailData.forEach {
//            val marker = mGoogleMap?.addMarker(
//                MarkerOptions().position(LatLng(it.lat, it.long))
//                    .title(it.locationName)
//                    .icon(BitmapDescriptorFactory.fromResource(
//                        when(caseType) {
//                            CaseType.DEATHS -> R.drawable.ic_marker_death
//                            CaseType.RECOVERED -> R.drawable.ic_marker_recovered
//                            else -> R.drawable.ic_marker_confirmed
//                        }
//                    ))
//            )
//            marker?.let { m -> markers.add(m) }
//        }
//    }

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

//        @JvmStatic
//        fun newInstance(data: ArrayList<CovidDetail>, caseType: Int) =
//            GlobalFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelableArrayList(DATA, data)
//                    putInt(TYPE, caseType)
//                }
//            }
    }

}
