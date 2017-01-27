package com.praveens.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveens on 1/23/17.
 */

public class Movie {

    private String posterPath;
    private String originalTitle;
    private String overView;
    private String backdropPath;
    private String vote;
    private String releaseDate;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overView = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.vote = jsonObject.getString("vote_average");
        this.releaseDate = jsonObject.getString("release_date");
    }

    public static List<Movie> fromJSONArray(JSONArray array) throws JSONException {
        List<Movie> movies = new ArrayList<Movie>();

        for (int i = 0; i < array.length(); i++) {
            movies.add(new Movie(array.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342/" + posterPath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w342/" + backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public String getVote() {
        return vote;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
