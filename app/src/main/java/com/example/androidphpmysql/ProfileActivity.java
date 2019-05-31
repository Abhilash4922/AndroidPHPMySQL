package com.example.androidphpmysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
public static String cid1;
    public static String name;
    public static String resume;

    private TextView textViewName, textViewUserName, textViewEmail, textViewUserResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        textViewUserResume = (TextView) findViewById(R.id.textViewCandidateResume);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewName.setText(SharedPrefManager.getInstance(this).getName());
        textViewUserResume.setText(SharedPrefManager.getInstance(this).getCId());
        textViewUserName.setText(SharedPrefManager.getInstance(this).getUsername());
        textViewEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textViewUserResume.setText(SharedPrefManager.getInstance(this).getResume());
    cid1=SharedPrefManager.getInstance(this).getCId();
    name=SharedPrefManager.getInstance(this).getName();
    resume=SharedPrefManager.getInstance(this).getResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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