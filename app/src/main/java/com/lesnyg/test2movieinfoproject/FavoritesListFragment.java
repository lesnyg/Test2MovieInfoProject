package com.lesnyg.test2movieinfoproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lesnyg.test2movieinfoproject.databinding.FragmentFavoritesListBinding;
import com.lesnyg.test2movieinfoproject.models.Result;

import java.util.ArrayList;
import java.util.List;


public class FavoritesListFragment extends Fragment {

    RecyclerView mRecycler;
    MovieRecyclerAdapter mAdapter;

    private List<Result> mResults = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    private MovieViewModel mModel;
    public FavoritesListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieViewModel viewModel = ViewModelProviders.of(requireActivity())
                .get(MovieViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_favorites);

        MovieFavoriteAdapter adapter = new MovieFavoriteAdapter(new MovieFavoriteAdapter.OnFavoriteClickListener() {
            @Override
            public void onFavoriteClick(Result item) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.fragment_main, MovieDetailFragment.newInstance(item));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        recyclerView.setAdapter(adapter);
        viewModel.favoritList.observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                adapter.setitems(results);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });


    }


}
