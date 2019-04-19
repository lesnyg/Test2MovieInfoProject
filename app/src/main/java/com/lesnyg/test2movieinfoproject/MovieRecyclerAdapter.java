package com.lesnyg.test2movieinfoproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lesnyg.test2movieinfoproject.models.LatestResult;
import com.lesnyg.test2movieinfoproject.models.Result;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieHolder> {
    List<LatestResult> mData = new ArrayList<>();
    List<Result> mList = new ArrayList<>();

    public void setitems(List<LatestResult> list) {
        mData = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        MovieHolder movieHolder = new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        LatestResult result = mData.get(i);

        String posterPath = "https://image.tmdb.org/t/p/w500" + result.getPoster_path();
        Glide.with(movieHolder.itemView)
                .load(posterPath)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(movieHolder.movieimage);
        movieHolder.textTitle.setText(result.getTitle());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView movieimage;
        private TextView textTitle;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieimage = itemView.findViewById(R.id.imageView_movie);
            textTitle = itemView.findViewById(R.id.text_title);
        }
    }
}
