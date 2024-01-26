package com.example;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.databinding.FragmentDetailBinding;

import java.util.concurrent.ExecutorService;

public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Recipe rec = (Recipe) msg.obj;
            binding.descriptionTitle.setText(rec.getrName());
            binding.descriptionDescription.setText(rec.getDescription());
            binding.descriptionIngredients.setText(rec.getIngredients());
            binding.descriptionNutrition.setText(rec.getNutrition().toString());
            binding.descriptionType.setText(rec.getType());

            Glide.with(getActivity()).load(rec.getImageURL()).into(binding.descriptionImage);

            ((MainActivity)getActivity()).getToolBar().setTitle("Hope You Enjoy This!");


            return true;

        }
    });
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(getLayoutInflater());


        String id =  getArguments().getString("id");
        RecipiRepository repo = new RecipiRepository();
        ExecutorService srv = ((RecipiApplication)getActivity().getApplication()).srv;
        repo.getRecipeById(srv,handler,id);

        binding.descriptionButtonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController =
                        Navigation.findNavController(getActivity(), R.id.fragmentContainer);

                Bundle dataBundle = new Bundle();
                dataBundle.putString("id",id);

                navController.navigate(R.id.action_detailFragment_to_reviewListFragment,dataBundle);
            }
        });

        return binding.getRoot();
    }
}