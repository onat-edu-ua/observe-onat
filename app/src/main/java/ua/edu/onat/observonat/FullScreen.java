package ua.edu.onat.observonat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import ua.edu.onat.observonat.Helpers.TouchImageView;

public class FullScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("ONATSettings", 0);
        int currTheme = pref.getInt("theme", R.style.FullScreen);
        if(currTheme!=R.style.FullScreen)
            setTheme(currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        boolean is_laboratory_campus = getIntent().getBooleanExtra("laboratory_campus", false);
        int idOfResource = is_laboratory_campus ? R.drawable.ic_1stfloor_lab : R.drawable.ic_floor_1;
        TouchImageView floorFullScreen = findViewById(R.id.imageFloorFullScreen);
        floorFullScreen.setImageResource(idOfResource);
        floorFullScreen.setMaxZoom(6);
        floorFullScreen.setMinZoom(1);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        String cabinet = getIntent().getStringExtra("cabinet");
        if(cabinet != null) {
            ArrayList<PointF> points = new ArrayList<>();
            switch(cabinet) {
                case "101 (главный)":
                    points = new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(41,23), new PointF(41, 12), new PointF(42, 12),  new PointF(42, 7)));
                    break;
                case "102 (главный)":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 6)));
                    break;
                case "102-A":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 8)));
                    break;
                case "103":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 11)));
                    break;
                case "104/1-2":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 14)));
                    break;
                case "104":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 16)));
                    break;
                case "105-A":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 14)));
                    break;
                case "105":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 16)));
                    break;
                case "106":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(37,23), new PointF(37, 22)));
                    break;
                case "106-A":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(34,23), new PointF(34, 22)));
                    break;
                case "107":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(33,23), new PointF(33, 22)));
                    break;
                case "107-A":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(30,23), new PointF(30, 22)));
                    break;
                case "107-B":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(18,23), new PointF(18, 22)));
                    break;
                case "108":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(15,23), new PointF(15, 22)));
                    break;
                case "109":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(13,23), new PointF(13, 22)));
                    break;
                case "110":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 28)));
                    break;
                case "111-1/2":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 27), new PointF(6, 27)));
                    break;
                case "111-3":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 27), new PointF(6, 27)));
                    break;
                case "113/113-A":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 19), new PointF(6, 19)));
                    break;
                case "114/114-A":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 16), new PointF(6, 16)));
                    break;
                case "115":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 15), new PointF(6, 15)));
                    break;
                case "116":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 13), new PointF(6, 13)));
                    break;
                case "117/117-A":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 11), new PointF(6, 11)));
                    break;
                case "118":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 10), new PointF(6, 10)));
                    break;
                case "119":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 8), new PointF(6, 8)));
                    break;
                case "120":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(6,23), new PointF(6, 7)));
                    break;
                case "122":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 15), new PointF(8, 15)));
                    break;
                case "123":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 16), new PointF(8, 16)));
                    break;
                case "124":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(7,23), new PointF(7, 18), new PointF(8, 18)));
                    break;
            }
            floorFullScreen.addPointsToImage(points);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioFloor1:
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_1stfloor_lab : R.drawable.ic_floor_1);
                    break;
                case R.id.radioFloor2:
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    break;
                case R.id.radioFloor3:
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab :R.drawable.ic_floor_3);
                    break;
                case R.id.radioFloor4:
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_4thfloor_lab : R.drawable.ic_floor_4);
                    break;
                default:
                    break;
            }
            floorFullScreen.setZoom(1);
        });
        Button select_floor = findViewById(R.id.select_floor);
        select_floor.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ThreeDimensionalActivity.class)));
    }
}
