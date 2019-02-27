package ua.edu.onat.observonat;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        ListView methods = findViewById(R.id.methods);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://metod.onat.edu.ua/metods?search_query=&department_id=0";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray jArray = new JSONArray(response);
                        ArrayList<methodicalItem> methodicalItems = new ArrayList<methodicalItem>();
                        Intent intent = getIntent();
                        String valueid = intent.getStringExtra("departmentId");
                        if (!valueid.equals(null)) {
                            System.out.printf(valueid);
                        }

                        for(int i=0;i<jArray.length();i++) {
                            if (jArray.getJSONObject(i).getString("department_id").equals(valueid)) {
                                methodicalItems.add(new methodicalItem(jArray.getJSONObject(i).getString("name"), jArray.getJSONObject(i).getString("download_url")));
                            }

                        }

                        methodicalAdapter mAdapter = new methodicalAdapter(methodicalItems, this);

                        methods.setAdapter(mAdapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }, error -> Log.v("Error","That didn't work!"));

        queue.add(stringRequest);

        methods.setOnItemClickListener((adapterView, view, i, l) -> {
            String metho = ((TextView)view.findViewById(R.id.methodicalbook_url)).getText().toString();
            Intent data = new Intent(getBaseContext(), methodicalbookActivity.class);
            Bundle b = new Bundle();
            b.putString("metho", metho);
            data.putExtras(b);
            startActivity(data);
        });
    }

}

