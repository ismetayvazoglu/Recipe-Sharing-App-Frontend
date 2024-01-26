package com.example;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.databinding.FragmentFindBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class FindFragment extends Fragment {

    FragmentFindBinding binding;

    Handler dataHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(@NonNull Message msg){

            List<Recipe> data = (List<Recipe>) msg.obj;

            RecipeAdapter adapter = new RecipeAdapter(getActivity(), data);
            binding.RecylerViewId.setAdapter(adapter);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFindBinding.inflate(getLayoutInflater());

        ((MainActivity)getActivity()).getToolBar().setTitle("Everything looks Delicious!");

        binding.RecylerViewId.setLayoutManager(new LinearLayoutManager(getActivity()));

        Spinner spinnerT = binding.spinnerType;
        ArrayAdapter<CharSequence> spin_adapterT = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.spinner_items, // Reference to the string array in strings.xml
                android.R.layout.simple_spinner_item
        );
        spin_adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerT.setAdapter(spin_adapterT);

        binding.RecylerViewId.setLayoutManager(new LinearLayoutManager(getActivity()));

        Spinner spinnerK = binding.spinnerKcal;
        ArrayAdapter<CharSequence> spin_adapterK = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.spinner_kcal, // Reference to the string array in strings.xml
                android.R.layout.simple_spinner_item
        );
        spin_adapterK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerK.setAdapter(spin_adapterK);

        RecipiRepository repo = new RecipiRepository();
        ExecutorService srv = ((RecipiApplication)getActivity().getApplication()).srv;
        repo.getAllRecipe(srv, dataHandler);

        binding.filtertype.setOnClickListener(v->{
            repo.filterByType(srv, dataHandler, spinnerT.getSelectedItem().toString());
        });

        binding.filterkcal.setOnClickListener(v->{
            repo.filterByKcal(srv, dataHandler, Integer.parseInt(spinnerK.getSelectedItem().toString()));
        });

        binding.imageView.setOnClickListener(v -> {
            repo.search(srv, dataHandler, binding.searchInput.getText().toString());
        });

        return binding.getRoot();
    }
}