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

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Context ctx;
    List<Review> data;

    public ReviewAdapter(Context ctx, List<Review> data){
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewlist_one, parent, false);
        ReviewAdapter.ReviewViewHolder holder = new ReviewAdapter.ReviewViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        /*
        holder.username.setText(data.get(position).getuName());
        holder.comment.setText(data.get(position).getComment());
        //holder.dynamicRate.setText(data.get(position).getRate());
        holder.dynamicRate.setText(String.valueOf(data.get(position).getRate()));
        */
        Review review = data.get(position);
        if (review != null) {
            holder.username.setText(review.getuName() != null ? review.getuName() : "");
            holder.comment.setText(review.getComment() != null ? review.getComment() : "");
            holder.dynamicRate.setText(String.valueOf(review.getRate()));
        }


        /*
        holder.row.setOnClickListener(v->{

            NavController navController =
                    Navigation.findNavController((Activity) ctx,R.id.fragmentContainer);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("id",data.get(holder.getAdapterPosition()).getId());


            navController.navigate(R.id.action_findFragment_to_detailFragment,dataBundle);
        });
        */
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView staticRate;
        public TextView dynamicRate;
        public TextView username;
        public TextView comment;
        ConstraintLayout row;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            staticRate = itemView.findViewById(R.id.textView35);
            dynamicRate = itemView.findViewById(R.id.rate);
            username = itemView.findViewById(R.id.uName);
            comment = itemView.findViewById(R.id.comm);
            row = itemView.findViewById(R.id.row_liste);
        }

    }

}

