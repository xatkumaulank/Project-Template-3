package com.example.customnavigationdrawler.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.customnavigationdrawler.R;
import com.example.customnavigationdrawler.databinding.FragmentHistoryBinding;


public class HistoryFragment extends Fragment {

    FragmentHistoryBinding binding;
    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_history,container,false);
        return binding.getRoot();
    }
}