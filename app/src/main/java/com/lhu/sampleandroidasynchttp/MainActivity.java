package com.lhu.sampleandroidasynchttp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText keywordView;
    ListView listView;
    MovieAdapter mAdapter;
    ArrayAdapter<Song> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        keywordView = (EditText)findViewById(R.id.edit_keyword);
        listView = (ListView)findViewById(R.id.listView);
//        mAdapter = new MovieAdapter();
//        listView.setAdapter(mAdapter);

        mListAdapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mListAdapter);

//        keywordView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                searchMovie(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        NetworkManager.getInstance().getMelonChart(this, 1, 10, new NetworkManager.OnResultListener<Melon>() {
            @Override
            public void onSuccess(Melon result) {
                for (Song s : result.songs.songList) {
                    mListAdapter.add(s);
                }
            }

            @Override
            public void onFail(int code) {

            }
        });
    }

    private void searchMovie(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            NetworkManager.getInstance().getNetworkMelon(this, keyword, 1, 10, new NetworkManager.OnResultListener<NaverMovies>() {
                @Override
                public void onSuccess(NaverMovies result) {
                    mAdapter.clear();
                    for (MovieItem item : result.items) {
                        mAdapter.add(item);
                    }
                }

                @Override
                public void onFail(int code) {
                    Toast.makeText(MainActivity.this, "error : " + code, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mAdapter.clear();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


//package com.lhu.samplenetwork;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.begentgroup.xmlparser.XMLParser;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//
//public class MainActivity extends AppCompatActivity {
//    EditText keywordView;
//    ListView listView;
//    MovieAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        keywordView = (EditText)findViewById(R.id.edit_keyword);
//        listView = (ListView)findViewById(R.id.listView);
////        mAdapter = new ArrayAdapter<MovieItem>(this, android.R.layout.simple_list_item_1);
//        mAdapter = new MovieAdapter();
//        listView.setAdapter(mAdapter);
//        Button btn = (Button)findViewById(R.id.btn_search);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String keyword = keywordView.getText().toString();
//                searchMovie(keyword);
//            }
//        });
//
//        keywordView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String keyword = s.toString();
//                searchMovie(keyword);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//    private void searchMovie(String keyword) {
//        if (!TextUtils.isEmpty(keyword)) {
//            NetworkManager.getInstance().getNaverMovies(new NaverMovieRequest(keyword), new NetworkManager.OnResultListener<NaverMovies>() {
//                @Override
//                public void onSuccess(NetworkRequest<NaverMovies> request, NaverMovies result) {
//                    mAdapter.clear();
//                    for (MovieItem item : result.items) {
//                        mAdapter.add(item);
//                    }
//                }
//
//                @Override
//                public void onFail(NetworkRequest<NaverMovies> request, int code) {
//                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            mAdapter.clear();
//        }
//    }
//
//    public static final String MOVIE_URL = "http://openapi.naver.com/search?key=55f1e342c5bce1cac340ebb6032c7d9a&query=%s&display=10&start=1&target=movie";
//    class MovieTask extends AsyncTask<String, Integer, NaverMovies> {
//
//        @Override
//        protected NaverMovies doInBackground(String... params) {
//            String keyword = params[0];
//            try {
//                String urlText = String.format(MOVIE_URL, URLEncoder.encode(keyword, "utf-8"));
//                URL url = new URL(urlText);
//                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                int code = conn.getResponseCode();
//                if (code == HttpURLConnection.HTTP_OK) {
//                    XMLParser parser = new XMLParser();
//                    NaverMovies movies = parser.fromXml(conn.getInputStream(), "channel", NaverMovies.class);
//                    return movies;
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(NaverMovies naverMovies) {
//            super.onPostExecute(naverMovies);
//            if (naverMovies != null) {
//                mAdapter.clear();
//                for (MovieItem item : naverMovies.items) {
//                    mAdapter.add(item);
//                }
//            } else {
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
