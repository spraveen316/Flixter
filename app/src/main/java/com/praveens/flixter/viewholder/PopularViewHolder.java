package com.praveens.flixter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.praveens.flixter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by praveens on 1/26/17.
 */

public class PopularViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.lvMovieImage)
    public ImageView movieImage;

    public PopularViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public ImageView getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(ImageView image) {
        this.movieImage = image;
    }
}
