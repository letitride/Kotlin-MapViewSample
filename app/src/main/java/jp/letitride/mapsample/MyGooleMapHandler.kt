package jp.letitride.mapsample

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MyGooleMapHandler(
    val mMap:GoogleMap){

    fun cameraPosition( position:LatLng, zoom:Float ){
        //カメラオブジェクト
        val camera = CameraUpdateFactory.newLatLngZoom(position, zoom)
        //カメラの移動
        mMap.moveCamera(camera)
    }

    fun setMarker(position: LatLng, title:String, color:Float?){
        val options = MarkerOptions()
        options.position( position )
        options.title( title )
        color?.let{
            options.icon( BitmapDescriptorFactory.defaultMarker(color) )
        }
        mMap.addMarker(options);
    }
}