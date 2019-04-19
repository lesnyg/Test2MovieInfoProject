package com.lesnyg.test2movieinfoproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lesnyg.test2movieinfoproject.models.Latest;
import com.lesnyg.test2movieinfoproject.models.LatestResult;
import com.lesnyg.test2movieinfoproject.models.Result;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieGridFragment extends Fragment {

    private static final String ARG_MVIMAGE = "movieImage";
    private static final String ARG_MVTITLE = "movieTitle";
    private static final String MY_KEY = "0882850438bd0da4458576be7d4a447c";
    private static final String MY_COUNTRY = "ko-KR";


    private int mMovieImage;
    private String mMovieTitle;

    RecyclerView mRecycler;
    MovieRecyclerAdapter mAdapter;

    private List<LatestResult> mLatestResults = new ArrayList<>();
    private List<Result> mResults = new ArrayList<>();


    public MovieGridFragment() {
        // Required empty public constructor
    }

    public static MovieGridFragment newInstance(int movieImage, String movieTitle) {
        MovieGridFragment fragment = new MovieGridFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MVIMAGE, movieImage);
        args.putString(ARG_MVTITLE, movieTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieImage = getArguments().getInt(ARG_MVIMAGE);
            mMovieTitle = getArguments().getString(ARG_MVTITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecycler = view.findViewById(R.id.recyclerview);
        mAdapter = new MovieRecyclerAdapter();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService service = retrofit.create(MovieService.class);

        service.getLatest(MY_KEY,MY_COUNTRY).enqueue(new Callback<Latest>() {
            @Override
            public void onResponse(Call<Latest> call, Response<Latest> response) {
                Latest latest = response.body();
                mLatestResults = latest.getResults();
                mAdapter.setitems(mLatestResults);
                mRecycler.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Latest> call, Throwable t) {

            }
        });

        SearchView searchView = (SearchView) view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                List<Result> filteredList = new ArrayList<>();
                for (int i = 0; i < mResults.size(); i++) {
                    Result result = mResults.get(i);
                    if (result.getTitle().toLowerCase().trim()
                            .contains(s.toLowerCase().trim())) {
                        filteredList.add(result);
                    }
                }

               // mAdapter.setitems(filteredList);


                return true;
            }

        });


    }
}



