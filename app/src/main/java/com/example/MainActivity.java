package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHost = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController navController = navHost.getNavController();

        AppBarConfiguration conf = new AppBarConfiguration.Builder(navController.getGraph()).build();

        toolBar = findViewById(R.id.toolbar);

        toolBar.setTitle("Home");
        NavigationUI.setupWithNavController(toolBar,navController,conf);

    }

    public Toolbar getToolBar() {
        return toolBar;
    }
}