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

public class AppliedInternAdapter extends RecyclerView.Adapter<AppliedInternAdapter.AppliedInternViewHolder> {
    private Context mct;
    private static String INTERN_MESSAGE="http://192.168.43.129/android/v1/internmessage.php";

    public String cid=ProfileActivity.cid1;
    private List<AppliedIntern> appliedInternList;
    public static String iid2;

    public AppliedInternAdapter(Context mct, List<AppliedIntern> appliedInternList) {
        this.mct = mct;
        this.appliedInternList = appliedInternList;
    }

    @NonNull
    @Override
    public AppliedInternViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mct);
        View view=layoutInflater.inflate(R.layout.appliedintern,null);
        AppliedInternViewHolder appliedInternViewHolder=new AppliedInternViewHolder(view);
        return appliedInternViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppliedInternViewHolder appliedInternViewHolder, int i) {
final AppliedIntern appliedIntern=appliedInternList.get(i);
appliedInternViewHolder.textViewAppliedInternTitle.setText(appliedIntern.getInternTitle());
        appliedInternViewHolder.textViewAppliedInternStatus.setText(appliedIntern.getInternStatus());
        appliedInternViewHolder.textViewAppliedIid.setText(appliedIntern.getIid());
        appliedInternViewHolder.textViewAppliedCid.setText(appliedIntern.getCid());
        iid2=appliedIntern.getIid();
        appliedInternViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadMessage();    }
            private void loadMessage() {
                final String iid = appliedIntern.getIid();
                final String cid1 = cid;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, INTERN_MESSAGE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String time = jsonObject.getString("created_at");
                                String message = jsonObject.getString("message");
                                Intent myIntent = new Intent(mct, InternMessageActivity.class);
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
                        params.put("iid", String.valueOf(iid));
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
        return appliedInternList.size();

    }

    class AppliedInternViewHolder extends RecyclerView.ViewHolder{
TextView textViewAppliedInternTitle,textViewAppliedInternStatus,textViewAppliedIid,textViewAppliedCid;
Button button;
    public AppliedInternViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewAppliedInternTitle=itemView.findViewById(R.id.textViewAppliedInternTitle);
        textViewAppliedInternStatus=itemView.findViewById(R.id.textViewAppliedInternStatus);
        textViewAppliedIid=itemView.findViewById(R.id.textViewAppliedIid);
        textViewAppliedCid=itemView.findViewById(R.id.textViewAppliedICid);
    button=itemView.findViewById(R.id.buttonViewInternMessage);
    }
}
}
