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
    private List<Job>jobs;
    private Context context;

    public JobAdapter(List<Job> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
Job job=jobs.get(i);
viewHolder.textViewTitle.setText(job.getTitle());
        viewHolder.textViewDescription  .setText(job.getDescription());
        viewHolder.textViewLink.setText(job.getLink());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewLink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=(TextView)itemView.findViewById(R.id.textViewTitle);
            textViewDescription=(TextView)itemView.findViewById(R.id.textViewDiscription);
            textViewLink=(TextView)itemView.findViewById(R.id.textViewLink);
        }
    }
}
