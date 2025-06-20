package com.pnu.pnuguide.ui.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.SearchView
import android.location.Geocoder
import java.io.IOException
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.MaterialToolbar
import com.pnu.pnuguide.R
import com.pnu.pnuguide.MainActivity

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_map)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val searchView = findViewById<SearchView>(R.id.search_view_map)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchLocation(it)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val pnu = LatLng(35.2323, 129.0831)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pnu, 16f))
        googleMap.addMarker(MarkerOptions().position(pnu).title("PNU"))
        enableMyLocation()
        googleMap.setOnInfoWindowClickListener { marker ->
            val gmmIntentUri = Uri.parse("google.navigation:q=${marker.position.latitude},${marker.position.longitude}")
            val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation()
        }
    }

    private fun searchLocation(query: String) {
        try {
            val geocoder = Geocoder(this)
            val results = geocoder.getFromLocationName(query, 1)
            if (!results.isNullOrEmpty()) {
                val location = results[0]
                val latLng = LatLng(location.latitude, location.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
                googleMap.addMarker(MarkerOptions().position(latLng).title(query))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST = 1
    }
}
