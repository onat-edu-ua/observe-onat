package ua.edu.onat.observonat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import ua.edu.onat.observonat.Helpers.NDSpinner;
import java.util.ArrayList;
import java.util.Arrays;
import ua.edu.onat.observonat.Helpers.CustomSpinnerAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import ua.edu.onat.observonat.Helpers.CustomAttrsFactory;
import android.content.SharedPreferences;
import android.support.v4.view.LayoutInflaterCompat;
import android.content.Intent;

public class SettingsActivity extends AppCompatActivity {
    boolean onload_theme = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("ONATSettings", 0);
        Integer currTheme = pref.getInt("theme", R.style.FullScreen);
        if(currTheme!=R.style.FullScreen)
            setTheme(currTheme);
        NDSpinner themeSelector = findViewById(R.id.theme_selector);

        final String[] themesArray = getResources().getStringArray(R.array.theme_array);
        ArrayList<String> listThemes = new ArrayList<>(Arrays.asList(themesArray));
        ArrayAdapter<CharSequence> customSpinnerAdapter = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_dropdown_item, listThemes);
        customSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSelector.setAdapter(customSpinnerAdapter);
        onload_theme = true;
        final Integer[] themesList = {R.style.FullScreen, R.style.LightTheme};
        if(currTheme!=R.style.FullScreen)
            themeSelector.setSelection(Arrays.asList(themesList).indexOf(currTheme));
        themeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(onload_theme)
                {
                    onload_theme = !onload_theme;
                    return;
                }
                writeToPreferences("theme", themesList[i]);
                Intent refresh = getIntent();
                refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(refresh);

                getApplication().setTheme(themesList[i]);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
            void writeToPreferences(String key, Object value)
            {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("ONATSettings", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                if(value.getClass().equals(String.class))
                    editor.putString(key, (String)value);
                else if(value.getClass().equals(Integer.class))
                    editor.putInt(key, (Integer)value);
                editor.apply();
            }

        });
}}

