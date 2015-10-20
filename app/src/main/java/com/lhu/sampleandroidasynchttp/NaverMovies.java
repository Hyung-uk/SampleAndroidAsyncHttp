package com.lhu.sampleandroidasynchttp;

import com.begentgroup.xmlparser.SerializedName;

import java.util.ArrayList;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class NaverMovies {
    String title;
    String link;
    int total;
    int start;
    int display;
    @SerializedName("item")
    ArrayList<MovieItem> items;
}
