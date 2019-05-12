package com.example.androidphpmysql;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Job extends AppCompatActivity  implements View.OnClickListener {

    private String title;
    private String description;
    private String link;
    private int id;
    private int user_id;

    public Job(String title, String description, String link,int id,int user_id) {

        this.title = title;
        this.description = description;
        this.link = link;
        this.id=id;
        this.user_id=user_id;
    }
    public int getId(){
        return id;
    }

    public int getUser_id(){
        return user_id;
    }

    public String getJobTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    @Override
    public void onClick(View v) {
        if (v == buttonJobApply)
            applyJob();
    }
    private TextView textViewTitle,textViewJobId,textViewUserId,textViewName;
    private Button buttonJobApply;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job);
        buttonJobApply = (Button) findViewById(R.id.buttonJobApply);
        progressDialog = new ProgressDialog(this);
        buttonJobApply.setOnClickListener((View.OnClickListener) this);
        textViewTitle=(TextView)findViewById(R.id.textViewTitle);
        textViewJobId=(TextView)findViewById(R.id.textViewJobId);
        textViewUserId=(TextView)findViewById(R.id.textViewUserId);
        textViewName=(TextView)findViewById(R.id.textViewName);
    }

    private void applyJob() {
        final String name=textViewName.getText().toString();
        final String job_name = textViewTitle.getText().toString();
        final String jid = textViewJobId.getText().toString();
        final String userid = textViewUserId.getText().toString();


        progressDialog.setMessage("Applying Job..");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_JOBAPPLY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("jid",jid);
                params.put("job_name", job_name);
                params.put("name",name);
                params.put("userid", userid);


                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
