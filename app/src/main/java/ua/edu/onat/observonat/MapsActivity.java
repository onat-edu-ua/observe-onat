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
                "101 (главный)" , "102 (главный)" , "102-A (главный)", "103 (главный)", "104 (главный) Отдел комплектования и научной обработки документов", "104/1-2 (главный)", "105 (главный) Кафедра высшей математики",
                "105-A (главный) Заведующий кафедрой высшей математики",
                "106", "106-A", "107", "107-A", "107-B Отдел кадров", "108 Приемная комиссия", "109 Руководитель центра информационных технологий, сертификации и трудоустройства",
                "110", "111-1/2", "111-3", "113/113-A Канцелярия", "114/114-A Учебный отдел", "115 Проректор с научной работы", "116 Отдел расчетов со студентами",
                "117/117-A Бухгалтерия", "118",
                "119 Подготовительные курсы/Центр компьютерных информационных технологий", "120", "122", "123 Начальник отдела кадров",
                "124 Проректор по административно-хозяйственной работы",

                // 2 этаж
                "200", "201 (главный)", "202 (главный)", "203 Деканат факультета заочного обучения", "204 Лаборатория инновационных технологий электронного управления", "205", "206", "207",
                "208 Международный центра сотрудничества с МСЕ", "209", "210 Центр гуманитарного образования и развития",
                "210-A Юрисконсульт", "211 Профком сотрудников", "212", "213 ННИ инфокоммуникация и программной инженерии", "215 Дирекция ННИ почтовая связь",
                "216 Буфет", "218", "220", "221", "222 Лаборатория сетевых технологий", "223", "224", "225", "226", "227 Кафедра философии и истории Украины", "228 Кабинет философии и украинознавтсва", "229 Профком студентов", "230 Музей ОНАС", "231 Кафедра экономической теории",
                "232 Заведующий кафедрой философии и истории Украины", "233 Заведующий кафедрой сети связи", "234", "235 Заведующий научно-иследовательским отделом",

                // 3rd floor
                "301 Подготовительное отделение для иностранных граждан", "302 Проректор с международных отношений", "303 Международный отдел",
                "304 Кабинет украинского языка и краеведчества", "305 Подготовительное отделение для иностранных граждан. Компьютерный класс",
                "306 Кабинет природоведческих наук", "307 Кабинет географии и истории", "308 Центр такжикской культуры", "309 Кабинет украинского языка и культуры",
                "310 Кафедра лингвистической подготовки", "311 Кабинет украинской и зарубежной литературы", "312-A", "312", "313", "315 ИЕМ. Заочное отделение", "316 Институт экономики и менеджмента",
                "317", "318", "319 Кафедра МтаМ Лаборатория 1",
                "320 Заведующий кафедрой менеджмента и маркетинга", "321", "322", "323", "324 Кафедра МтаМ", "325 Кафедра экономики предпринимательства и корпоративного управления",
                "325-A", "326 Центр правовой защиты студентов/Кафедра политологии, социологии и социальных коммуникаций", "328 ННИ Проблем информационного сообщества",
                "329 Проректор по научно-педагогической и воспитательной работе, директор ННИ Проблем информационного общества",
                "330", "331 Кафедра управления проектами и системного анализа", "332 Отдел социально-психологической и воспитательной работы/Центр психологического консультирования",
                "333 Заведующий кафедры экономики предприятия и корпоративного управления", "334 Кафедра физвоспитания", "335",

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
