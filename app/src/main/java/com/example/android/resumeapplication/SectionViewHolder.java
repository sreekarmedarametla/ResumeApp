package com.example.android.resumeapplication;

/**
 * Created by Chaitu on 3/12/2017.
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class SectionViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    public SectionViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.section);
    }
}
