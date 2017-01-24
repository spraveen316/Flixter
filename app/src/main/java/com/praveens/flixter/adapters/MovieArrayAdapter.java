package com.praveens.flixter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.praveens.flixter.R;
import com.praveens.flixter.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by praveens on 1/23/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            convertView = inflator.inflate(R.layout.item_movie, parent, false);
        }
        ImageView lvImage = (ImageView) convertView.findViewById(R.id.lvMovieImage);
        lvImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverView());

        Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop().placeholder(R.drawable.imageViewPlaceholder).into(lvImage);

        return convertView;

    }

}
