package com.lesnyg.test2movieinfoproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lesnyg.test2movieinfoproject.databinding.FragmentFavoritesListBinding;
import com.lesnyg.test2movieinfoproject.models.Result;



public class FavoritesListFragment extends Fragment {
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

        FragmentFavoritesListBinding binding= DataBindingUtil.bind(view);
        MovieViewModel viewModel = ViewModelProviders.of(requireActivity())
                .get(MovieViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}
