package com.example.androidphpmysql;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AppliedJobAdapter extends RecyclerView.Adapter<AppliedJobAdapter.AppliedJobViewHolder> {
    private Context mct;
    private List<AppliedJob> appliedJobList;

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
    }

    @Override
    public int getItemCount() {
        return appliedJobList.size();

    }

    class AppliedJobViewHolder extends RecyclerView.ViewHolder{
TextView textViewAppliedJobTitle,textViewAppliedJobStatus;
    public AppliedJobViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewAppliedJobTitle=itemView.findViewById(R.id.textViewAppliedJobTitle);
        textViewAppliedJobStatus=itemView.findViewById(R.id.textViewAppliedJobStatus);
    }
}
}
