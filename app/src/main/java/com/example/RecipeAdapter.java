package com.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    Context ctx;
    List<Recipe> mRecipes;

    public RecipeAdapter(Context ctx, List<Recipe> data){
        this.ctx = ctx;
        this.mRecipes = data;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_one, parent, false);
        RecipeViewHolder holder = new RecipeViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        //Recipe recipe = mRecipes.get(position);
        holder.textView.setText(mRecipes.get(position).getrName());

        Glide.with(ctx).load(mRecipes.get(position).getImageURL()).into(holder.imageView);

        holder.row.setOnClickListener(v->{

            NavController navController =
                    Navigation.findNavController((Activity) ctx,R.id.fragmentContainer);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("id",mRecipes.get(holder.getAdapterPosition()).getId());


            navController.navigate(R.id.action_findFragment_to_detailFragment,dataBundle);
        });


    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        ConstraintLayout row;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipeImageView);
            textView = itemView.findViewById(R.id.comment);
            row = itemView.findViewById(R.id.row_list);
        }

    }
}
