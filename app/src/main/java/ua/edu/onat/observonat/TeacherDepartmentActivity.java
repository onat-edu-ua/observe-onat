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
        String dept_name = getIntent().getStringExtra("dept_name");
        String url ="https://onat.edu.ua/instituti/ik-and-pi/volz/sklad-kafedry_volz/";
        switch(dept_name) {
            case "Кафедра ВОЛС":
                url = "https://onat.edu.ua/instituti/ik-and-pi/volz/sklad-kafedry_volz/";
                break;
            case "Кафедра высшей математики":
                url = "https://onat.edu.ua/instituti/ik-and-pi/vm/sklad-kafedry_vm/";
                break;
            case "Кафедра коммутационных систем":
                url = "https://onat.edu.ua/instituti/ik-and-pi/ks/sklad-kafedry_ks/";
                break;
            case "Кафедра информационных технологий":
                url = "https://onat.edu.ua/instituti/ik-and-pi/kafedra_it/sklad-kafedry-it/";
                break;
            case "Кафедра сетей связи":
                url = "https://onat.edu.ua/instituti/ik-and-pi/mz/sklad-kafedry/";
                break;
            case "Кафедра телекоммуникационных систем":
                url = "https://onat.edu.ua/instituti/ik-and-pi/tks/sklad-kafedry/";
                break;
            case "Кафедра компьютерных наук":
                url = "https://onat.edu.ua/instituti/ik-and-pi/kafedra-kompjuternih-nauk/";
                break;
            case "Кафедра телевидения и радиовещания":
                url = "https://onat.edu.ua/instituti/rt-and-ib/tv/sotrudnyky_kafedry/";
                break;
            case "Кафедра ТЭС":
                url = "https://onat.edu.ua/instituti/rt-and-ib/tez/memvers-of-tez/";
                break;
            case "Кафедра ТЭД":
                url = "https://onat.edu.ua/instituti/rt-and-ib/sklad-kafedri/";
                break;
            case "Кафедра ИБ и ПД":
                url = "https://onat.edu.ua/instituti/rt-and-ib/mss/sklad-kafedri/";
                break;
            case "Кафедра КИТПиВ":
                url = "https://onat.edu.ua/instituti/kt/kitp-and-v/sklad-kafedri-kitp-and-v/";
                break;
            case "Кафедра физического воспитания":
                url = "https://onat.edu.ua/instituti/kt/fz/fiz-sklad-kafedry/";
                break;
            case "Кафедра АТП":
                url = "https://onat.edu.ua/instituti/kt/avtomatizacija-tehnologichnih-procesiv-ta-elektrozhivlennja/cklad-kafedri_atp-ta-e/";
                break;
            case "Кафедра ЭТ и УП":
                url = "https://onat.edu.ua/instituti/e-and-m/kafedra-ekonomichnoi-teorii-ta-upravlinnja-proektami/spivrobitniki-kafedri-et-ta-up/";
                break;
            case "Кафедра экономики предприятия":
                url = "https://onat.edu.ua/instituti/e-and-m/ep-and-ku/sklad-kafedry_ep-and-ku/";
                break;
            case "Кафедра менеджмента и маркетинга":
                url = "https://onat.edu.ua/instituti/e-and-m/m-and-m/sklad-kafedri_m-and-m/";
                break;
            case "Кафедра политологии и социологии":
                url = "https://onat.edu.ua/instituti/problem-informatsijnogo-suspilstva/kaf-politologiyi-sotsiologiyi-ta-sotsial/sklad-kafedri_polsotsial/";
                break;
            case "Кафедра лингвистической подготовки":
                url = "https://onat.edu.ua/instituti/problem-informatsijnogo-suspilstva/kaf-lingvistychnoyi-pidgotovky/";
                break;
            case "Кафедра философии и истории Украины":
                url = "https://onat.edu.ua/instituti/problem-informatsijnogo-suspilstva/f-and-u/sklad-kafedri_f-and-u/";
                break;
            case "Кафедра заочного обучения":
                url = "https://onat.edu.ua/instituti/instytut-dystantsijnogo-ta-zaochnogo-na/";
                break;
        }
        try {
            Document doc = Jsoup.connect(url).get();
            Log.v("contents", doc.html());
            Elements header = doc.select("header");
            header.attr("style", "display:none");
            Elements pageheading = doc.select(".page-heading");
            pageheading.attr("style", "display:none");
            Elements footer = doc.select("footer");
            footer.attr("style", "display:none");
            Elements navigationbottom = doc.select(".wpb_column.vc_column_container.vc_col-sm-4");
            navigationbottom.attr("style", "display:none");
            WebView webview = findViewById(R.id.webview_teacher_department);
            webview.loadData(doc.html(), "text/html", "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
