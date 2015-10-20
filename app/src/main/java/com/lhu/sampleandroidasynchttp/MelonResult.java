package com.lhu.sampleandroidasynchttp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class MelonResult implements JSONParsing {
    Melon melon;

    @Override
    public void parsing(JSONObject jobject) throws JSONException {
        melon = new Melon();
        JSONObject jmelon = jobject.getJSONObject("melon");
        melon.parsing(jmelon);
    }
}
