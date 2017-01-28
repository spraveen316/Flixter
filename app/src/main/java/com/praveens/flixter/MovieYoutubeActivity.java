package com.praveens.flixter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.SyncHttpClient;
import com.praveens.flixter.models.Movie;
import com.squareup.picasso.Downloader;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.praveens.flixter.MovieActivity.state;

/**
 * Created by praveens on 1/27/17.
 */

public class MovieYoutubeActivity extends YouTubeBaseActivity {

    private static final String API_KEY = "AIzaSyAq6aSLFBtlMMTFGQuK0Y--jPjkzvQVGVU";
    private static final String MOVIE_TRAILER_URL = "https://api.themoviedb.org/3/movie/%s/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String DEFAULT_TRAILER_KEY = "LKFuXETZUsI";
    private static final String SEARCH_KEY = "trailer";
    private String parsedTrailerKey = DEFAULT_TRAILER_KEY;

    private class YouTubeTrailerKeyFetchTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... url) {
            parsedTrailerKey = getJSONObjectFromURL(url[0]);
            return parsedTrailerKey;
        }

        @Override
        protected void onPostExecute(String result) {
            parsedTrailerKey = result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        final String trailerURL = String.format(MOVIE_TRAILER_URL, new Object[]{movie.getId()});

        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize(API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        //youTubePlayer.cueVideo("5xVh-7ywKpE");
                        // or to play immediately
                        try {
                            new YouTubeTrailerKeyFetchTask().execute(trailerURL);
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            Log.d("Exception", "Trailer URL call failed" + e);
                        }
                        youTubePlayer.loadVideo(parsedTrailerKey);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(MovieYoutubeActivity.this, "YouTube init failure!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String parserTrailerJSONArray(JSONObject object) throws JSONException {
        JSONArray array = object.getJSONArray("youtube");
        for (int i = 0; i < array.length(); i++) {
            String parsedName = array.getJSONObject(i).getString("name");
            if (StringUtils.isNotBlank(parsedName) && StringUtils.containsIgnoreCase(parsedName, SEARCH_KEY)) {
                if (StringUtils.isNotBlank(array.getJSONObject(i).getString("source"))) {
                    return array.getJSONObject(i).getString("source");
                }
            }
        }
        return DEFAULT_TRAILER_KEY;

    }

    private String getJSONObjectFromURL(String urlString) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            char[] buffer = new char[1024];
            String jsonString = new String();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            jsonString = sb.toString();
            return parserTrailerJSONArray(new JSONObject(jsonString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULT_TRAILER_KEY;
    }

}





