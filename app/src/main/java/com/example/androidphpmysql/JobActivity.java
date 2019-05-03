package com.example.androidphpmysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JobActivity extends AppCompatActivity {
    private static final String URL_DATA="http://192.168.0.105/android/v1/job.php";
    private String jtitle;
    private String link;
    private String description;
private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private List<JobActivity>jobActivities;
    private static JobActivity JobInstance;
    private static Context mCtx;
    public JobActivity(String jtitle, String link, String description) {
        this.jtitle = jtitle;
        this.link = link;
        this.description = description;
}

    private JobActivity(Context context) {
        mCtx = context;

    }


    public String getJtitle() {
        return jtitle;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_job);
        recyclerView=(RecyclerView)findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobActivities=new ArrayList<>();
loadRecyclerViewData();
    }
private void loadRecyclerViewData(){
    ProgressDialog progressDialog=new ProgressDialog(this);
    progressDialog.setMessage("Loading Jobs...");
    progressDialog.show();
    StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONArray jsonObject=new JSONArray(response);
                for(int i=0;i<jsonObject.length();i++){
                    JSONObject obj=jsonObject.getJSONObject(i);
                    String title=obj.getString("title");
                    String description=obj.getString("discription");
                    String link=obj.getString("link");
                    JobActivity jobActivity=new JobActivity(title,description,link);
                    jobActivities.add(jobActivity);
                }
                adapter=new JobAdapter(JobActivity.this,jobActivities);
                } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(JobActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });
    RequestQueue requestQueue= Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.menuJobs:
                startActivity(new Intent(this,JobActivity.class));
                break;
            case R.id.menuInternship:
                startActivity(new Intent(this,InternActivity.class));
                break;
        }
        return true;
    }
}
