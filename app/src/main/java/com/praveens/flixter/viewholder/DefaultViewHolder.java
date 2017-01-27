package com.praveens.flixter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.praveens.flixter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by praveens on 1/26/17.
 */

public class DefaultViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvOverview)
    TextView overview;

    @BindView(R.id.lvMovieImage)
    public ImageView movieImage;

    public DefaultViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
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
