package com.lhu.sampleandroidasynchttp;

import com.begentgroup.xmlparser.XMLParser;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tacademy on 2015-10-20.
 */
public class NaverMovieRequest extends NetworkRequest<NaverMovies> {
    String keyword;
    int start;
    int display;
    private static final String MOVIE_URL = "http://openapi.naver.com/search?key=55f1e342c5bce1cac340ebb6032c7d9a&query=%s&display=%s&start=%s&target=movie";

    public NaverMovieRequest(String keyword, int start, int display) {
        this.keyword = keyword;
        this.start = start;
        this.display = display;
    }

    public NaverMovieRequest(String keyword) {
        this(keyword, 1, 10);
    }

    @Override
    public URL getURL() throws MalformedURLException {
        try {
            String urlText = String.format(MOVIE_URL, URLEncoder.encode(keyword, "utf-8"), display, start);
            return new URL(urlText);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public NaverMovies parsing(InputStream is) {
        XMLParser parser = new XMLParser();
        NaverMovies movies = parser.fromXml(is, "channel", NaverMovies.class);
        return movies;
    }
}
