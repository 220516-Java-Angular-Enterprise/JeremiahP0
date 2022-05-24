package com.revature.P0;

import Utilities.UserMenu;
import Utilities.UserService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu implements UserMenu {

    private final UserService userService;

    public Menu(UserService userService) {
        this.userService = userService;
    }


    public void startMenu(UserModel user) {
        System.out.println("Hello " + user.getUsername());
        mainMenu();
    }

    public void mainMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to GameGo");
        System.out.println("[0] to Log in\n[1] to Sign up\n[2] to quit");
        int choice = scan.nextInt();
        try {

            switch (choice) {
                case 0:
                    logIn();
                    break;
                case 1:
                    signUp();
                    break;
                case 2:
                    System.out.println("Good Bye!");
                    break;
                default:
                    System.out.println("Enter 0,1 or 2");
                    mainMenu();

            }

        } catch (InputMismatchException e) {
            System.out.println("Enter 0,1 or 2");
            mainMenu();

        }

    }

    private void signUp() {


        Scanner scan = new Scanner(System.in);
        String username;
        String password;

        System.out.println("Initiating sign up\n");

        while (true) {
            System.out.println("Enter your new user name: ");
            username = scan.nextLine();
            if (userService.isUserValid(username)) {

                System.out.println("username is set to: " + username);
                break;

            } else {
                System.out.println("Username contains invalid characters: (?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,24}");

            }

        }

        confirmExit:
        {
            while (true) {
                System.out.println("Enter your new password:");
                password = scan.nextLine();

                if (userService.isPassValid(password)) {
                    System.out.println("password is set to: " + password);
                    System.out.println("\nConfirm password");
                    String passwordConf = scan.nextLine();


                    if (password.equals(passwordConf)) {
                        System.out.println("Username: " + username + "\n" + "Password: " + password);
                        System.out.println("Confirm 'y/n'");
                        String confirm = scan.nextLine();

                        switch (confirm) {
                            case "y":
                                try {
                                    BufferedWriter bf = new BufferedWriter(new FileWriter("UserDatabase"));

                                    UserModel user = new UserModel(UUID.randomUUID().toString(), username, password, Role.guest);
                                    bf.write(user.getId());
                                    bf.write(username);
                                    bf.write(password);
                                    bf.write(user.getRole());
                                    bf.close();
                                    System.out.println(user);
                                    startMenu(user);
                                    break confirmExit;

                                } catch (IOException e) {
                                    System.out.println("Unable to save data try again later");
                                }


                            case "n":
                                signUp();
                                break confirmExit;

                            default:
                                while (true) {
                                    System.out.println("enter y to confirm or n to reEnter");
                                    confirm = scan.nextLine();
                                    if (confirm.equals("y")) break confirmExit;
                                    else if (confirm.equals("n")) break;
                                }

                        }


                    } else System.out.println("Password does not match, Re-authenticate");

                } else {
                    System.out.println("password contains invalid characters: (?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,24}");
                }


            }


        }


    }


    private void logIn() {
        Scanner scan = new Scanner(System.in);
        String username;

        String password;
        System.out.println("Enter your user name ");
        username = scan.nextLine();
        System.out.println("Enter your password ");
        password = scan.nextLine();

        if (username.equals(username) && password.equals(password)) {
            System.out.println("Welcome");
        } else if (username.equals(username) && !password.equals(password)) {
            System.out.println("password is incorrect");
        } else if (password.equals(password) && !username.equals(username)) {
            System.out.println("username is incorrect");
        } else {
            System.out.println("username and password is incorrect");
        }
    }

}
