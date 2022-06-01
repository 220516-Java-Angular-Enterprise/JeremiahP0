package com.revature.P0.models;

public class Game {
    private String gameUPC;
    private String gameName;
    private String description;
    private String gameType;
    private String rating;
    private Double gameScore;
    private Double price;
    private String console;
    private String condition;
    private String review;


    public Game(){
        super();
    }
   public Game(String gameUPC, String gameName,String description,String gameType, String rating,Double gameScore, Double price,String console,String condition, String review){
        this.gameUPC = gameUPC;
        this.gameName = gameName;
        this.description = description;
        this.gameType = gameType;
        this.rating = rating;
        this.gameScore = gameScore;
        this.price = price;
        this.console = console;
        this.condition = condition;
        this.review = review;

    }



    public String getGameUPC(){
        return gameUPC;
    }

    public String getGameName() {
        return gameName;
    }

    public String getDescription() {
        return description;
    }

    public String getGameType() {
        return gameType;
    }

    public String getRating() {
        return rating;
    }


    public Double getGameScore() {
        return gameScore;
    }

    public Double getPrice() {
        return price;
    }

    public String getConsole() {
        return console;
    }

    public String getCondition() {
        return condition;
    }
    public String getConToString(){
        return condition.toString();
    }

    public void setGameUPC(String gameUPC) {
        this.gameUPC = gameUPC;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setGameScore(Double gameScore) {
        this.gameScore = gameScore;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
