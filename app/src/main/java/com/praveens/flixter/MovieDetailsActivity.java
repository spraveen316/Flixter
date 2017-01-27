package com.praveens.flixter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.praveens.flixter.models.Movie;

import com.squareup.picasso.Picasso;

/**
 * Created by praveens on 1/26/17.
 */

public class MovieDetailsActivity extends Activity {

    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvOverview;
    private ImageView ivImage;
    private RatingBar rbStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        ivImage = (ImageView) findViewById(R.id.lvMovieImage);
        rbStars = (RatingBar) findViewById(R.id.rbStars);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        tvTitle.setText(movie.getOriginalTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvOverview.setText(movie.getOverView());
        rbStars.setRating(Float.valueOf(movie.getVote()));

        Picasso.with(getApplicationContext()).load(movie.getBackdropPath())
                .error(R.drawable.imageviewplaceholder)
                .placeholder(R.drawable.imageviewplaceholder)
                .into(ivImage);

    }
}

