package ua.edu.onat.observonat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ListView cafedras = findViewById(R.id.cafedras);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://metod.onat.edu.ua/departments";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        List<String> departmentsStrings = new ArrayList<>();
                        JSONObject jObject = new JSONObject(response);
                        JSONArray departments = jObject.getJSONArray("departments");
                        for(int i=0;i<departments.length();i++) {
                            departmentsStrings.add(((JSONObject)departments.get(i)).getString("name"));
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                android.R.layout.simple_list_item_1, departmentsStrings);
                        cafedras.setAdapter(adapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }, error -> Log.v("Error","That didn't work!"));

        queue.add(stringRequest);
    }
}
