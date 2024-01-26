package com.example;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(getLayoutInflater());

        ((MainActivity)getActivity()).getToolBar().setTitle("Home");

        binding.createBtn.setOnClickListener(v->{

            NavController navController =
                    Navigation.findNavController(getActivity(),R.id.fragmentContainer);

            navController.navigate(R.id.action_firstFragment_to_createFragment);

        });

        binding.findBtn.setOnClickListener(v->{

            NavController navController =
                    Navigation.findNavController(getActivity(),R.id.fragmentContainer);

            navController.navigate(R.id.action_firstFragment_to_findFragment);

        });

        return binding.getRoot();
    }
}
