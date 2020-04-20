package dev.emrizkiem.covid19.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emrizkiem.covid19.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ic_back.setOnClickListener { onBackPressed() }
    }
}
