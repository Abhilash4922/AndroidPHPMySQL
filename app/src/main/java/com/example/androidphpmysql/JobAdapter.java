package com.example.androidphpmysql;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private List<Job>jobList;
    private Context context;

    public JobAdapter( Context context,List<Job>jobs) {
        this.jobList = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.job,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
Job job=jobList.get(i);
        viewHolder.textViewUserId.setText(""+job.getUser_id());
viewHolder.textViewJobId.setText(""+job.getId());
viewHolder.textViewTitle.setText(job.getJobTitle());
        viewHolder.textViewDescription  .setText(job.getDescription());
        viewHolder.textViewLink.setText(job.getLink());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewUserId;
        public TextView textViewJobId;
        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewLink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserId=itemView.findViewById(R.id.textViewUserId);
            textViewJobId=itemView.findViewById(R.id.textViewJobId);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDescription=itemView.findViewById(R.id.textViewDiscription);
            textViewLink=itemView.findViewById(R.id.textViewLink);
        }
    }
}
