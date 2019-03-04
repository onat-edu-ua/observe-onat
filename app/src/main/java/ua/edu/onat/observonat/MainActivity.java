package ua.edu.onat.observonat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.SharedPreferences;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import ua.edu.onat.observonat.Helpers.CustomAttrsFactory;
import android.support.v4.view.LayoutInflaterCompat;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("ONATSettings", 0);
        int currTheme = pref.getInt("theme", R.style.FullScreen);
        if(currTheme!=R.style.FullScreen)
            setTheme(currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createView(R.id.maps_container, MapsActivity.class);
        createView(R.id.account_container, AccountActivity.class);
        createView(R.id.teacher_container, TeachersActivity.class);
        createView(R.id.library_container, LibraryActivity.class);
        findViewById(R.id.settings).setOnClickListener(v-> {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        });
    }

    void createView(int id, Class<?> activity)
    {
        float radius = 5;
        BlurView container = findViewById(id);
        container.setupWith(findViewById(R.id.main_activity_content))
                .blurAlgorithm(new RenderScriptBlur(this))
                .blurRadius(radius)
                .setHasFixedTransformationMatrix(true);
        container.setOnClickListener(v -> {
            Intent data = new Intent(getBaseContext(), activity);
            startActivity(data);
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("theme")) {
            recreate();
        }


    }
}
