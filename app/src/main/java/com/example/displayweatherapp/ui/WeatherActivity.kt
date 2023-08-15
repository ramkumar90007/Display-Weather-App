package com.example.displayweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.displayweatherapp.R
import com.example.displayweatherapp.repository.WeatherRepository

class WeatherActivity : AppCompatActivity() {

    lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val city = resources.getStringArray(R.array.City)
        val weatherRepository = WeatherRepository()
        val viewModelProviderFactory = WeatherViewModelProviderFactory(weatherRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(WeatherViewModel::class.java)
        //viewModel.getWeatherLocation(city[position])
        // access the items of the list

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        val btn_location = findViewById(R.id.btn_location) as Button
        val btn_daysummary = findViewById(R.id.btn_daysummary) as Button
        val btn_detailedweather = findViewById(R.id.btn_detailedweather) as Button
        val btn_onedaydata = findViewById(R.id.btn_onedaydata) as Button
        val btn_threedaydata = findViewById(R.id.btn_threedaydata) as Button
        val btn_currenttemperature = findViewById(R.id.btn_currenttemperature) as Button
        val btn_historicaldata = findViewById(R.id.btn_historicaldata_past3days) as Button
// set on-click listener
        btn_location.setOnClickListener {
            Toast.makeText(this@WeatherActivity, "Display Location", Toast.LENGTH_SHORT).show()
        }
        btn_daysummary.setOnClickListener {
            Toast.makeText(this@WeatherActivity, "Display Day Summary", Toast.LENGTH_SHORT).show()
        }
        btn_detailedweather.setOnClickListener {
            Toast.makeText(this@WeatherActivity, "Display Detailed Weather", Toast.LENGTH_SHORT).show()
        }
        btn_onedaydata.setOnClickListener {
            Toast.makeText(this@WeatherActivity, "Display Oneday Data", Toast.LENGTH_SHORT).show()
        }
        btn_threedaydata.setOnClickListener {
            Toast.makeText(this@WeatherActivity, "Display Threeday Data", Toast.LENGTH_SHORT).show()
        }
        btn_currenttemperature.setOnClickListener {
            Toast.makeText(this@WeatherActivity, "Display current Temperature", Toast.LENGTH_SHORT)
                .show()
        }
        btn_historicaldata.setOnClickListener {
            Toast.makeText(this@WeatherActivity, "Display Historical Data", Toast.LENGTH_SHORT).show()
        }
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, city
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    viewModel.getWeatherLocation(city[position])
                   /* Toast.makeText(
                        this@WeatherActivity,
                        getString(R.string.selected_item) + " " +
                                "" + city[position], Toast.LENGTH_SHORT
                    ).show()*/
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}