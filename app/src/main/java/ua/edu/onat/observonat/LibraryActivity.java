package ua.edu.onat.observonat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ua.edu.onat.observonat.Helpers.DepartmentAdapter;
import ua.edu.onat.observonat.Helpers.DepartmentItem;

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
                        JSONObject jObject = new JSONObject(response);
                        JSONArray departments = jObject.getJSONArray("departments");
                        ArrayList<DepartmentItem> departmentItems = new ArrayList<DepartmentItem>();

                        for(int i=0;i<departments.length();i++) {
                            departmentItems.add(new DepartmentItem(((JSONObject)departments.get(i)).getString("name"),
                                    ((JSONObject)departments.get(i)).getString("id")));
                        }

                        DepartmentAdapter dAdapter = new DepartmentAdapter(departmentItems, this);

                        cafedras.setAdapter(dAdapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }, error -> Log.v("Error","That didn't work!"));

        queue.add(stringRequest);

        cafedras.setOnItemClickListener((adapterView, view, i, l) -> {
            String departmentId = ((TextView)view.findViewById(R.id.id_of_department)).getText().toString();
            Intent data = new Intent(getBaseContext(), DepartmentActivity.class);
            Bundle b = new Bundle();
            b.putString("departmentId", departmentId);
            data.putExtras(b);
            startActivity(data);
        });
        Button searchButton = findViewById(R.id.search_methods_button);
        searchButton.setOnClickListener(view -> {
            EditText searchQuery = findViewById(R.id.methodical_string_search);
            Intent data = new Intent(getBaseContext(), DepartmentActivity.class);
            Bundle b = new Bundle();
            b.putBoolean("is_query_string", true);
            b.putString("query_string", searchQuery.getText().toString());
            data.putExtras(b);
            startActivity(data);
        });
    }
}
