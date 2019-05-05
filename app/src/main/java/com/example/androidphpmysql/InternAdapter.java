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

    public InternAdapter(Context context,List<Intern> interns) {
        this.interns = interns;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.intern,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
Intern intern=interns.get(i);
viewHolder.textViewTitleIntern.setText(intern.getTitleIntern());
viewHolder.textViewDescriptionIntern.setText(intern.getDescriptionIntern());
viewHolder.textViewLinkIntern.setText(intern.getLinkIntern());
    }

    @Override
    public int getItemCount() {
        return interns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
public TextView textViewTitleIntern;
        public TextView textViewDescriptionIntern;
        public TextView textViewLinkIntern;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitleIntern=(TextView)itemView.findViewById(R.id.textViewTitleIntern);
            textViewDescriptionIntern=(TextView)itemView.findViewById(R.id.textViewDescriptionIntern);
            textViewLinkIntern=(TextView)itemView.findViewById(R.id.textViewLinkIntern);
        }
    }
}
