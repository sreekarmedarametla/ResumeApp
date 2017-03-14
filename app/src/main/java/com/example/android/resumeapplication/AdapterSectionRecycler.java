package com.example.android.resumeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Chaitu on 3/12/2017.
 */

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<sectionheader, child, SectionViewHolder, ChildViewHolder> {

Context context;

    public AdapterSectionRecycler(Context context, List<sectionheader> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
    }
    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sectionheader, sectionViewGroup, false);
        return new SectionViewHolder(view);
    }
    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sectionchild, childViewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int sectionPosition, sectionheader section) {
        sectionViewHolder.name.setText(section.sectionText);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int sectionPosition, int childPosition, child child) {
        childViewHolder.name.setText(child.getName());
    }

}
