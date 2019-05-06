package com.example.androidphpmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText editTextName,editTextUserName,editTextEmail,editTextPassword;
private Button buttonRegister;
private ProgressDialog progressDialog;
private TextView textViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextUserName=(EditText)findViewById(R.id.editTextUsername);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        progressDialog=new ProgressDialog(this);
         buttonRegister.setOnClickListener((View.OnClickListener) this);
         textViewLogin=(TextView)findViewById(R.id.textViewLogin);
         textViewLogin.setOnClickListener((View.OnClickListener) this);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }
    }
    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String username = editTextUserName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.setMessage("registering user..");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
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
                params.put("email", email);
                params.put("name", name);
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
        public void onClick(View view){
        if(view==buttonRegister)
            registerUser();
        if(view==textViewLogin)
            startActivity(new Intent(this,LoginActivity.class));
    }
}

