package com.example.androidphpmysql;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppliedJobAdapter extends RecyclerView.Adapter<AppliedJobAdapter.AppliedJobViewHolder> {
    private static String JOB_MESSAGE="http://192.168.43.129/android/v1/jobmessage.php";
    private Context mct;
    private List<AppliedJob> appliedJobList;
    public String cid=ProfileActivity.cid1;
    public static String ji;
    public AppliedJobAdapter(Context mct, List<AppliedJob> appliedJobList) {
        this.mct = mct;
        this.appliedJobList = appliedJobList;
    }

    @NonNull
    @Override
    public AppliedJobViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mct);
        View view=layoutInflater.inflate(R.layout.appliedjob,null);
        AppliedJobViewHolder appliedJobViewHolder=new AppliedJobViewHolder(view);
        return appliedJobViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppliedJobViewHolder appliedJobViewHolder, int i) {
       final AppliedJob appliedJob = appliedJobList.get(i);
        appliedJobViewHolder.textViewAppliedJobTitle.setText(appliedJob.getTitle());
        appliedJobViewHolder.textViewAppliedJobStatus.setText(appliedJob.getStatus());
        appliedJobViewHolder.textViewAppliedJid.setText(appliedJob.getJid());
        ji = appliedJob.getJid();
        appliedJobViewHolder.textViewAppliedCid.setText(cid);
        appliedJobViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMessage();
            }

            private void loadMessage() {
                final String jid = appliedJob.getJid();
                final String cid1 = cid;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, JOB_MESSAGE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String time = jsonObject.getString("created_at");
                                String message = jsonObject.getString("message");

                                Intent myIntent = new Intent(mct, JobMessageActivity.class);
                                myIntent.putExtra("message",message ); //Optional parameters
                                myIntent.putExtra("time",time ); //Optional parameters

                                mct.getApplicationContext().startActivity(myIntent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mct.getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("jid", String.valueOf(jid));
                        params.put("cid", String.valueOf(cid1));
                        return params;
                    }
                };
                RequestHandler.getInstance(mct).addToRequestQueue(stringRequest);
            }
        });

    }
    @Override
    public int getItemCount() {
        return appliedJobList.size();

    }

    class AppliedJobViewHolder extends RecyclerView.ViewHolder{
TextView textViewAppliedJobTitle,textViewAppliedJobStatus,textViewAppliedJid,textViewAppliedCid;
Button button;
    public AppliedJobViewHolder(@NonNull View itemView) {
        super(itemView);
        button=itemView.findViewById(R.id.buttonViewJobMessage);
        textViewAppliedJobTitle=itemView.findViewById(R.id.textViewAppliedJobTitle);
        textViewAppliedJobStatus=itemView.findViewById(R.id.textViewAppliedJobStatus);
textViewAppliedJid=itemView.findViewById(R.id.textViewAppliedJid);
        textViewAppliedCid=itemView.findViewById(R.id.textViewAppliedCid);
    }
}
}
