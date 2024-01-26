package com.example;


import java.io.Serializable;

public class Review implements Serializable {

    private String uName;
    private int rate;
    private String comment;


    public Review() {
        // Default constructor
    }

    public Review(String uName, int rate, String comment) {
        this.uName = uName;
        this.rate = rate;
        this.comment = comment;
    }

    // Getter and Setter methods

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review [uName=" + this.uName + ", rate=" + this.rate + ", comment=" + this.comment + "]";
    }

    // Other necessary methods
}
