package com.revature.P0.DataBase;


import com.revature.P0.Utilities.database.DatabaseConnection;
import com.revature.P0.models.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements BaseDAO<Review>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Review obj) {

    }

    @Override
    public void update(Review obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Review getById(String id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    public List<Review> getReviewsByGameId(String game_id){
        List<Review> reviews = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("Select * FROM reviews WHERE reviewID = (?)");
            ps.setString(1,game_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reviews.add(new Review(rs.getString("reviewID"),rs.getString("itemUPC"),rs.getString("userID"),rs.getString("rating"),rs.getString("reviews"),rs.getDouble("gamescore")));
            }
        }
        catch(SQLException error){
            System.out.println("An error occurred when trying to retrieve the game");
        }
        return reviews;
    }

    public void makeReview(String msg, String id) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE items SET review = (?) WHERE itemupc = (?)");
            ps.setString(1, msg);
            ps.setString(2, id);
            ps.executeUpdate();

            //gameDAO
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }
}
