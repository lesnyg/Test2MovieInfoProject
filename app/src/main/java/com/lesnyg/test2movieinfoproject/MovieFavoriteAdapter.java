package com.lesnyg.test2movieinfoproject;


import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lesnyg.test2movieinfoproject.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieHolder> {
    List<Result> mList = new ArrayList<>();
    public MovieFavoriteAdapter(){

    }

    public MovieFavoriteAdapter(OnFavoriteClickListener listener) {
        mListener = listener;
    }

    public void setitems(List<Result> items) {
        mList = items;
        notifyDataSetChanged();
    }
    public void updateItems(List<Result> items) {
        new Thread(() -> {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ListDiffCallback(this.mList, items));

            mList.clear();
            mList.addAll(items);

            new Handler(Looper.getMainLooper()).post(() -> result.dispatchUpdatesTo(this));
        }).start();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_favorites, viewGroup, false);
        MovieHolder movieHolder = new MovieHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    final Result item = mList.get(movieHolder.getAdapterPosition());
                    mListener.onFavoriteClick(item);
                }
            }
        });

        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        Result item = mList.get(i);

        String posterPath = "https://image.tmdb.org/t/p/w500" + item.getPoster_path();
        Glide.with(movieHolder.itemView)
                .load(posterPath)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(movieHolder.movieimage);
        movieHolder.textTitle.setText(item.getTitle());

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
