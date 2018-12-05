package ua.edu.onat.observonat;

import android.content.Intent;
import android.graphics.PointF;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import ua.edu.onat.observonat.Helpers.CustomMapTileProvider;
import ua.edu.onat.observonat.Helpers.TouchImageView;

public class MapsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Button select_floor = findViewById(R.id.select_floor);
        select_floor.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ThreeDimensionalActivity.class)));
        TouchImageView mapBigPic = findViewById(R.id.mapBigPic);
        mapBigPic.setZoom(3);
        mapBigPic.setOnTouchListener(new View.OnTouchListener() {
            private int CLICK_ACTION_THRESHOLD = 5;
            private float startX;
            private float startY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        if (isAClick(startX, endX, startY, endY) && startX>188 && startX < 801 && startY<1690 && startY> 894) {
                            startActivity(new Intent(MapsActivity.this, FullScreen.class));
                        }
                        break;
                }
                return true;
            }

            private boolean isAClick(float startX, float endX, float startY, float endY) {
                float differenceX = Math.abs(startX - endX);
                float differenceY = Math.abs(startY - endY);
                return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
            }
        });
    }
}
