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

import java.util.List;

public class AppliedJobAdapter extends RecyclerView.Adapter<AppliedJobAdapter.AppliedJobViewHolder> {
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
AppliedJob appliedJob=appliedJobList.get(i);
appliedJobViewHolder.textViewAppliedJobTitle.setText(appliedJob.getTitle());
        appliedJobViewHolder.textViewAppliedJobStatus.setText(appliedJob.getStatus());
        appliedJobViewHolder.textViewAppliedJid.setText(appliedJob.getJid());
        ji=appliedJob.getJid();
appliedJobViewHolder.textViewAppliedCid.setText(cid);
appliedJobViewHolder.button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(v.getContext(),JobMessageActivity.class);
        mct.startActivity(i);
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
