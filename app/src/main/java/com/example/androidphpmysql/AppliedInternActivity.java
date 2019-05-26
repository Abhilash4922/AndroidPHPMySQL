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

public class AppliedInternActivity extends AppCompatActivity {
    public String cid=ProfileActivity.cid1;
    private static String APPLIED_INTERN="http://192.168.43.129/android/v1/appliedintern.php";
    RecyclerView recyclerView;
    AppliedInternAdapter appliedInternAdapter;
    List<AppliedIntern> appliedInternList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_intern);
        appliedInternList=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recylcerViewAppliedIntern);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       loadAppliedIntern();
    }
    private void loadAppliedIntern(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, APPLIED_INTERN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String intern_title=jsonObject.getString("intern_name");
                    String intern_status=jsonObject.getString("status");
                    AppliedIntern appliedIntern=new AppliedIntern(intern_title,intern_status);
                    appliedInternList.add(appliedIntern);
                }

                    appliedInternAdapter=new AppliedInternAdapter(AppliedInternActivity.this,appliedInternList);
                    recyclerView.setAdapter(appliedInternAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AppliedInternActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
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
