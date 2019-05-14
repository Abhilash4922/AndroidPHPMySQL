package com.example.androidphpmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
public class ProfileActivity extends AppCompatActivity {
    private TextView textViewName,textViewUserName,textViewEmail,textViewUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        textViewUserId=(TextView)findViewById(R.id.textViewCandidateId);
        textViewName=(TextView)findViewById(R.id.textViewName);
        textViewUserName=(TextView)findViewById(R.id.textViewUserName);
        textViewEmail=(TextView)findViewById(R.id.textViewEmail);
        textViewName.setText(SharedPrefManager.getInstance(this).getName());
        textViewUserId.setText(SharedPrefManager.getInstance(this).getCId());
        textViewUserName.setText(SharedPrefManager.getInstance(this).getUsername());
        textViewEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
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
