package com.example.androidphpmysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
private Button buttonHomeLogin;
private Button buttonHomeSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonHomeLogin=(Button)findViewById(R.id.buttonHomeLogin);
        buttonHomeLogin.setOnClickListener((View.OnClickListener) this);

        buttonHomeSignUp=(Button)findViewById(R.id.buttonHomeSignUp);
        buttonHomeSignUp.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSignUp:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.menuLogIn:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
        return true;
    }
    public void onClick(View view){
        if(view==buttonHomeLogin)
            startActivity(new Intent(this,LoginActivity.class));
        if(view==buttonHomeSignUp)
            startActivity(new Intent(this,MainActivity.class));
    }
}

