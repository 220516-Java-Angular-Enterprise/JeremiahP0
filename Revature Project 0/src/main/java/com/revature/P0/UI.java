package com.revature.P0;

import Utilities.UserService;
import Utilities.Util;

public class UI {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Menu test = new Menu(userService);
        //Util utilities = new Util();
        test.mainMenu();

    }
}
