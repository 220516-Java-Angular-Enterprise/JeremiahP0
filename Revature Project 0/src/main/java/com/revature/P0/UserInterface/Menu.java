package com.revature.P0.UserInterface;

import com.revature.P0.DataBase.GameDAO;
import com.revature.P0.DataBase.ReviewDAO;
import com.revature.P0.DataBase.UserDAO;
import com.revature.P0.Services.GameService;
import com.revature.P0.Services.ReviewService;
import com.revature.P0.Services.UserService;
import com.revature.P0.Utilities.annotations.Inject;
import com.revature.P0.Utilities.custom_exceptions.InvalidUserException;
import com.revature.P0.models.UserModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu implements IMenu {

    @Inject

    private final UserService userService;


    @Inject
    public Menu(UserService userService) {
        this.userService = userService;
    }


    private void displayWelcomeMsg() {

        System.out.println("\n+------------------+");
        System.out.println("| Welcome to GameGo |");
        System.out.println("+------------------+");
        System.out.println("[1] Login");
        System.out.println("[2] Signup");
        System.out.println("[x] Exit");
    }

    @Override
    public void start() {

        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                displayWelcomeMsg();

                /* Asking user to enter in their input. */
                System.out.print("\nEnter: ");
                String input = scan.nextLine();

                /* Switch case, basically if else statement but more simple. */
                switch (input) {

                    case "1":

                        logIn();
                        break;
                    case "2":

                        signUp();
                        break;
                    case "x":
                        System.out.println("\nSee you next time!");
                        /* Breaking out of everything. */
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }

    private void signUp() {


        Scanner scan = new Scanner(System.in);
        String username;
        String password;

        System.out.println("Initiating sign up\n");

        confirmExit:
        {
            while (true) {
                System.out.println("Opening account creation");

                while (true) {
                    System.out.println("\nEnter Usernane: ");
                    username = scan.nextLine();

                    try {
                        if (userService.isUserValid(username)) {
                            if (userService.isNotDuplicateUsername(username)) break;
                        }
                    } catch (InvalidUserException error) {
                        System.out.println("Unable to reach Server, try again");
                        System.out.println(error.getMessage());
                    }
                }


                while (true) {
                System.out.println("\nPassword: ");
                password = scan.nextLine();

                try{
                    if (userService.isPassValid(password)){
                        System.out.println("\nConfirm password: ");
                        String confirm = scan.nextLine();

                        if (password.equals(confirm)) break;
                        else System.out.println("Password does not match");
                    }

                } catch (InvalidUserException error){
                    System.out.println(error.getMessage());
                }

                }

                //End of address
                //start
                        Scanner option = new Scanner(System.in);
                        UserModel userModel = new UserModel();
                        while (true) {
                            System.out.println("Enter your state ex: PA for pennsylvania");
                            try {
                                String input = option.nextLine();
                                if ((userService.isStateValid(input))) {
                                    userModel.setState(input);
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Enter two characters for your state ex: PA for pennsylvania");
                            }
                        }
                        while (true) {
                            System.out.println("Enter your city");
                            try {
                                String input = option.nextLine();
                                userModel.setCity(input);
                                break;

                            } catch (InputMismatchException e) {
                                System.out.println("\nInvalid input");
                            }
                        }
                        while (true) {
                            System.out.println("Enter your address");
                            try {
                                String input = option.nextLine();

                                userModel.setAddress(input);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("\nInvalid input");
                            }
                        }

                        //Scanner choice = new Scanner(System.in);


                //end
                Exit:{
                    while (true){
                        System.out.println("Are these the credentials you want? y/n");

                        System.out.println("Username: " + username);
                        System.out.println("Password: " + password);
                        System.out.println("Is this the address you want configured? y/n");
                        System.out.println("  State: " + userModel.getState());
                        System.out.println("   City: " + userModel.getCity());
                        System.out.println("Address: " + userModel.getAddress());

                        System.out.println("\nEnter: ");
                        String input = scan.nextLine();
                        switch(input) {

                            case "y":

                                UserModel user = new UserModel(UUID.randomUUID().toString(),username,password, "GUEST",userModel.getAddress(),userModel.getCity(),userModel.getState());

                                userService.register(user);

                                new MainMenu(user, new UserService(new UserDAO()), new ReviewService(new ReviewDAO()), new GameService(new GameDAO())).start();
                                break confirmExit;
                            case "n": break Exit;


                            default: System.out.println("Enter either y for yes, n for no");
                        }

                    }
                }
            }
        }

    }


    private void logIn() {
        Scanner scan = new Scanner(System.in);
        String username;

        String password;
        UserModel user = new UserModel();


        while (true) {


            System.out.println("Enter your user name ");
            username = scan.nextLine();
            System.out.println("Enter your password ");
            password = scan.nextLine();

            try {
                user = userService.login(username, password);
                if (user.getRole().equals("ADMIN"))
                    new AdminMenu(user, new GameService(new GameDAO())).start();
                else
                    new MainMenu(user, new UserService(new UserDAO()),new ReviewService(new ReviewDAO()), new GameService(new GameDAO())).start();
                break;
            } catch (InvalidUserException error) {
                System.out.println(error.getMessage());
            }

        }
    }


}
