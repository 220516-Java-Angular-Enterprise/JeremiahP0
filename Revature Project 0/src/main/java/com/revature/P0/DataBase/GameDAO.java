package com.revature.P0.DataBase;

import com.revature.P0.Utilities.custom_exceptions.InvalidSQLException;
import com.revature.P0.Utilities.database.DatabaseConnection;
import com.revature.P0.models.Game;
import com.revature.P0.models.OrderModel;
import com.revature.P0.models.UserModel;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO implements BaseDAO<Game> {


    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Game obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO items (itemupc, name, description, gametype, rating, gamescore, price, itemcondition, review) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, obj.getGameUPC());
            ps.setString(2, obj.getGameName());
            ps.setString(3, obj.getDescription());
            ps.setString(4, obj.getGameType());
            ps.setString(5, obj.getRating());
            ps.setDouble(6, obj.getGameScore());
            ps.setDouble(7, obj.getPrice());
            ps.setString(8, obj.getCondition());
            ps.setString(9,obj.getReview());
            //ps.setString(10,"2172");
            ps.executeQuery();
        } catch (SQLException error) {
            System.out.println("An error occurred while trying to save to the database");
        }

    }

    @Override
    public void update(Game obj) {

        try {

            PreparedStatement ps = con.prepareStatement("UPDATE FROM items WHERE (itemupc, name, description, gametype, rating, gamescore, price, itemcondition) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, obj.getGameUPC());
            ps.setString(2, obj.getGameName());
            ps.setString(3, obj.getDescription());
            ps.setString(4, obj.getGameType());
            ps.setString(5, obj.getRating());
            ps.setDouble(6, obj.getGameScore());
            ps.setDouble(7, obj.getPrice());
            ps.setString(8, obj.getCondition());
            ps.executeQuery();
        } catch (SQLException error) {
            throw new InvalidSQLException("An error occurred while trying to update the database");
        }
    }

    public void updateGameName(String gameName, String id) {
      try{
          PreparedStatement ps = con.prepareStatement("UPDATE items SET name = (?) WHERE itemupc = (?)");
          ps.setString(1, gameName);
          ps.setString(2, id);
          ps.executeUpdate();

          //gameDAO
      }
      catch(SQLException error){
          System.out.println(error.getMessage());
      }

    }

    public void updateDescription(String description, String id) {

        try{
            PreparedStatement ps = con.prepareStatement("UPDATE items SET description = (?) WHERE itemupc = (?)");
            ps.setString(1, description);
            ps.setString(2, id);
            ps.executeUpdate();

            //gameDAO
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }

    public void updateType(String type, String id) {

        try{
            PreparedStatement ps = con.prepareStatement("UPDATE items SET gametype = (?) WHERE itemupc = (?)");
            ps.setString(1, type);
            ps.setString(2, id);
            ps.executeUpdate();

            //gameDAO
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }

    public void updateRating(String rating, String id) {

        try{
            PreparedStatement ps = con.prepareStatement("UPDATE items SET rating = (?) WHERE itemupc = (?)");
            ps.setString(1, rating);
            ps.setString(2, id);
            ps.executeUpdate();

            //gameDAO
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }

    public void updateGameScore(double gameScore, String id) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE items SET gamescore = (?) WHERE itemupc = (?)");
            ps.setDouble(1, gameScore);
            ps.setString(2, id);
            ps.executeUpdate();

            //gameDAO
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }

    public void updatePrice(double price, String id) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE items SET price = (?) WHERE itemupc = (?)");
            ps.setDouble(1, price);
            ps.setString(2, id);
            ps.executeUpdate();

            //gameDAO
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }

    public void updateCondition(String condition, String id) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE items SET itemcondition = (?) WHERE itemupc = (?)");
            ps.setString(1, condition);
            ps.setString(2, id);
            ps.executeUpdate();

            //gameDAO
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM items WHERE itemupc = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException error) {
            System.out.println("An error occurred while trying to delete this item");

        }

    }

    @Override
    public Game getById(String id) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                games.add(new Game(rs.getString("itemupc"), rs.getString("name"), rs.getString("description"), rs.getString("gametype"), rs.getString("rating"), rs.getDouble("gamescore"), rs.getDouble("price"), rs.getString("itemcondition"),rs.getString("console"),rs.getString("review")));
            }
        } catch (SQLException error) {
            throw new RuntimeException("An error occurred when trying to get all Items");
        }
        return games;
    }
    public void orderGame(Game game, OrderModel order, UserModel user) {

        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (orderid,userid,itemupc,quantity,shippeddate,total) VALUES (?,?,?,?,?,?)");
            ps.setString(1, order.getOrderid());
            ps.setString(2, user.getUserId());
            ps.setString(3,game.getGameUPC());
            ps.setDate(4, (Date) order.getDate());
            ps.setInt(5, order.getQuantity());
            ps.setDouble(6,game.getPrice());

            ps.executeUpdate();
        }
        catch(SQLException error){
            System.out.println("Could not send order, please try again.");
        }
    }

    public void purchaseGame(OrderModel order){
        try{PreparedStatement ps = con.prepareStatement("INSERT INTO orders (orderid, userid, itemupc, shippeddate, quantity, total) VALUES (?,?,?,?,?,?)");
            ps.setString(1, order.getOrderid());
            ps.setString(2, order.getUserid());
            ps.setString(3,order.getItemupc());
            ps.setDate(4, (Date) order.getDate());
            ps.setInt(5, order.getQuantity());
            ps.setDouble(6,order.getTotal());


            //PreparedStatement ps = con.prepareStatement("UPDATE items SET name = ? WHERE itemupc = ?");
            //PreparedStatement ps = con.prepareStatement("INSERT INTO orders WHERE orderid = ?, userid = ?, itemupc = ?)");
            //ps.setString(1,order.getOrderid());
            //ps.setString(2,order.getUserid());
            //ps.setString(3,order.getItemupc());
            ps.executeQuery();
        }
        catch(SQLException error){
            System.out.println("An error occurred while trying to add game to the cart");
        }

    }


}
