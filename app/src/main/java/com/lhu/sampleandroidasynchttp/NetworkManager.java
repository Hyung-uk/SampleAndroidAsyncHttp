package com.lhu.sampleandroidasynchttp;

import android.content.Context;

import com.begentgroup.xmlparser.XMLParser;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayInputStream;

/**
 * Created by dongja94 on 2015-10-20.
 */
public class NetworkManager {
    private static NetworkManager instance;
    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    AsyncHttpClient client;
    XMLParser parser;
    Gson gson;

    private NetworkManager() {
        client = new AsyncHttpClient();
        parser = new XMLParser();
        gson = new Gson();

        client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
    }

    public interface OnResultListener<T> {
        public void onSuccess(T result);
        public void onFail(int code);
    }


    private static final String SERVER = "http://openapi.naver.com";

    private static final String MOVIE_URL = SERVER + "/search";
    private static final String KEY = "55f1e342c5bce1cac340ebb6032c7d9a";
    private static final String TARGET = "movie";

    public void getNetworkMelon(Context context, String keyword, int start, int display, final OnResultListener<NaverMovies> listener) {
        final RequestParams params = new RequestParams();

        params.put("key", KEY);
        params.put("query", keyword);
        params.put("display", display);
        params.put("start", start);
        params.put("target", TARGET);

        client.get(context, MOVIE_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ByteArrayInputStream bais = new ByteArrayInputStream(responseBody);
                NaverMovies movies = parser.fromXml(bais, "channel", NaverMovies.class);
                listener.onSuccess(movies);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listener.onFail(statusCode);
            }
        });
    }

    private static final String MELON_URL = "http://apis.skplanetx.com/melon/charts/realtime";

    public void getMelonChart(Context context, int page, int count, final OnResultListener<Melon> listener) {
        RequestParams params = new RequestParams();
        params.put("count", count);
        params.put("page", page);
        params.put("version",1);
//        File file = new File("abc");
//
//        for (int i = 0; i < 5; i++) {
//            params.add("count", "" + i);
//        }
//
//        File[] files = new File[3];
//        try {
//            params.put("files" , files);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        Header[] headers = new Header[2];
        headers[0] = new BasicHeader("Accept", "application/json");
        headers[1] = new BasicHeader("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a");

        client.get(context, MELON_URL, headers, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                MelonResult result = gson.fromJson(responseString, MelonResult.class);
                listener.onSuccess(result.melon);
            }
        });


    }

    public void cancelAll(Context context) {
        client.cancelRequests(context, true);
    }
}







//package com.lhu.samplenetwork;
//
//import android.graphics.Bitmap;
//import android.os.Handler;
//import android.os.Looper;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by Tacademy on 2015-10-20.
// */
//public class NetworkManager {
//    private static NetworkManager instance;
//
//    public static NetworkManager getInstance() {
//        if (instance == null) {
//            instance = new NetworkManager();
//        }
//
//        return instance;
//    }
//
//    ThreadPoolExecutor mExecutor;
//    Handler mHandler = new Handler(Looper.getMainLooper());
//
//    private NetworkManager() {
//        mExecutor = new ThreadPoolExecutor(5, 64, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
//    }
//
//    public interface OnResultListener<T> {
//        public void onSuccess(NetworkRequest<T> request, T result);
//        public void onFail(NetworkRequest<T> request, int code);
//    }
//
//    public void getNaverMovies(NetworkRequest<NaverMovies> request, OnResultListener<NaverMovies> listener) {
//        mExecutor.execute(new NetworkProcess<NaverMovies>(request, listener));
//    }
//
//    public void getImageBitmap(NetworkRequest<Bitmap> request, OnResultListener<Bitmap> listener) {
//        mExecutor.execute(new NetworkProcess<Bitmap>(request, listener));
//    }
//
//    public <T> void getNetworkData(NetworkRequest<T> request, OnResultListener<T> listener) {
//        mExecutor.execute(new NetworkProcess<T>(request, listener));
//    }
//
//    class NetworkProcess<T> implements Runnable {
//        NetworkRequest<T> request;
//        OnResultListener<T> listener;
//
//        public NetworkProcess(NetworkRequest<T> request, OnResultListener<T> listener) {
//            this.request = request;
//            this.listener = listener;
//        }
//
//        @Override
//        public void run() {
//            try {
//                URL url = request.getURL();
//                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                request.setRequestMethod(conn);
//                request.setOutput(conn);
//                int code = conn.getResponseCode();
//                if (code == HttpURLConnection.HTTP_OK) {
//                    InputStream is = conn.getInputStream();
//                    final T object = request.parsing(is);
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (!request.isCancel()) {
//                                listener.onSuccess(request, object);
//                            }
//                        }
//                    });
//                    return;
//                }
//           } catch (MalformedURLException e) {
//            e.printStackTrace();
//           } catch (IOException e) {
//               e.printStackTrace();
//            }
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (!request.isCancel()) {
//                        listener.onFail(request, -1);
//                    }
//                }
//            });
//        }
//    }
//}