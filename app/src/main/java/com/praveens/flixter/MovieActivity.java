package com.praveens.flixter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.praveens.flixter.adapters.MovieArrayAdapter;
import com.praveens.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.praveens.flixter.R.id.lvMovies;

public class MovieActivity extends Activity {

    private SwipeRefreshLayout swipeContainer;

    private List<Movie> movies = new ArrayList<Movie>();
    private MovieArrayAdapter movieArrayAdapter;
    private ListView lvItems;

    private final String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    public static Parcelable state;

    @Override
    public void onPause() {
        // Save ListView state @ onPause
        state = lvItems.onSaveInstanceState();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvItems = (ListView) findViewById(lvMovies);
        movies = new ArrayList<Movie>();
        movieArrayAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieArrayAdapter);

        final AsyncHttpClient client = new AsyncHttpClient();

        fetchMoviesAsync(client);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.activity_movie);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchMoviesAsync(client);
                // Make sure you call swipeContainer.setRefreshing(false) once the network request has completed successfully.
                swipeContainer.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void fetchMoviesAsync(AsyncHttpClient client) {
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;

                try {
                    movieJSONResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJSONResults));
                    movieArrayAdapter.notifyDataSetChanged();

                    // Restore previous state (including selected item index and scroll position)
                    if (state != null) {
                        lvItems.onRestoreInstanceState(state);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
