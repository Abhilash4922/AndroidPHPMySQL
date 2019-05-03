package com.example.androidphpmysql;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder>{
    private List<JobActivity>jobActivities;
    private Context context;

    public JobAdapter(JobActivity jobActivities, List<JobActivity> context) {
        this.jobActivities = (List<JobActivity>) jobActivities;
        this.context = (Context) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_job,viewGroup);
    return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        JobActivity jobActivity=jobActivities.get(i);
    viewHolder.textViewTitle.setText(jobActivity.getJtitle());
    viewHolder.textViewDescription.setText(jobActivity.getDescription());
    viewHolder.textViewLink.setText(jobActivity.getLink());

    }

    @Override
    public int getItemCount() {
        return jobActivities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewLink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=(TextView)itemView.findViewById(R.id.textViewTitle);
            textViewDescription=(TextView)itemView.findViewById(R.id.textViewDescription);
            textViewLink=(TextView)itemView.findViewById(R.id.textViewLink);
        }
    }
}
