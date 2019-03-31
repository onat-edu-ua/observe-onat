package ua.edu.onat.observonat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class TeachersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("ONATSettings", 0);
        int currTheme = pref.getInt("theme", R.style.FullScreen);
        if(currTheme!=R.style.FullScreen)
            setTheme(currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        ListView teacher_departments_list = findViewById(R.id.teacher_departments_list);
        String[] teachers_deps = {"Кафедра ВОЛС", "Кафедра высшей математики", "Кафедра коммутационных систем", "Кафедра информационных технологий",
        "Кафедра сетей связи", "Кафедра телекоммуникационных систем", "Кафедра компьютерных наук", "Кафедра телевидения и радиовещания", "Кафедра ТЭС",
        "Кафедра ТЭД", "Кафедра ИБ и ПД", "Кафедра КИТПиВ", "Кафедра физического воспитания", "Кафедра АТП", "Кафедра ЭТ и УП", "Кафедра экономики предприятия",
        "Кафедра менеджмента и маркетинга", "Кафедра политологии и социологии", "Кафедра лингвистической подготовки", "Кафедра философии и истории Украины",
        "Кафедра заочного обучения"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teachers_deps);
        teacher_departments_list.setAdapter(adapter);
        teacher_departments_list.setOnItemClickListener(
                (adapterView, view, i, l) -> {
                    Intent intent = new Intent(TeachersActivity.this, TeacherDepartmentActivity.class);
                    String dept_name = (String)adapterView.getItemAtPosition(i);
                    intent.putExtra("dept_name", dept_name);
                    startActivity(intent);
                }
        );
    }
}
