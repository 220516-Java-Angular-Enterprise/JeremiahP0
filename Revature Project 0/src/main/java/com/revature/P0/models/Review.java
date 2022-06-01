package com.revature.P0.models;

public class Review {
    private String reviewID;
    private String itemUPC;
    private String rating;
    private String userID;
    private String reviews;
    private double gameScore;

    public Review(){}

    public Review(String reviewID, String itemUPC, String userID,String rating, String reviews, double gameScore) {
        this.userID = userID;
        this.reviewID = reviewID;
        this.itemUPC = itemUPC;
        this.rating = rating;
        this.reviews = reviews;
        this.gameScore = gameScore;
    }


    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getItemUPC() {
        return itemUPC;
    }

    public void setItemUPC(String itemUPC) {
        this.itemUPC = itemUPC;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return reviews;
    }

    public void setReview(String review) {
        this.reviews = review;
    }

    public double getGameScore() {
        return gameScore;
    }

    public void setGameScore(double gameScore) {
        this.gameScore = gameScore;
    }

    public String getUser_id() {
        return userID;
    }

    public void setUser_id(String user_id) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Review[ " +
                " rating: " + rating + '\n' +
                " review:" + reviews + '\n' +
                " gameScore: " + gameScore +
                ']';
    }
}
