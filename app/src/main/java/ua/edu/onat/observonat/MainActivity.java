package ua.edu.onat.observonat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createBlur(R.id.maps_container);
        createBlur(R.id.account_container);
        createBlur(R.id.teacher_container);
        createBlur(R.id.library_container);
        BlurView maps_container = findViewById(R.id.maps_container);
        maps_container.setOnClickListener(v -> {
            Intent data = new Intent(getBaseContext(), MapsActivity.class);
            startActivity(data);
        });
    }
    void createBlur(int id)
    {
        float radius = 5;
        BlurView maps_container = findViewById(id);
        maps_container.setupWith(findViewById(R.id.main_activity_content))
                .blurAlgorithm(new RenderScriptBlur(this))
                .blurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }

}
