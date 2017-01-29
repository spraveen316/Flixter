package com.praveens.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveens on 1/23/17.
 */

public class Movie implements Parcelable {

    private String posterPath;
    private String originalTitle;
    private String overView;
    private String backdropPath;
    private String vote;
    private String releaseDate;
    private String id;

    private Movie(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(originalTitle);
        dest.writeString(overView);
        dest.writeString(backdropPath);
        dest.writeString(vote);
        dest.writeString(releaseDate);
        dest.writeString(id);
    }

    private void readFromParcel(Parcel in) {
        posterPath = in.readString();
        originalTitle = in.readString();
        overView = in.readString();
        backdropPath = in.readString();
        vote = in.readString();
        releaseDate = in.readString();
        id = in.readString();
    }

    private Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overView = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.vote = jsonObject.getString("vote_average");
        this.releaseDate = jsonObject.getString("release_date");
        this.id = jsonObject.getString("id");
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

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

}
