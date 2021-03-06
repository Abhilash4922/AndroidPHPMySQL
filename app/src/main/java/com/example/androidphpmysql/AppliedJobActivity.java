package com.example.androidphpmysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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

public class AppliedJobActivity extends AppCompatActivity {
    public String cid=ProfileActivity.cid1;
   public static String jid1;

    private static String APPLIED_JOB="http://192.168.43.129/android/v1/appliedjob.php";
    RecyclerView recyclerView;
    AppliedJobAdapter appliedJobAdapter;
    List<AppliedJob> appliedJobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_job);
        appliedJobList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recylcerViewAppliedJob);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadAppliedJob();
    }
    private void loadAppliedJob(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, APPLIED_JOB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String title=jsonObject.getString("job_name");
                        String status=jsonObject.getString("status");
                        String jid=jsonObject.getString("jid");
                        AppliedJob appliedJob=new AppliedJob(title,status,jid);
                        appliedJobList.add(appliedJob);
                    }

                    appliedJobAdapter=new AppliedJobAdapter(AppliedJobActivity.this,appliedJobList);
                    recyclerView.setAdapter(appliedJobAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AppliedJobActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cid",String.valueOf(cid));
                return params;
            }
        };
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
            case R.id.menuAppliedJobs:
                startActivity(new Intent(this,AppliedJobActivity.class));
                break;
            case R.id.menuAppliedInterns:
                startActivity(new Intent(this,AppliedInternActivity.class));
        }
        return true;
    }
}
