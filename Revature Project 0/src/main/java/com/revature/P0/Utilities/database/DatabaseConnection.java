package com.revature.P0.Utilities.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection con;

    private static final Properties prop = new Properties();

    static{
        try{
            Class.forName("org.postgresql.Driver");

            prop.load(new FileReader("src/main/resources/db.properties"));

            con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"), prop.getProperty("password"));
        }
        catch(ClassNotFoundException | SQLException | IOException error){
           error.printStackTrace();
        }

    }

    public static Connection getCon() {return con;}
}
