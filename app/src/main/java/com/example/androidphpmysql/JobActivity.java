package com.example.androidphpmysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
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
    private static final String URL_DATA="http://192.168.43.129/android/v1/job.php";
 RecyclerView recyclerView;
 JobAdapter adapter;

private List<Job>jobList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        jobList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadJob();
    }
private void loadJob(){
    StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONArray products=new JSONArray(response);
            for(int i=0;i<products.length();i++){
                JSONObject obj=products.getJSONObject(i);
                int user_id=obj.getInt("user_id");
                int id=obj.getInt("id");
                String title=obj.getString("title");
                String description=obj.getString("description");
                String link=obj.getString("link");
                Job job=new Job(title,description,link,id,user_id);
                jobList.add(job);
            }

                adapter = new JobAdapter(JobActivity.this, jobList);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(JobActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });
    Volley.newRequestQueue(this).add(stringRequest);
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
            case R.id.menuProfile:
                startActivity(new Intent(this,ProfileActivity.class));
                break;
        }
        return true;
    }
    }
