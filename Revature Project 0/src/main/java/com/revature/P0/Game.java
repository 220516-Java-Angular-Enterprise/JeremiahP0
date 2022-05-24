package com.revature.P0;

enum ConsoleType
{
    Playstation,
    Xbox,
    Nintendo
        }

        enum Condition{
    New,
    Used
        }
        enum Rating{
    E, E10, T, M17, AO
        }
interface adminMenu{
    void addItemInventory();
    void checkInventory();
}


public abstract class Game {
    private String gameName;
    private String description;
    private String gameType;
    private Rating rating;
    private Double gameScore;
    private Double price;
    private ConsoleType console;
    private Condition condition;





    Game(){

    }
    Game(String gameName,String description,String gameType, Rating rating,Double gameScore, Double price,ConsoleType console,Condition condition){

        this.gameName = gameName;
        this.description = description;
        this.gameType = gameType;
        this.rating = rating;
        this.gameScore = gameScore;
        this.price = price;
        this.console = console;
        this.condition = condition;

    }


}
