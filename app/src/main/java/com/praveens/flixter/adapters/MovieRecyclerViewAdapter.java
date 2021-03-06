package com.praveens.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.praveens.flixter.MovieDetailsActivity;
import com.praveens.flixter.MovieYoutubeActivity;
import com.praveens.flixter.R;
import com.praveens.flixter.models.Movie;
import com.praveens.flixter.viewholder.PopularViewHolder;
import com.praveens.flixter.viewholder.DefaultViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static android.R.attr.data;

/**
 * Created by praveens on 1/25/17.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movies;
    private Context context;

    private final int POPULAR = 0, NOT_POPULAR = 1;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case POPULAR:
                viewHolder = new PopularViewHolder(inflater.inflate(R.layout.popular_viewholder, viewGroup, false));
                break;
            default:
                viewHolder = new DefaultViewHolder(inflater.inflate(R.layout.item_movie, viewGroup, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder customViewHolder, final int position) {
        Movie movie = movies.get(position);

        switch (customViewHolder.getItemViewType()) {
            case POPULAR:
                PopularViewHolder popularViewHolder = (PopularViewHolder) customViewHolder;
                configurePopularViewHolder(popularViewHolder, movie);
                popularViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Movie movie = movies.get(position);
                        Intent intent = new Intent(context, MovieYoutubeActivity.class);
                        intent.putExtra("movie", movie);
                        context.startActivity(intent);
                    }
                });
                break;
            default:
                DefaultViewHolder defaultViewHolder = (DefaultViewHolder) customViewHolder;
                configureDefaultViewHolder(defaultViewHolder, position);
                customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Movie movie = movies.get(position);
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movie", movie);
                        context.startActivity(intent);

                    }
                });
                break;
        }

    }

    private void configureDefaultViewHolder(DefaultViewHolder defaultViewHolder, int position) {
        Movie movie = movies.get(position);
        defaultViewHolder.getTitle().setText(movie.getOriginalTitle());
        defaultViewHolder.getOverview().setText(movie.getOverView());

        String imagePath = null;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imagePath = movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imagePath = movie.getBackdropPath();
        }

        Picasso.with(context).load(imagePath)
                .error(R.drawable.imageviewplaceholder)
                .transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.imageviewplaceholder)
                .into(defaultViewHolder.movieImage);
    }

    private void configurePopularViewHolder(PopularViewHolder popularViewHolder, Movie movie) {

        Picasso.with(context).load(movie.getBackdropPath())
                .error(R.drawable.imageviewplaceholder)
                .transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.imageviewplaceholder)
                .into(popularViewHolder.movieImage);
    }

    @Override
    public int getItemViewType(int position) {
        if (Float.valueOf(movies.get(position).getVote()) > 7.0f) {
            return POPULAR;
        } else {
            return NOT_POPULAR;
        }
    }

    @Override
    public int getItemCount() {
        return (null != movies ? movies.size() : 0);
    }

}