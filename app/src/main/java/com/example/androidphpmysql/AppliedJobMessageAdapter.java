package com.example.androidphpmysql;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AppliedJobMessageAdapter extends RecyclerView.Adapter<AppliedJobMessageAdapter.AppliedJobMessageViewHolder> {
    private Context context;
    private List<AppliedJobMessage>appliedJobMessagesList;

    public AppliedJobMessageAdapter(Context context, List<AppliedJobMessage> appliedJobMessages) {
        this.context = context;
        this.appliedJobMessagesList = appliedJobMessages;
    }

    @NonNull
    @Override
    public AppliedJobMessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= (View) layoutInflater.inflate(R.layout.jobmessage,null);
        AppliedJobMessageViewHolder holder=new AppliedJobMessageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppliedJobMessageViewHolder appliedJobMessageViewHolder, int i) {
AppliedJobMessage appliedJobMessage=appliedJobMessagesList.get(i);
appliedJobMessageViewHolder.textViewJobMessage.setText(appliedJobMessage.getMessage());
appliedJobMessageViewHolder.textViewJobMessageTime.setText(appliedJobMessage.getTime());
    }

    @Override
    public int getItemCount() {
        return appliedJobMessagesList.size();
    }

    class AppliedJobMessageViewHolder extends RecyclerView.ViewHolder{
TextView textViewJobMessage,textViewJobMessageTime;
        public AppliedJobMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJobMessageTime=itemView.findViewById(R.id.textViewJobMessageTime);
            textViewJobMessage=itemView.findViewById(R.id.textViewJobMessage);
        }
    }
}
