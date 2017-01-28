package com.praveens.flixter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.praveens.flixter.models.Movie;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by praveens on 1/26/17.
 */

public class MovieDetailsActivity extends Activity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvReleaseDate)
    TextView tvReleaseDate;
    @BindView(R.id.tvOverview)
    TextView tvOverview;
    @BindView(R.id.lvMovieImage)
    ImageView ivImage;
    @BindView(R.id.rbStars)
    RatingBar rbStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);
        ButterKnife.bind(this);

        final Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        tvTitle.setText(movie.getOriginalTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvOverview.setText(movie.getOverView());
        rbStars.setRating(Float.valueOf(movie.getVote()));

        Picasso.with(getApplicationContext()).load(movie.getBackdropPath())
                .error(R.drawable.imageviewplaceholder)
                .placeholder(R.drawable.imageviewplaceholder)
                .into(ivImage);

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovieYoutubeActivity.class);
                intent.putExtra("movie", movie);
                v.getContext().startActivity(intent);
            }
        });

    }
}

