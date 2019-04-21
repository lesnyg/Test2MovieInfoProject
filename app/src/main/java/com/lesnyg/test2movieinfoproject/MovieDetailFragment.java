package com.lesnyg.test2movieinfoproject;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lesnyg.test2movieinfoproject.databinding.FragmentMovieDetailBinding;
import com.lesnyg.test2movieinfoproject.models.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {

    private Result mResult;


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(Result result) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("result", result);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mResult = (Result) getArguments().getSerializable("result");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        FragmentMovieDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
//        binding.imageView.setImageResource(mResult.getPoster_path());
//        binding.textViewTitle.setText(mResult.getTitle());
//        binding.textViewReleasedate.setText(mResult.getRelease_date());
//        binding.textViewOverview.setText(mResult.getOverview());
//        return binding.getRoot();
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String posterPath = "https://image.tmdb.org/t/p/w500" + mResult.getPoster_path();

        ImageView posterImage = view.findViewById((R.id.imageView));
//        posterImage.setImageResource();
        Glide.with(getActivity())
                .load(posterPath)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(posterImage);
        TextView titleText = view.findViewById(R.id.textView_title);
        titleText.setText(mResult.getTitle());
        TextView dateText = view.findViewById(R.id.textView_releasedate);
        dateText.setText("상영일 : "+mResult.getRelease_date());
        TextView overViewText = view.findViewById(R.id.textView_overview);
        overViewText.setText(mResult.getOverview());

    }

}