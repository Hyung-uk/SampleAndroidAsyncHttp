package com.lhu.sampleandroidasynchttp;//package com.lhu.samplenetwork;
//
//import android.graphics.Bitmap;
//import android.text.TextUtils;
//import android.widget.ImageView;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by dongja94 on 2015-10-20.
// */
//public class ImageLoader {
//    private static ImageLoader instance;
//    public static ImageLoader getInstance() {
//        if (instance == null) {
//            instance = new ImageLoader();
//        }
//        return instance;
//    }
//    private ImageLoader() {
//
//    }
//
//    Map<ImageView, ImageRequest> mRequestMap = new HashMap<ImageView, ImageRequest>();
//
//    public void displayImage(String url, ImageView imageView) {
//        ImageRequest oldRequest = mRequestMap.get(imageView);
//        if (oldRequest != null) {
//            oldRequest.cancel();
//            mRequestMap.remove(imageView);
//        }
//        if (!TextUtils.isEmpty(url)) {
//            ImageRequest request = new ImageRequest(url);
//            request.setImageView(imageView);
//            mRequestMap.put(imageView, request);
//            imageView.setImageResource(R.drawable.ic_stub);
//            NetworkManager.getInstance().getImageBitmap(request, new NetworkManager.OnResultListener<Bitmap>() {
//                @Override
//                public void onSuccess(NetworkRequest<Bitmap> request, Bitmap result) {
//                    ImageView view = ((ImageRequest)request).getImageView();
//                    ImageRequest ir = mRequestMap.get(view);
//                    if (ir == request) {
//                        view.setImageBitmap(result);
//                        mRequestMap.remove(view);
//                    }
//                }
//
//                @Override
//                public void onFail(NetworkRequest<Bitmap> request, int code) {
//                    ImageView view = ((ImageRequest)request).getImageView();
//                    ImageRequest ir = mRequestMap.get(view);
//                    if (ir == request) {
//                        view.setImageResource(R.drawable.ic_error);
//                        mRequestMap.remove(view);
//                    }
//                }
//            });
//        } else {
//            imageView.setImageResource(R.drawable.ic_empty);
//        }
//    }
//
//}