package com.example.my.weatherapi_5month.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.my.weatherapi_5month.databinding.FragmentFirstBinding
import com.example.my.weatherapi_5month.model.Model
import com.example.my.weatherapi_5month.presenter.FirstPresenter
import com.example.my.weatherapi_5month.view.FirstView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class FirstFragment : Fragment(), FirstView {

    private lateinit var binding: FragmentFirstBinding

    @Inject
    lateinit var presenter: FirstPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        val lon = arguments?.getString("key_lon").toString()
        val lat = arguments?.getString("key_lat").toString()
        val units = arguments?.getString("key_units").toString()
        presenter.getWeatherDetails(lon.toDouble(), lat.toDouble(), units)
    }

    override fun showWeather(weatherModel: Model) {
        with(binding) {
            tvCountry.text = weatherModel.sys.country
            tvTemp.text = weatherModel.main.temp.toString() + "°C"
            tvName.text = weatherModel.name
            //tvTempMax.text = weatherModel.main.temp_max.toString()
            //tvTempMin.text = weatherModel.main.temp_min.toString()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), "Error Service", Toast.LENGTH_SHORT).show()
    }

}