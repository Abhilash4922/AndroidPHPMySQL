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
    private List<Intern>internList;
    private Context context;

    public InternAdapter( Context context,List<Intern>interns) {
        this.internList = interns;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.intern,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Intern intern=internList.get(i);
        viewHolder.textViewTitleIntern.setText(intern.getiTitle());
        viewHolder.textViewDescriptionIntern  .setText(intern.getIdescription());
        viewHolder.textViewLinkIntern.setText(intern.getiLink());
    }

    @Override
    public int getItemCount() {
        return internList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitleIntern;
        public TextView textViewDescriptionIntern;
        public TextView textViewLinkIntern;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitleIntern=itemView.findViewById(R.id.textViewTitleIntern);
            textViewDescriptionIntern=itemView.findViewById(R.id.textViewDescriptionIntern);
            textViewLinkIntern=itemView.findViewById(R.id.textViewLinkIntern);
        }
    }
}
