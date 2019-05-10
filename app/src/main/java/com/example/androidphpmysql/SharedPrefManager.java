package com.example.androidphpmysql;
import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {


    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String KEY_USER_NAME ="name" ;
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_RESUME = "resume";
    private static final String KEY_JOB_ID ="jid" ;
    private static final String KEY_USER_JOB_ID ="user_job_id" ;
    private static final String KEY_USER_JOB_NAME ="job_name" ;
    private static final String SHARED_PREF_NAME_JOB = "mysharedprefjob";

    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);

        }
        return mInstance;
    }

    public boolean userLogin(int id,String name, String username, String email){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NAME, name);
        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, username);

        editor.apply();

        return true;
    }

    public boolean applliedJob(int jid,int userid, String job_name){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_JOB, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JOB_ID, String.valueOf(jid));
        editor.putInt(KEY_USER_JOB_ID, userid);
        editor.putString(KEY_USER_JOB_NAME, job_name);


        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME, null);
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public String getUserResume(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_RESUME, null);
    }
    public String getKeyJobId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_JOB, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_JOB_ID, null);
    }
    public String getKeyUserJobId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_JOB, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_JOB_ID, null);
    }
    public String getKeyUserJobName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME_JOB, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_JOB_NAME, null);
    }



}