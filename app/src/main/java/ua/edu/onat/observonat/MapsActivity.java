package ua.edu.onat.observonat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.Arrays;

import ua.edu.onat.observonat.Helpers.CabinetsArrayAdapter;
import ua.edu.onat.observonat.Helpers.TouchImageView;

public class MapsActivity extends FragmentActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("ONATSettings", 0);
        int currTheme = pref.getInt("theme", R.style.FullScreen);
        if(currTheme!=R.style.FullScreen)
            setTheme(currTheme);
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
        String[] cities = {"101 (главный)" , "102 (главный)" , "102-A", "103", "104", "105", "105-A", "106", "106-A", "107", "107-A", "107-B", "108", "109",
                "110", "111-1/2", "111-3", "113/113-A", "114/114-A", "115", "116", "117/117-A", "118", "119", "120", "122", "123", "124"};
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.searchMap);
        CabinetsArrayAdapter  adapter =
                new CabinetsArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, new ArrayList<>(Arrays.asList(cities)));
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MapsActivity.this, FullScreen.class);
            intent.putExtra("cabinet", (String)adapterView.getItemAtPosition(i));
            startActivity(intent);
        });
    }
}
