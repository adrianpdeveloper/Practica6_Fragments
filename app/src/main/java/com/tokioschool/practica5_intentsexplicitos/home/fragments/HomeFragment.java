package com.tokioschool.practica5_intentsexplicitos.home.fragments;

import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_USER;
import static com.tokioschool.practica5_intentsexplicitos.Constants.KEY_USER_BUNDLE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tokioschool.practica5_intentsexplicitos.R;
import com.tokioschool.practica5_intentsexplicitos.databinding.ActivityHomeBinding;
import com.tokioschool.practica5_intentsexplicitos.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listeners();
    }

    public void fragmentRentCar() {
        getChildFragmentManager().beginTransaction().add(binding.homeactivityFragmentFrame.getId(), new RentCarFragment()).commitAllowingStateLoss();
    }

    private void listeners() {

    }
}