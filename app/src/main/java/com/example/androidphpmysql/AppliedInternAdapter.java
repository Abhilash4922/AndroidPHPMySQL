package com.example.androidphpmysql;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AppliedInternAdapter extends RecyclerView.Adapter<AppliedInternAdapter.AppliedInternViewHolder> {
    private Context mct;
    private List<AppliedIntern> appliedInternList;

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
AppliedIntern appliedIntern=appliedInternList.get(i);
appliedInternViewHolder.textViewAppliedInternTitle.setText(appliedIntern.getInternTitle());
        appliedInternViewHolder.textViewAppliedInternStatus.setText(appliedIntern.getInternStatus());
        appliedInternViewHolder.textViewAppliedIid.setText(appliedIntern.getIid());

    }

    @Override
    public int getItemCount() {
        return appliedInternList.size();

    }

    class AppliedInternViewHolder extends RecyclerView.ViewHolder{
TextView textViewAppliedInternTitle,textViewAppliedInternStatus,textViewAppliedIid;
    public AppliedInternViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewAppliedInternTitle=itemView.findViewById(R.id.textViewAppliedInternTitle);
        textViewAppliedInternStatus=itemView.findViewById(R.id.textViewAppliedInternStatus);
        textViewAppliedIid=itemView.findViewById(R.id.textViewAppliedIid);
    }
}
}
