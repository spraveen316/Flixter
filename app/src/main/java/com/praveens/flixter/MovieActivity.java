package com.praveens.flixter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.praveens.flixter.adapters.MovieRecyclerViewAdapter;
import com.praveens.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends Activity {

    private SwipeRefreshLayout swipeContainer;
    private List<Movie> movies = new ArrayList<Movie>();
    private MovieRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    public static Parcelable state;

    private final String MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("recyclerview_state", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable("recyclerview_state");
        }

        recyclerView = (RecyclerView) findViewById(R.id.lvMovies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movies = new ArrayList<Movie>();

        final AsyncHttpClient client = new AsyncHttpClient();
        fetchMoviesAsync(client);

        recyclerViewAdapter = new MovieRecyclerViewAdapter(this, movies);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.activity_movie);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                state = null;
                fetchMoviesAsync(client);
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void fetchMoviesAsync(final AsyncHttpClient client) {

        client.get(MOVIE_URL, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults;

                try {
                    movieJSONResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJSONResults));
                    recyclerViewAdapter.notifyDataSetChanged();

                    if (state != null) {
                        recyclerView.getLayoutManager().onRestoreInstanceState(state);
                        state = null;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
