package com.lhu.sampleandroidasynchttp;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class MovieItem {
    String title;
    String link;
    String image;
    String director;
    String actor;
    float userRating;

    @Override
    public String toString() {
        return title + "(" + director + ")";
    }
}
