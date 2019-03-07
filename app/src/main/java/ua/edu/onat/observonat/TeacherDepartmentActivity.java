package ua.edu.onat.observonat;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import ua.edu.onat.observonat.Helpers.DepartmentAdapter;
import ua.edu.onat.observonat.Helpers.DepartmentItem;

public class TeacherDepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_department);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //wpb_column vc_column_container vc_col-sm-8
        String url ="https://onat.edu.ua/instituti/ik-and-pi/volz/sklad-kafedry_volz/";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements header = doc.select("header");
            header.attr("style", "display:none");
            Elements pageheading = doc.select(".page-heading");
            pageheading.attr("style", "display:none");
            WebView webview = findViewById(R.id.webview_teacher_department);
            webview.loadData(doc.html(), "text/html", "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
