package com.gbhw.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gbhw.weatherapp.ui.main.MainActivityFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity2_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainActivityFragment.newInstance())
                .commitNow()
        }
    }
}