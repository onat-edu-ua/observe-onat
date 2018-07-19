package ua.edu.onat.observonat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

}
