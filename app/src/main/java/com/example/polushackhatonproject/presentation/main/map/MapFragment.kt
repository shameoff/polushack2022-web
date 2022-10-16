package com.example.polushackhatonproject.presentation.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.polushackhatonproject.R
import com.example.polushackhatonproject.databinding.FragmentMapBinding
import com.example.polushackhatonproject.domain.main.model.Coordinate
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.image.ImageProvider

class MapFragment : Fragment() {

    companion object {
        const val MAPS_API_KEY = "f73c83ab-4ec2-487a-a28f-3ac53d948347"
    }

    private lateinit var binding: FragmentMapBinding

    private val viewModel by lazy {
        MapFragmentViewModel(activity?.application!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val mainView = inflater.inflate(R.layout.fragment_map, container, false)
        binding = FragmentMapBinding.bind(mainView)
        binding.mapView.map.move(
            CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )

        // TODO: добавить функцию onCoordinatesLiveDataChange()
        return mainView
    }

    private fun onCoordinatesLiveDataChange() {
        viewModel.getCoordinatesLiveData().observe(this) {
            createPlacemark(it)
        }
    }

    private fun createPlacemark(coordinate: Coordinate) {
        binding.mapView.map.mapObjects.addPlacemark(
            Point(coordinate.latitude.toDouble(), coordinate.longitude.toDouble()),
            ImageProvider.fromResource(this.context, R.drawable.main_map_marker)
        )
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }


}