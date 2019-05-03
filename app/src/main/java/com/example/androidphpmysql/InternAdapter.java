package com.example.androidphpmysql;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InternAdapter extends RecyclerView.Adapter<InternAdapter.ViewHolder> {
    private List<Intern>interns;
    private Context context;

    public InternAdapter(List<Intern> interns, Context context) {
        this.interns = interns;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.intern,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
Intern intern=interns.get(i);
viewHolder.textViewInternTitle.setText(intern.getInternTitle());
        viewHolder.textViewInternDescription.setText(intern.getInternDescription());
        viewHolder.textViewInternLink.setText(intern.getInternLink());
    }

    @Override
    public int getItemCount() {
        return interns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
public TextView textViewInternTitle;
        public TextView textViewInternDescription;
        public TextView textViewInternLink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInternTitle=(TextView)itemView.findViewById(R.id.textViewInternTitle);
            textViewInternDescription=(TextView)itemView.findViewById(R.id.textViewInternDiscription);
            textViewInternLink=(TextView)itemView.findViewById(R.id.textViewInternLink);
        }
    }
}
