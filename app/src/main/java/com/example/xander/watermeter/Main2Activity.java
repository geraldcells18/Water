package com.example.xander.watermeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class Main2Activity extends AppCompatActivity {

    long BackPress_Iden;

    RequestQueue request_queue;
    StringRequest string_request;

    String url = "http://192.168.43.80/myprojects/Water/assets/data.json";

    String data = "0";

    private View.OnClickListener refresh_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            getData();
        }
    };
    private View.OnClickListener logout_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cells cells = new Cells();
            cells.changeActivity(Main2Activity.this, MainActivity.class, false);
        }
    };

    public void getData() {

        request_queue = Volley.newRequestQueue(Main2Activity.this);

        string_request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ProgressBar pb = (ProgressBar) findViewById(R.id.progressBarMain);
                TextView pb_text = (TextView) findViewById(R.id.progressBarText);

                long data = Integer.parseInt(parseJSON(response));
                pb.setProgress((int) data);
                pb_text.setText(String.valueOf(data));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Response :" + error.getCause(), Toast.LENGTH_SHORT).show();
            }
        });

        request_queue.add(string_request);
    }

    String parseJSON(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            data = obj.get("Liters").toString();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Response :" + ex.getCause(), Toast.LENGTH_SHORT).show();
        }
        return data;
    }


    @Override
    public void onBackPressed() {
        if (BackPress_Iden + 1000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(Main2Activity.this, "Press once again to exit", Toast.LENGTH_SHORT).show();
        }
        BackPress_Iden = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);
        refresh.setOnClickListener(refresh_click);

        RelativeLayout logout = (RelativeLayout) findViewById(R.id.logout_menu);
        logout.setOnClickListener(logout_listener);
    }
}
