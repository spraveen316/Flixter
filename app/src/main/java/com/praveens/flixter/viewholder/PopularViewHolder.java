package com.praveens.flixter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.praveens.flixter.R;

/**
 * Created by praveens on 1/26/17.
 */

public class PopularViewHolder extends RecyclerView.ViewHolder {

    public ImageView movieImage;

    public PopularViewHolder(View v) {
        super(v);
        movieImage = (ImageView) v.findViewById(R.id.lvMovieImage);
    }

    public ImageView getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(ImageView image) {
        this.movieImage = image;
    }
}
