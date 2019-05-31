package com.example.androidphpmysql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobMessageActivity extends AppCompatActivity {
    public String cid= ProfileActivity.cid1;
    private static String JOB_MESSAGE="http://192.168.43.129/android/v1/jobmessage.php";
    RecyclerView recyclerView;
    AppliedJobMessageAdapter appliedJobMessageAdapter;
    List<AppliedJobMessage> appliedJobMessageList;
    public String jid= AppliedJobActivity.jid1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_message);
        appliedJobMessageList=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewJobMessage);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMessage();
    }

    private void loadMessage() {
        final String ci=cid;
        final String ji=AppliedJobActivity.jid1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JOB_MESSAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String time = jsonObject.getString("created_at");
                        String message = jsonObject.getString("message");
                        AppliedJobMessage appliedJobMessage = new AppliedJobMessage(message,time);
                        appliedJobMessageList.add(appliedJobMessage);
                    }

                    appliedJobMessageAdapter = new AppliedJobMessageAdapter(JobMessageActivity.this, appliedJobMessageList);
                    recyclerView.setAdapter(appliedJobMessageAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JobMessageActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cid", ci);
                params.put("jid","2");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    }
