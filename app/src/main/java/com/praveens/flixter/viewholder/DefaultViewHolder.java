package com.praveens.flixter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.praveens.flixter.R;

/**
 * Created by praveens on 1/26/17.
 */

public class DefaultViewHolder extends RecyclerView.ViewHolder {

    private TextView title, overview;

    public ImageView movieImage;

    public DefaultViewHolder(View v) {
        super(v);
        title = (TextView) v.findViewById(R.id.tvTitle);
        overview = (TextView) v.findViewById(R.id.tvOverview);
        movieImage = (ImageView) v.findViewById(R.id.lvMovieImage);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getOverview() {
        return overview;
    }

    public void setOverview(TextView overview) {
        this.overview = overview;
    }

    public ImageView getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(ImageView image) {
        this.movieImage = image;
    }
}
