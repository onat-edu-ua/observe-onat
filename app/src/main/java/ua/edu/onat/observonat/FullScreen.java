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
                case "102-A (главный)":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 8)));
                    break;
                case "103 (главный)":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 11)));
                    break;
                case "104/1-2 (главный)":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 14)));
                    break;
                case "104 (главный)":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 16)));
                    break;
                case "105-A (главный)":
                    points =  new ArrayList<>(Arrays.asList(new PointF(24.6f,25), new PointF(24.6f,23), new PointF(42,23), new PointF(42, 14)));
                    break;
                case "105 (главный)":
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

                // Пиздец здесь будет говнокод. Вот пусть это будет на сервере, а то места дохера
                case "200":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 4), new PointF(28, 4)));
                    break;
                case "201 (главный)":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 3)));
                    break;
                case "202 (главный)":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 4), new PointF(31, 4)));
                    break;
                case "203":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 5), new PointF(31, 5)));
                    break;
                case "204":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 6), new PointF(31, 6)));
                    break;
                case "205":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 8), new PointF(31, 8)));
                    break;
                case "206":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 10), new PointF(31, 10)));
                    break;
                case "207":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 12), new PointF(31, 12)));
                    break;
                case "208":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 15), new PointF(31, 15)));
                    break;
                case "209":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(29,16), new PointF(29, 17), new PointF(31, 17)));
                    break;
                case "210":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(29,16), new PointF(29, 7), new PointF(28, 7)));
                    break;
                case "210-A":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(29,16), new PointF(29, 6), new PointF(28, 6)));
                    break;
                case "211":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(29,16), new PointF(29, 8), new PointF(28, 8)));
                    break;
                case "212":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(29,16), new PointF(29, 9), new PointF(28, 9)));
                    break;
                case "213":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(29,16), new PointF(29, 10), new PointF(28, 10)));
                    break;
                case "215":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(24,16), new PointF(24, 14)));
                    break;
                case "216":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(23,16), new PointF(23, 14)));
                    break;
                case "218":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(19,16), new PointF(19, 12)));
                    break;
                case "220":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(13,16), new PointF(13, 12)));
                    break;
                case "221":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(11,16), new PointF(11, 14)));
                    break;
                case "222":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(9,16), new PointF(9, 14)));
                    break;
                case "223":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 17)));
                    break;
                case "225":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(2,16)));
                    break;
                case "224":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 13), new PointF(2, 13)));
                    break;
                case "226":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 8), new PointF(2, 8)));
                    break;
                case "227":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 7), new PointF(2, 7)));
                    break;
                case "228":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 4), new PointF(2, 4)));
                    break;
                case "230":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 4)));
                    break;
                case "231":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(2,16), new PointF(2, 3), new PointF(2, 7), new PointF(4, 7)));
                    break;
                case "229":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 6), new PointF(4, 6)));
                    break;
                case "232":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 8), new PointF(4, 8)));
                    break;
                case "233":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 9), new PointF(4, 9)));
                    break;
                case "234":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 10), new PointF(4, 10)));
                    break;
                case "235":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    radioGroup.check(R.id.radioFloor2);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 11), new PointF(4, 11)));
                    break;
                case "301":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 3)));
                    break;
                case "302":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 4), new PointF(31, 4)));
                    break;
                case "303":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 5), new PointF(31, 5)));
                    break;
                case "304":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 7), new PointF(31, 7)));
                    break;
                case "305":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 8), new PointF(31, 8)));
                    break;
                case "306":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 11), new PointF(31, 11)));
                    break;
                case "307":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 12), new PointF(31, 12)));
                    break;
                case "308":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(31,16)));
                    break;
                case "309":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 17)));
                    break;
                case "310":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 6), new PointF(29, 6)));
                    break;
                case "311":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 7), new PointF(29, 7)));
                    break;
                case "312-A":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 8), new PointF(29, 8)));
                    break;
                case "312":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 9), new PointF(29, 9)));
                    break;
                case "313":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(30,16), new PointF(30, 11), new PointF(29, 11)));
                    break;
                case "315":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(26,16), new PointF(26, 14)));
                    break;
                case "316":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(24,16), new PointF(24, 14)));
                    break;
                case "317":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(23,16), new PointF(23, 14)));
                    break;
                case "318":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(21,16), new PointF(21, 14)));
                    break;
                case "319":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(19,16), new PointF(19, 12)));
                    break;
                case "320":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(13,16), new PointF(13, 12)));
                    break;
                case "321":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(11,16), new PointF(11, 14)));
                    break;
                case "322":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(9,16), new PointF(9, 14)));
                    break;
                case "323":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 18)));
                    break;
                case "324":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(2,16)));
                    break;
                case "325":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 14), new PointF(2, 14)));
                    break;
                case "325-A":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 10), new PointF(2, 10)));
                    break;
                case "326":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 7), new PointF(2, 7)));
                    break;
                case "330":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 4), new PointF(2, 4)));
                    break;
                case "331":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 3)));
                    break;
                case "328":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 6), new PointF(4, 6)));
                    break;
                case "329":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 7), new PointF(4, 7)));
                    break;
                case "332":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 8), new PointF(4, 8)));
                    break;
                case "333":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 9), new PointF(4, 9)));
                    break;
                case "334":
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab : R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 10), new PointF(4, 10)));
                    break;
                case "335":
                    floorFullScreen.setImageResource(R.drawable.ic_floor_3);
                    radioGroup.check(R.id.radioFloor3);
                    points = new ArrayList<>(Arrays.asList(new PointF(16.6f,17), new PointF(16.6f,16), new PointF(3,16), new PointF(3, 12), new PointF(4, 12)));
                    break;

                case "402":
                    floorFullScreen.setImageResource(R.drawable.ic_floor_4);
                    radioGroup.check(R.id.radioFloor4);
                    points = new ArrayList<>(Arrays.asList(new PointF(11.6f,12), new PointF(5,12), new PointF(5, 11)));
                    break;
                case "402-A":
                    floorFullScreen.setImageResource(R.drawable.ic_floor_4);
                    radioGroup.check(R.id.radioFloor4);
                    points = new ArrayList<>(Arrays.asList(new PointF(11.6f,12), new PointF(4,12), new PointF(4, 13)));
                    break;
                case "403":
                    floorFullScreen.setImageResource(R.drawable.ic_floor_4);
                    radioGroup.check(R.id.radioFloor4);
                    points = new ArrayList<>(Arrays.asList(new PointF(11.6f,12), new PointF(16.6f,12)));
                    break;
                case "403-A":
                    floorFullScreen.setImageResource(R.drawable.ic_floor_4);
                    radioGroup.check(R.id.radioFloor4);
                    points = new ArrayList<>(Arrays.asList(new PointF(11.6f,12), new PointF(18.6f,12), new PointF(19, 13)));
                    break;
                case "404":
                    floorFullScreen.setImageResource(R.drawable.ic_floor_4);
                    radioGroup.check(R.id.radioFloor4);
                    points = new ArrayList<>(Arrays.asList(new PointF(7.6f,15), new PointF(7.6f,12), new PointF(15, 12), new PointF(15, 15)));
                    break;

                case "101 (2 лаб)":
                    floorFullScreen.setImageResource(R.drawable.ic_1stfloor_lab);
                    points = new ArrayList<>(Arrays.asList(new PointF(8f,16), new PointF(8f,18), new PointF(16, 18), new PointF(16, 26)));
                    break;
                case "102 (2 лаб)":
                    break;
            }
            floorFullScreen.addPointsToImage(points);
        }

        if(is_laboratory_campus) {
            findViewById(R.id.radioFloor5).setVisibility(View.VISIBLE);
            findViewById(R.id.text_5_floor).setVisibility(View.VISIBLE);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioFloor1:
                    ArrayList<PointF> points = new ArrayList<>();
                    floorFullScreen.addPointsToImage(points);
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_1stfloor_lab : R.drawable.ic_floor_1);
                    break;
                case R.id.radioFloor2:
                    points = new ArrayList<>();
                    floorFullScreen.addPointsToImage(points);
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_2ndfloor_lab : R.drawable.ic_floor_2);
                    break;
                case R.id.radioFloor3:
                    points = new ArrayList<>();
                    floorFullScreen.addPointsToImage(points);
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_3dfloor_lab :R.drawable.ic_floor_3);
                    break;
                case R.id.radioFloor4:
                    points = new ArrayList<>();
                    floorFullScreen.addPointsToImage(points);
                    floorFullScreen.setImageResource(is_laboratory_campus ? R.drawable.ic_4thfloor_lab : R.drawable.ic_floor_4);
                    break;
                case R.id.radioFloor5:
                    points = new ArrayList<>();
                    floorFullScreen.addPointsToImage(points);
                    floorFullScreen.setImageResource(R.drawable.ic_5thfloor_lab);
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
