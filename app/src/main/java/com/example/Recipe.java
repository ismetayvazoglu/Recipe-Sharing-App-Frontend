package com.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {


    private String id;
    private String imageURL;
    private String rName;
    private String ingredients;
    private String description;
    private String type;
    private Nutrition nutrition;
    private ArrayList<Review> reviews;


    public Recipe() {
        // TODO Auto-generated constructor stub
    }

    public Recipe(String id, String image, String rName) {
        super();
        this.id = id;
        this.imageURL = image;
        this.rName = rName;
    }
    public Recipe(String id, String image, String rName, String ingredients, String description, String type,
                  Nutrition nutrition) {
        super();
        this.id = id;
        this.imageURL = image;
        this.rName = rName;
        this.ingredients = ingredients;
        this.description = description;
        this.type = type;
        this.nutrition = nutrition;
    }



    public Recipe(String id, String image, String rName, String ingredients, String description, String type,
                  Nutrition nutrition, ArrayList<Review> reviews) {
        super();
        this.id = id;
        this.imageURL = image;
        this.rName = rName;
        this.ingredients = ingredients;
        this.description = description;
        this.type = type;
        this.nutrition = nutrition;
        this.reviews = reviews;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }





    public String getrName() {
        return rName;
    }


    public void setrName(String rName) {
        this.rName = rName;
    }


    public String getIngredients() {
        return ingredients;
    }


    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Nutrition getNutrition() {
        return nutrition;
    }


    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }


    public ArrayList<Review> getReviews() {
        return reviews;
    }


    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }


    public String getImageURL() {
        return imageURL;
    }


    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    // Other necessary methods
}
