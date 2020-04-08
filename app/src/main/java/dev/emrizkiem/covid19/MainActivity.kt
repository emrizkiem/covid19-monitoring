package dev.emrizkiem.covid19

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import dev.emrizkiem.covid19.ui.explore.ExploreFragment
import dev.emrizkiem.covid19.ui.home.HomeFragment
import dev.emrizkiem.covid19.ui.information.InformationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            menu_navigation.setItemSelected(R.id.navigation_dashboard, true)
            fragmentManager = supportFragmentManager
            val homeFragment = HomeFragment()
            fragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit()
        }

        menu_navigation.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
                when(id) {
                    R.id.navigation_dashboard -> fragment = HomeFragment()
                    R.id.navigation_explore -> fragment = ExploreFragment()
                    R.id.navigation_information -> fragment = InformationFragment()
                }

                if (fragment != null) {
                    fragmentManager = supportFragmentManager
                    fragmentManager!!.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                } else {
                    Log.e(TAG, "Error in creating fragment")
                }
            }
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
