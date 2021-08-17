package com.example.customnavigationdrawler.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.customnavigationdrawler.R;
import com.example.customnavigationdrawler.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    FragmentFavoriteBinding binding;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false);
        return binding.getRoot();
    }
}