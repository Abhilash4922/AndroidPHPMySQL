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

public class InternActivity extends AppCompatActivity {
    private static final String URL_DATA="http://192.168.1.2/android/v1/intern.php";
    RecyclerView recyclerView;
    InternAdapter internAdapter;

    private List<Intern>internList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern);
        internList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewIntern);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadIntern();

    }
    private void loadIntern(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj1=jsonArray.getJSONObject(i);
                        String ititle=obj1.getString("ititle");
                        String idescription=obj1.getString("idescription");
                        String ilink=obj1.getString("ilink");
                        Intern intern=new Intern(ititle,idescription,ilink);
                        internList.add(intern);
                    }

                    internAdapter = new InternAdapter(InternActivity.this, internList);
                    recyclerView.setAdapter(internAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InternActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
        }
        return true;
    }
}
