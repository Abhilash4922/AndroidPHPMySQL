package com.example.androidphpmysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.util.List;
import java.util.Map;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private List<Job>jobList;
    private Context context;
public ProgressDialog progressDialog;
    public JobAdapter( Context context,List<Job>jobs) {
        this.jobList = jobs;
        this.context = context;
    }
    public String cid=ProfileActivity.cid1;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.job,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Job job=jobList.get(i);
        viewHolder.textViewTitle.setText(job.getJobTitle());
        viewHolder.textViewDescription.setText(job.getDescription());
        viewHolder.textViewLink.setText(job.getLink());
        viewHolder.textViewJobId.setText(""+job.getId());
        viewHolder.textViewUserId.setText(""+job.getUser_id());
        viewHolder.textViewCId.setText(cid);
        viewHolder.jobApply.setOnClickListener(new View.OnClickListener() {

            @Override
        public void onClick(View v) {
                applyJob();
            }
            private void applyJob() {
                final String title=job.getJobTitle();
                final String description=job.getDescription();
                final String link=job.getLink();
                final int jid=job.getId();
                final int userid=job.getUser_id();
                final String cid1=cid;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_JOBAPPLY, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context.getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("jid",String.valueOf(jid));
                        params.put("job_name", title);
                        params.put("userid", String.valueOf(userid));
                        params.put("cid",String.valueOf(cid1));
                        return params;
                    }
                };
                RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
            }
    });
    }
    @Override
    public int getItemCount() {
        return jobList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewLink;
        public TextView textViewJobId;
        public TextView textViewUserId;
        public TextView textViewCId;
public LinearLayout linearLayout;
public Button jobApply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCId=itemView.findViewById(R.id.textViewCID);
            jobApply=itemView.findViewById(R.id.buttonJobApply);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDescription=itemView.findViewById(R.id.textViewDiscription);
            textViewLink=itemView.findViewById(R.id.textViewLink);
            textViewUserId=itemView.findViewById(R.id.textViewUserId);
            textViewJobId=itemView.findViewById(R.id.textViewJobId);
        linearLayout=itemView.findViewById(R.id.linearLayout);
        }

    }
}
