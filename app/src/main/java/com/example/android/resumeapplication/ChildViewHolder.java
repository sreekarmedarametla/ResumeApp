package com.example.android.resumeapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chaitu on 3/12/2017.
 */

public class ChildViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    public ChildViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.child);
    }
}
