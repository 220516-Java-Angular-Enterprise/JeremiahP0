package com.revature.P0.Services;

import com.revature.P0.DataBase.GameDAO;
import com.revature.P0.Utilities.custom_exceptions.InvalidSQLException;
import com.revature.P0.models.Game;
import com.revature.P0.models.OrderModel;

import java.util.List;

public class GameService {
    private final GameDAO gameDAO;

    public GameService(GameDAO gameDao) {
        this.gameDAO = gameDao;
    }


    public void register(Game game){gameDAO.save(game);}

    public void buyGame(OrderModel order){gameDAO.purchaseGame(order);}



    public List<Game> getAllGames(){return gameDAO.getAll();}



    public boolean updateName(String name, String id){
        try{
            gameDAO.updateGameName(name, id);
            return true;
        }
        catch(InvalidSQLException error){
            error.getMessage();

        }
        return false;
    }


    public boolean deleteGame(String id){
        try{
            gameDAO.delete(id);
            return true;
        }
        catch(InvalidSQLException error){
           System.out.println(error.getMessage());

        }
        return false;
    }

    public boolean isGamePurchased(String id){
        try{
            gameDAO.getById(id);
            return true;
        }
        catch (InvalidSQLException error){
            System.out.println(error.getMessage());
        }
        return false;
    }

    public String addToCart(String gameName,Double price){

        return "Game: ["+ gameName + "] price: [" + price.toString() + "]";

    }



}
