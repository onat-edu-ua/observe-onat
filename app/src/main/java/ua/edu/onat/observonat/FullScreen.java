package ua.edu.onat.observonat;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import ua.edu.onat.observonat.Helpers.TouchImageView;

public class FullScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        Bundle b = getIntent().getExtras();
        int idOfResource = R.drawable.ic_floor_1;
        TouchImageView floorFullScreen = findViewById(R.id.imageFloorFullScreen);
        floorFullScreen.setImageResource(idOfResource);
        floorFullScreen.setMaxZoom(6);
        floorFullScreen.setMinZoom(1);

        RadioButton redRadioButton = (RadioButton)findViewById(R.id.radioFloor1);
        redRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton greenRadioButton = (RadioButton)findViewById(R.id.radioFloor2);
        greenRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton blueRadioButton = (RadioButton)findViewById(R.id.radioFloor3);
        blueRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton grayRadioButton = (RadioButton)findViewById(R.id.radioFloor4);
        grayRadioButton.setOnClickListener(radioButtonClickListener);

    }
    View.OnClickListener radioButtonClickListener = v -> {
        RadioButton rb = (RadioButton)v;
        TouchImageView floorFullScreen = findViewById(R.id.imageFloorFullScreen);
        switch (rb.getId()) {
            case R.id.radioFloor1:
                floorFullScreen.setImageResource(R.drawable.ic_floor_1);
                break;
            case R.id.radioFloor2:
                floorFullScreen.setImageResource(R.drawable.ic_floor_2);
                break;
            case R.id.radioFloor3:
                floorFullScreen.setImageResource(R.drawable.ic_floor_3);
                break;
            case R.id.radioFloor4:
                floorFullScreen.setImageResource(R.drawable.ic_floor_4);
                break;

            default:
                break;
        }
    };
}
