package com.example;

import java.io.Serializable;

public class Nutrition implements Serializable {


    private int kcal;
    private String fat;
    private String protein;
    private String carbs;
    private String sugars;
    private String salt;


    public Nutrition() {
        // TODO Auto-generated constructor stub
    }

    public Nutrition(int kcal, String fat, String protein, String carbs, String sugars, String salt) {
        super();
        this.kcal = kcal;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.sugars = sugars;
        this.salt = salt;
    }

    public int getKcal() {
        return kcal;
    }
    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
    public String getFat() {
        return fat;
    }
    public void setFat(String fat) {
        this.fat = fat;
    }
    public String getProtein() {
        return protein;
    }
    public void setProtein(String protein) {
        this.protein = protein;
    }
    public String getCarbs() {
        return carbs;
    }
    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }
    public String getSugars() {
        return sugars;
    }
    public void setSugars(String sugars) {
        this.sugars = sugars;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Nutrition Values: kcal:" + kcal + ", fat:" + fat + ", protein:" + protein + ", carbs:" + carbs + ", sugars:"
                + sugars + ", salt:" + salt;
    }


}
