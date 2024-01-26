package com.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.databinding.FragmentCreateReviewBinding;

import java.util.concurrent.ExecutorService;

public class CreateReviewFragment extends Fragment {

    FragmentCreateReviewBinding binding;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateReviewBinding.inflate(getLayoutInflater());

        ((MainActivity)getActivity()).getToolBar().setTitle("Express Your Feelings");

        binding.submitButton.setOnClickListener(v->{

            String id =  getArguments().getString("id");
            RecipiRepository repo = new RecipiRepository();
            ExecutorService srv = ((RecipiApplication)getActivity().getApplication()).srv;
            repo.saveReview(srv, binding.username.getText().toString(),
                    Integer.parseInt(binding.RateInput.getText().toString()),
                    binding.commentText.getText().toString(), id);

            Toast toast = Toast.makeText(getActivity(), "Your Review has been Posted Successfully", Toast.LENGTH_LONG);
            toast.show();
            /*
            NavController navController =
                    Navigation.findNavController(getActivity(),R.id.fragmentContainer);
            navController.navigate(R.id.action_createReviewFragment_to_reviewListFragment);
             */
        });

        return binding.getRoot();
    }
}
