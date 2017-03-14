package com.example.android.resumeapplication;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

/**
 * Created by Chaitu on 3/12/2017.
 */

public class sectionheader implements Section<child>, Comparable<sectionheader> {

    List<child> childList;
    String sectionText;
    int index;
    @Override
    public List<child> getChildItems() {
        return childList;
    }

    public sectionheader(List<child> childList, String sectionText, int index) {
        this.childList = childList;
        this.sectionText = sectionText;
        this.index = index;
    }



    public String getSectionText() {
        return sectionText;
    }

   @Override
    public int compareTo(sectionheader another) {
        if (this.index > another.index) {
            return -1;
        } else {
            return 1;
        }
    }





}
