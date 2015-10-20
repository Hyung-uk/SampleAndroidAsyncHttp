package com.lhu.sampleandroidasynchttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dongja94 on 2015-10-20.
 */
public class ImageRequest extends NetworkRequest<Bitmap> {
    String url;
    public ImageRequest(String url) {
        this.url = url;
    }
    ImageView imageView;
    public void setImageView(ImageView view) {
        imageView = view;
    }
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public URL getURL() throws MalformedURLException {
        return new URL(url);
    }

    @Override
    public Bitmap parsing(InputStream is) {
        Bitmap bm = BitmapFactory.decodeStream(is);
        return bm;
    }
}
