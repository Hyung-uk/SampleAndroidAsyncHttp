package com.lhu.sampleandroidasynchttp;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class Melon implements JSONParsing{
    int menuId;
    int count;
    int page;
    @SerializedName("totalPages")
    int total;
    Songs songs;

    @Override
    public void parsing(JSONObject jobject) throws JSONException {
        menuId = jobject.getInt("menuId");
        count = jobject.getInt("count");
        page = jobject.getInt("page");
        total = jobject.getInt("total");
        songs = new Songs();
        JSONObject jsongs = jobject.getJSONObject("songs");
        songs.parsing(jsongs);
    }
}
