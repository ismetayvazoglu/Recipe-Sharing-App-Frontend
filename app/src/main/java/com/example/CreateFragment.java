package com.example;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.databinding.FragmentCreateBinding;

import java.util.concurrent.ExecutorService;

public class CreateFragment extends Fragment {

    FragmentCreateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateBinding.inflate(getLayoutInflater());

        ((MainActivity)getActivity()).getToolBar().setTitle("Create Your Own Recipe!");

        Spinner spinner = binding.spinnertype;
        ArrayAdapter<CharSequence> spin_adapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.spinner_items, // Reference to the string array in strings.xml
                android.R.layout.simple_spinner_item
        );
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spin_adapter);


        binding.btnPost.setOnClickListener(v->{

            RecipiRepository repo = new RecipiRepository();
            ExecutorService srv = ((RecipiApplication)getActivity().getApplication()).srv;

            repo.saveRecipe(srv, binding.urlofR.getText().toString(),binding.name.getText().toString(),
                    binding.ingredients.getText().toString(), binding.description.getText().toString(),
                    binding.spinnertype.getSelectedItem().toString(), Integer.parseInt(binding.kcal.getText().toString()),
                    binding.fat.getText().toString(), binding.protein.getText().toString(),
                    binding.carbs.getText().toString(), binding.sugars.getText().toString(), binding.salt.getText().toString());

            Toast toast = Toast.makeText(getActivity(), "Your Recipe has been Posted Successfully", Toast.LENGTH_LONG);
            toast.show();

            /*
            NavController navController =
                    Navigation.findNavController(getActivity(),R.id.fragmentContainer);
            navController.navigate(R.id.action_createFragment_to_firstFragment);

                Toast.makeText(getActivity(), "Please fill all the fields :)", Toast.LENGTH_SHORT).show();

             */
        });

        return binding.getRoot();
    }
}