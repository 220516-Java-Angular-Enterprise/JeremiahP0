package com.revature.P0.DataBase;



import com.revature.P0.Utilities.database.DatabaseConnection;
import com.revature.P0.models.AddressModel;
import com.revature.P0.models.Game;
import com.revature.P0.models.OrderModel;
import com.revature.P0.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements BaseDAO<UserModel> {
Connection con = DatabaseConnection.getCon();
String path = "src/main/resources/database/user.txt";

    @Override
    public void save(UserModel obj) {

        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (userid, username, password, role, state, city, address) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, obj.getUserId());
            ps.setString(2,obj.getUsername());
            ps.setString(3,obj.getPassword());
            ps.setString(4,obj.getRole());
            ps.setString(5, obj.getState());
            ps.setString(6,obj.getCity());
            ps.setString(7,obj.getAddress());
            ps.executeUpdate();
        }
        catch(SQLException error){
            System.out.println("Could not save, please try again.");
        }
    }



    @Override
    public void update(UserModel obj) {

    }

    @Override
    public void delete(String id) {
        /*
        try{PreparedStatement ps = con.prepareStatement("DELETE ")}
        catch(SQLException error){
            System.out.println("An error occured while trying to delele");
        }

         */

    }

    @Override
    public UserModel getById(String id) {

        try{
            PreparedStatement ps = con.prepareStatement("SELECT userid from users where username = (?)");
            ResultSet rs = ps.executeQuery();
                UserModel user = new UserModel();
                user.setId(rs.getString("userid"));


            return user;


        }
        catch(SQLException error){System.out.println("An error occured while trying to retrieve Id");}

        return null;
    }

    @Override
    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<>();
        try{
            PreparedStatement ps =  con.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                UserModel user = new UserModel();
                user.setId(resultSet.getString("userid"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                users.add(user);

            }
        }
        catch(SQLException error){
          throw new RuntimeException("Could not reach serve, try again.");
        }

        return users;
    }

    public List<String> getAllUsernames(){
        List<String> usernames = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                usernames.add(rs.getString("username").toLowerCase());
            }
        }
        catch(SQLException error){
            throw new RuntimeException("An error occurred while trying to retrieve the username");
        }
       return usernames;
    }




    private void custoOrder(Game game, OrderModel order, List<Game> list){
       List<String> games = new ArrayList<>();
        try{
            //not done
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?)");

            ps.setString(1, order.getOrderid());
            ps.setString(2,game.getGameUPC());
            ps.setInt(3,order.getQuantity());
            ps.setDouble(4,order.getTotal());
            ps.setString(5,order.getUserid());
             ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    games.add(rs.getString("itemupc"));
                }

        }
        catch(SQLException error1){
            System.out.println("An error occurred while trying to reach the database");
        }


    }

    public String getDatId(UserModel user){
        try{
            PreparedStatement ps = con.prepareStatement("SELECT userid FROM users WHERE username = (?)");
            ResultSet rs = ps.executeQuery();
            //UserModel user = new UserModel();
            user.setId(rs.getString("userid"));


            return user.getUserId().toString();


        }
        catch(SQLException error){System.out.println("An error occured while trying to retrieve Id");}

        return null;
    }




}
