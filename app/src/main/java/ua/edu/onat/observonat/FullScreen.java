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
                    points = new ArrayList<>(Arrays.asList(new PointF(10,10), new PointF(10,100), new PointF(100,100)));
                    break;
                case "102 (главный)":
                    points =  new ArrayList<>(Arrays.asList(new PointF(80,10), new PointF(80,80), new PointF(120,80)));
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
