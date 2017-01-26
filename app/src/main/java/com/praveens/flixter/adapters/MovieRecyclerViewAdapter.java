package com.praveens.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.praveens.flixter.R;
import com.praveens.flixter.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by praveens on 1/25/17.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.CustomViewHolder> {
    private List<Movie> movies;
    private Context context;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        Movie movie = movies.get(position);

        String imagePath = null;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imagePath = movie.getPosterPath();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imagePath = movie.getBackdropPath();
        }

        //Render image using Picasso library
        Picasso.with(context).load(imagePath)
                .error(R.drawable.imageviewplaceholder)
                .placeholder(R.drawable.imageviewplaceholder)
                .into(customViewHolder.posterImage);

        customViewHolder.title.setText(movie.getOriginalTitle());
        customViewHolder.overview.setText(movie.getOverView());

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return (null != movies ? movies.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView posterImage;
        protected TextView title;
        protected TextView overview;

        public CustomViewHolder(View view) {
            super(view);
            this.posterImage = (ImageView) view.findViewById(R.id.lvMovieImage);
            this.title = (TextView) view.findViewById(R.id.tvTitle);
            this.overview = (TextView) view.findViewById(R.id.tvOverview);
        }
    }
}