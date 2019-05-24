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

public class InternAdapter extends RecyclerView.Adapter<InternAdapter.ViewHolder> {
    private List<Intern>internList;
    private Context context;
    public ProgressDialog progressDialog;
    public InternAdapter( Context context,List<Intern>interns) {
        this.internList = interns;
        this.context = context;
    }

    public String cid= ProfileActivity.cid1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.intern,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Intern intern=internList.get(i);
        viewHolder.textViewInternTitle.setText(intern.getInternTitle());
        viewHolder.textViewInternDescription.setText(intern.getInternDescription());
        viewHolder.textViewInternLink.setText(intern.getInternLink());
        viewHolder.textViewInternId.setText(""+intern.getIid());
        viewHolder.textViewUserId.setText(""+intern.getUser_id());
        viewHolder.textViewCid.setText(cid);
        viewHolder.internApply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                applyIntern();
            }
            private void applyIntern() {
                final String ititle=intern.getInternTitle();
                final String description=intern.getInternDescription();
                final String link=intern.getInternLink();
                final int iid=intern.getIid();
                final int userid=intern.getUser_id();
                final String cid1=cid;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_INTERNAPPLY, new Response.Listener<String>() {
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
                        params.put("iid",String.valueOf(iid));
                        params.put("intern_name", ititle);
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
        return internList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewInternTitle;
        public TextView textViewInternDescription;
        public TextView textViewInternLink;
        public TextView textViewInternId;
        public TextView textViewUserId;
        public LinearLayout linearLayoutIntern;
        public Button internApply;
        public TextView textViewCid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCid=itemView.findViewById(R.id.textViewCid);
            internApply=itemView.findViewById(R.id.buttonInternApply);
            textViewInternTitle=itemView.findViewById(R.id.textViewInternTitle);
            textViewInternDescription=itemView.findViewById(R.id.textViewInternDescription);
            textViewInternLink=itemView.findViewById(R.id.textViewInternLink);
            textViewUserId=itemView.findViewById(R.id.textViewUserId);
            textViewInternId=itemView.findViewById(R.id.textViewInternId);
            linearLayoutIntern=itemView.findViewById(R.id.linearLayoutIntern);
        }

    }
}
