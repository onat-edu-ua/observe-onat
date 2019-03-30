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
        String[] cabinets = {
                // 1 этаж
                "101 (главный)" , "102 (главный)" , "102-A (главный)", "103 (главный)", "104 (главный)", "104/1-2 (главный)", "105 (главный)", "105-A (главный)", "106", "106-A", "107", "107-A", "107-B", "108", "109",
                "110", "111-1/2", "111-3", "113/113-A", "114/114-A", "115", "116", "117/117-A", "118", "119", "120", "122", "123", "124",

                // 2 этаж
                "200", "201 (главный)", "202 (главный)", "203", "204", "205", "206", "207", "208", "209", "210", "210-A", "211", "212", "213", "215",
                "216", "218", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "235",

                // 3rd floor
                "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312-A", "312", "313", "315", "316", "317", "318", "319",
                "320", "321", "322", "323", "324", "325", "325-A", "326", "328", "329", "330", "331", "332", "333", "334", "335",

                // 4th floor
                "402", "402-A", "403", "403-A", "404",

                /*
                второй лаб
                 */

                // 1st floor
                "101 (2 лаб)", "103 (2 лаб)", "104 (2 лаб)", "105 (2 лаб)", "106 (2 лаб)", "107 (2 лаб)", "108 (2 лаб)", "109 (2 лаб)", "116 (2 лаб)", "124 (2 лаб)",

                // 2nd floor
                "242-A (2 лаб)", "243 (2 лаб)", "244 (2 лаб)", "247 (2 лаб)", "248 (2 лаб)", "249 (2 лаб)", "250 (2 лаб)", "251 (2 лаб)", "252 (2 лаб)", "253 (2 лаб)",

                // 3rd floor
                "363 (2 лаб)", "364 (2 лаб)", "365 (2 лаб)", "366 (2 лаб)", "367 (2 лаб)", "368 (2 лаб)", "370, 370-A (2 лаб)", "372 (2 лаб)", "373 (2 лаб)", "374 (2 лаб)",

                // 4th floor
                "485 (2 лаб)", "486 (2 лаб)", "487 (2 лаб)", "488 (2 лаб)", "491 (2 лаб)", "492 (2 лаб)", "493 (2 лаб)", "494 (2 лаб)", "495 (2 лаб)", "496 (2 лаб)",

                // 5th floor
                "502 (2 лаб)", "503 (2 лаб)", "504 (2 лаб)", "505 (2 лаб)", "506 (2 лаб)", "507 (2 лаб)", "508 (2 лаб)", "509 (2 лаб)", "512 (2 лаб)", "513 (2 лаб)",
                "514 (2 лаб)", "515 (2 лаб)", "518 (2 лаб)"
        };
        Log.v("Length", String.valueOf(cabinets.length));
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.searchMap);
        CabinetsArrayAdapter  adapter =
                new CabinetsArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, new ArrayList<>(Arrays.asList(cabinets)));
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MapsActivity.this, FullScreen.class);
            // Начиная со 103 номера -ь это лабораторный корпус
            String cabinetName = (String)adapterView.getItemAtPosition(i);
            intent.putExtra("cabinet", cabinetName);
            if(Arrays.asList(cabinets).indexOf(cabinetName) > 102) {
                intent.putExtra("laboratory_campus", true);
            }
            startActivity(intent);
        });
    }
}
