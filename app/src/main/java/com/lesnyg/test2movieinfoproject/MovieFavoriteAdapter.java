package com.lesnyg.test2movieinfoproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lesnyg.test2movieinfoproject.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieHolder> {
    List<Result> mList = new ArrayList<>();

    public MovieFavoriteAdapter(OnFavoriteClickListener listener) {
        mListener = listener;
    }

    public void setitems(List<Result> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_favorites, viewGroup, false);
        MovieHolder movieHolder = new MovieHolder(view);


        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        Result result = mList.get(i);

        String posterPath = "https://image.tmdb.org/t/p/w500" + result.getPoster_path();
        Glide.with(movieHolder.itemView)
                .load(posterPath)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(movieHolder.movieimage);
        movieHolder.textTitle.setText(result.getTitle());

        movieHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFavoriteClick(mList.get(movieHolder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView movieimage;
        private TextView textTitle;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieimage = itemView.findViewById(R.id.image_favorites);
            textTitle = itemView.findViewById(R.id.text_favorites);
        }
    }


    interface OnFavoriteClickListener {
        void onFavoriteClick(Result item);
    }

    private OnFavoriteClickListener mListener;

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener) {
        mListener = listener;
    }
}
