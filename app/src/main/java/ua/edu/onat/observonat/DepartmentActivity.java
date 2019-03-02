package ua.edu.onat.observonat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.edu.onat.observonat.Helpers.methodicalAdapter;
import ua.edu.onat.observonat.Helpers.methodicalItem;

public class DepartmentActivity extends AppCompatActivity {
    int REQUEST_WRITE_DATA = 2345;
    String methodUrl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        ListView methods = findViewById(R.id.methods);
        RequestQueue queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        String valueid = intent.getStringExtra("departmentId");
        if (valueid.equals(null)) valueid = "0";
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
            methodUrl = ((TextView)view.findViewById(R.id.methodicalbook_url)).getText().toString();
            // запрос на запись на карту памяти
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_WRITE_DATA);
            }
            // разрешение дано на запись
            else {
                if(methodUrl!=null)
                    new DownloadFile().execute(methodUrl);
            }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_WRITE_DATA) {
            if(methodUrl!=null)
                new DownloadFile().execute(methodUrl);
        }
    }
    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(DepartmentActivity.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                Pattern pattern = Pattern.compile("(?<=filename=\")(.*)(?=\")");

                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                String tmpfileName =  connection.getHeaderField("Content-Disposition");
                Matcher m = pattern.matcher(tmpfileName);
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length()) + ".pdf";
                if(m.find())
                    fileName = m.group(0);

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory() + File.separator + "observonat/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            Toast.makeText(getApplicationContext(),
                    message, Toast.LENGTH_LONG).show();

        }

    }
}