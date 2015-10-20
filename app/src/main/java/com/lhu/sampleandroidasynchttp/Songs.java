package com.lhu.sampleandroidasynchttp;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class Songs implements JSONParsing{

    @SerializedName("song")
    List<Song> songList;

    @Override
    public void parsing(JSONObject jobject) throws JSONException {
        songList = new ArrayList<Song>();
        JSONArray array = jobject.getJSONArray("songList");
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsong = array.getJSONObject(i);
            Song s = new Song();
            s.parsing(jsong);
            songList.add(s);
        }
    }
}
