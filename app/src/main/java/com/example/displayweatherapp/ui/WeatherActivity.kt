package com.example.displayweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.displayweatherapp.R
import com.example.displayweatherapp.models.Current
import com.example.displayweatherapp.models.WeatherResponse
import com.example.displayweatherapp.repository.WeatherRepository
import com.example.displayweatherapp.util.Resource

class WeatherActivity : AppCompatActivity() {

    lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val city = resources.getStringArray(R.array.City)
        val weatherRepository = WeatherRepository()
        val viewModelProviderFactory = WeatherViewModelProviderFactory(weatherRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(WeatherViewModel::class.java)
        //viewModel.getWeatherLocation(city[position])
        // access the items of the list

        viewModel.weatherLocation.observe(this, observer);

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)/*        val btn_location = findViewById(R.id.btn_location) as Button
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
                    Toast.makeText(this@WeatherActivity, "Display Detailed Weather", Toast.LENGTH_SHORT)
                        .show()
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
                    Toast.makeText(this@WeatherActivity, "Display Historical Data", Toast.LENGTH_SHORT)
                        .show()
                }*/
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, city
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View, position: Int, id: Long
                ) {
                    viewModel.getWeatherLocation(city[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private val observer = Observer<Resource<WeatherResponse>> { response ->
        response.data?.let {
            showWeatherData(it)
        }
    }

    private fun showWeatherData(weatherResponse: WeatherResponse) {
        val current = weatherResponse.current!!
        val linearContainer = findViewById<LinearLayout>(R.id.linearContainer)

        if (linearContainer.childCount > 0) {
            linearContainer.removeAllViews()
        }

        addFieldView("Latitude:", weatherResponse.location?.lat.toString())
        addFieldView("Longitude:", weatherResponse.location?.lon.toString())
        addFieldView("Last Updated:", current.last_updated ?: "")
        addFieldView("Temperature (°C):", current.temp_c.toString())
        addFieldView("Temperature (°F):", current.temp_f.toString())
        addFieldView("Is Day:", current.is_day.toString())
        addFieldView("Condition:", current.condition?.text.toString())
        addFieldView("Wind (mph):", current.wind_mph.toString())
        addFieldView("Wind (kph):", current.wind_kph.toString())
        addFieldView("Wind Degree:", current.wind_degree.toString())
        addFieldView("Wind Direction:", current.wind_dir.toString())
        addFieldView("Pressure (mb):", current.pressure_mb.toString())
        addFieldView("Pressure (in):", current.pressure_in.toString())
        addFieldView("Precipitation (mm):", current.precip_mm.toString())
        addFieldView("Precipitation (in):", current.precip_in.toString())
        addFieldView("Humidity:", current.humidity.toString())
        addFieldView("Cloud:", current.cloud.toString())
        addFieldView("Feels Like (°C):", current.feelslike_c.toString())
        addFieldView("Feels Like (°F):", current.feelslike_f.toString())
        addFieldView("Visibility (km):", current.vis_km.toString())
        addFieldView("Visibility (miles):", current.vis_miles.toString())
        addFieldView("UV Index:", current.uv.toString())
        addFieldView("Gust (mph):", current.gust_mph.toString())
        addFieldView("Gust (kph):", current.gust_kph.toString())
    }
    private fun addFieldView(title: String, value: String) {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val linearContainer = findViewById<LinearLayout>(R.id.linearContainer)

        val itemView = layoutInflater.inflate(R.layout.item_weather, linearContainer, false)

        val textTitle: TextView = itemView.findViewById(R.id.title)
        val textValue: TextView = itemView.findViewById(R.id.value)

        textTitle.text = title
        textValue.text = value

        itemView.layoutParams = layoutParams

        linearContainer.addView(itemView)
    }

}