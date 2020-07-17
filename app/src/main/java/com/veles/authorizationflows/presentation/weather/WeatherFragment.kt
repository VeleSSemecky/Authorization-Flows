package com.veles.authorizationflows.presentation.weather

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.mvvm.fragment.BaseFragment
import com.veles.authorizationflows.data.local.permission.PermissionManager
import com.veles.authorizationflows.data.local.permission.PermissionResult
import com.veles.authorizationflows.model.api.WeatherNetworkModel
import com.veles.authorizationflows.util.convertWhenIsPositive
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.sntProgressBar
import kotlinx.android.synthetic.main.toolbar_back.*
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WeatherFragment : BaseFragment<WeatherContract.ViewModel>(),
    WeatherContract.View {

    override val viewModel: WeatherContract.ViewModel by viewModels { viewModelFactory }

    override val toolbarResource: Int
        get() = R.layout.toolbar_back

    override val layoutResource: Int
        get() = R.layout.fragment_weather

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onViewReady(_savedInstanceState: Bundle?) {
        sntIvBack.setOnClickListener { findNavController().popBackStack() }
        permissionManager
            .checkPermissionLocation()
            .checkPermissionResult(this)
        viewModel.apply {
            data.observe(viewLifecycleOwner,apiWeatherObserver())
            error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), "Error $it", Toast.LENGTH_SHORT).show()
            })
            isProgress.observe(viewLifecycleOwner, Observer {
                sntProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionManager.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this::onPermissionProvided
        )
    }
    @SuppressLint("MissingPermission")
    private fun onPermissionProvided(arg: PermissionResult) {
        when (arg) {
            PermissionResult.PERMISSION_DENIED -> {
                Toast.makeText(
                    requireContext(),
                    R.string.you_did_not_grant_access,
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }
            PermissionResult.PERMISSION_GRANTED -> fusedLocationProviderClient.lastLocation
                .addOnSuccessListener(requireActivity(), this::onSuccess)
                .addOnFailureListener(this::onFailure)
        }
    }

    override fun onSuccess(location: Location) {
        viewModel.apiWeather(location.latitude, lon = location.longitude)
        tvDataNameLocation.text = viewModel.getCityName(requireContext(), location)
    }

    override fun onFailure(e: Exception) {
        Toast.makeText(requireContext(), R.string.error_location, Toast.LENGTH_SHORT).show()
    }

    override fun apiWeatherObserver() = Observer<WeatherNetworkModel> {
        it.current.apply {
            tvDataPressure.text =
                getString(R.string.pressure, pressure.toString())
            tvDataHumidity.text =
                getString(R.string.humidity, humidity.toString())
            tvDataWind.text =
                getString(R.string.wind_speed, wind_speed.toString())
            tvDataTemperature.text = getString(R.string.temperature,
                temp.toInt().convertWhenIsPositive())
        }

    }


}