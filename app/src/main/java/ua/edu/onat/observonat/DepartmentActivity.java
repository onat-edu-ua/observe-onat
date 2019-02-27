package ua.edu.onat.observonat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ua.edu.onat.observonat.Helpers.methodicalAdapter;
import ua.edu.onat.observonat.Helpers.methodicalItem;

public class DepartmentActivity extends AppCompatActivity {
    int REQUEST_WRITE_DATA = 2345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        ListView methods = findViewById(R.id.methods);
        RequestQueue queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        String valueid = intent.getStringExtra("departmentId");
        Log.v("valueId", valueid);
        if (valueid.equals(null)) {
            valueid = "0";
        }
        String url ="https://metod.onat.edu.ua/metods?search_query=&department_id=" + valueid;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray jArray = new JSONArray(response);
                        ArrayList<methodicalItem> methodicalItems = new ArrayList<>();

                        for(int i=0;i<jArray.length();i++) {
                            methodicalItems.add(new methodicalItem(jArray.getJSONObject(i).getString("name"), jArray.getJSONObject(i).getString("download_url")));
                        }

                        methodicalAdapter mAdapter = new methodicalAdapter(methodicalItems, this);

                        methods.setAdapter(mAdapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }, error -> Log.v("Error","That didn't work!"));

        queue.add(stringRequest);

        methods.setOnItemClickListener((adapterView, view, i, l) -> {
            String methodUrl = ((TextView)view.findViewById(R.id.methodicalbook_url)).getText().toString();
            // запрос на запись на карту памяти
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_DATA);
            }
            // разрешение дано на запись
            else {

            }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_WRITE_DATA) {
            Log.v("Save", "there");
        }
    }
}

