package jp.letitride.mapsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var defaultPosition = LatLng(35.70316439, 139.57984714)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    data class MarkerData(
        var position:LatLng,
        var title:String,
        var color:Float?
    )

    override fun onMapReady(googleMap: GoogleMap) {

        val markerList = listOf<MarkerData>(
            MarkerData(LatLng(35.70436915, 139.57947137), "北口のお店", null),
            MarkerData(LatLng(35.70138951, 139.5772505), "公園口のお店", BitmapDescriptorFactory.HUE_YELLOW)
        )
        mMap = googleMap
        MyGooleMapHandler( mMap ).apply {
            //カメラの移動
            cameraPosition( defaultPosition, 15f )
            //マーカーの設置
            markerList.forEach {markerData ->
                setMarker( markerData.position, markerData.title, markerData.color )
            }
        }

        mMap.setOnMarkerClickListener { marker ->
            val id = marker.id.replace("m", "").toInt()
            Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            //click時にカメラを移動する trueは移動しない
            false
        }
        mMap.setOnInfoWindowClickListener { marker ->
            val id = marker.id.replace("m", "").toInt()
            Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
