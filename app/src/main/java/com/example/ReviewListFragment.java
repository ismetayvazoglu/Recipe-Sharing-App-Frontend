package com.example;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databinding.FragmentReviewListBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class ReviewListFragment extends Fragment {


    FragmentReviewListBinding binding;

    Handler dataHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(@NonNull Message msg){

            List<Review> data = (List<Review>) msg.obj;

            ReviewAdapter adapter = new ReviewAdapter(getActivity(),data);
            binding.ReviewRecycler.setAdapter(adapter);
            return true;
        }
    });

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReviewListBinding.inflate(getLayoutInflater());

        ((MainActivity)getActivity()).getToolBar().setTitle("How People Feel");

        binding.ReviewRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        String id =  getArguments().getString("id");

        RecipiRepository repo = new RecipiRepository();
        ExecutorService srv = ((RecipiApplication)getActivity().getApplication()).srv;
        repo.getAllReview(srv, dataHandler, id);


        binding.butPost.setOnClickListener(view -> {

            NavController navController =
                    Navigation.findNavController(getActivity(), R.id.fragmentContainer);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("id",id);

            navController.navigate(R.id.action_reviewListFragment_to_createReviewFragment,dataBundle);
        });


        return binding.getRoot();
    }
}