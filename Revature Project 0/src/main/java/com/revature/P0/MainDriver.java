package com.revature.P0;

import com.revature.P0.DataBase.UserDAO;
import com.revature.P0.Services.UserService;
import com.revature.P0.UserInterface.MainMenu;
import com.revature.P0.UserInterface.Menu;
import com.revature.P0.Utilities.database.DatabaseConnection;

public class MainDriver {
    public static void main(String[] args) {
    //UserDAO userDAO = new UserDAO();
    //UserService userService = new UserService(userDAO);

         System.out.println(DatabaseConnection.getCon());
        new Menu(new UserService(new UserDAO())).start();

        //new MainMenu(new UserService(new UserDAO())).start();
    }
}
