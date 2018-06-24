package ua.edu.onat.observonat;

import android.graphics.PointF;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import ua.edu.onat.observonat.Helpers.CustomMapTileProvider;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(new CustomMapTileProvider(getResources().getAssets())));
        // Ставим на главный корпус
        LatLng mainCampus = new LatLng(46.482293, 30.723556);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainCampus, 17f));

        //origin
        //76722 -> 3584
        int xtile = (int)Math.floor( (30.723556 + 180) / 360 * (1<<17) ) ;
        //46376 -> 2816
        int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(46.482293)) + 1 / Math.cos(Math.toRadians(46.482293))) / Math.PI) / 2 * (1<<17) ) ;

    }
}
