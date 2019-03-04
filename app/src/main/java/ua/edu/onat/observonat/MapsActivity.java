package ua.edu.onat.observonat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

import ua.edu.onat.observonat.Helpers.TouchImageView;

public class MapsActivity extends FragmentActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

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
                        PointF coordinates = mapBigPic.transformCoordTouchToBitmap(startX,startY,true);
                        Log.v("Coordinate x", String.valueOf(coordinates.x));
                        Log.v("Coordinate y", String.valueOf(coordinates.y));
                        float endX = event.getX();
                        float endY = event.getY();
                        if (isAClick(startX, endX, startY, endY)) {
                            if(coordinates.x > 41 && coordinates.x < 65 && coordinates.y > 42 && coordinates.y < 68) {
                                startActivity(new Intent(MapsActivity.this, FullScreen.class));
                            }
                            if(coordinates.x > 2 && coordinates.x < 6 && coordinates.y > 2 && coordinates.y < 10) {
                                Intent intent = new Intent(MapsActivity.this, FullScreen.class);
                                intent.putExtra("laboratory_campus", true);
                                startActivity(intent);
                            }
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
        SearchView searchView = findViewById(R.id.searchMap);
        searchView.setFocusable(false);
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.v("Search", s);
                return false;
            }
        });
    }
}
