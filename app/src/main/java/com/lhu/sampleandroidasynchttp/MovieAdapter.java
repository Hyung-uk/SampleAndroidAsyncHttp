package com.lhu.sampleandroidasynchttp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class MovieAdapter extends BaseAdapter {

    List<MovieItem> items = new ArrayList<MovieItem>();

    public void add(MovieItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieItemView view;
        if (convertView == null) {
            view = new MovieItemView(parent.getContext());
        } else {
            view = (MovieItemView)convertView;
        }
        view.setMovieItem(items.get(position));
        return view;
    }
}
