package com.revature.P0.DataBase;

import com.revature.P0.Utilities.database.DatabaseConnection;
import com.revature.P0.models.Game;
import com.revature.P0.models.OrderModel;
import com.revature.P0.models.UserModel;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderDAO implements BaseDAO<OrderModel>{

    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(OrderModel obj) {

    }

    @Override
    public void update(OrderModel obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public OrderModel getById(String id) {
        return null;
    }

    @Override
    public List<OrderModel> getAll() {
        return null;
    }



}
