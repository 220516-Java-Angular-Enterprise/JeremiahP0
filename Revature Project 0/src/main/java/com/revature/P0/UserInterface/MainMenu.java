package com.revature.P0.UserInterface;

import com.revature.P0.Services.GameService;
import com.revature.P0.Services.ReviewService;
import com.revature.P0.Services.UserService;
import com.revature.P0.Utilities.annotations.Inject;
import com.revature.P0.models.*;

import java.time.LocalDate;
import java.util.*;

public class MainMenu implements IMenu {
    @Inject
    private final UserModel user;
    private final UserService userService;
    private final ReviewService reviewService;
    private final GameService gameService;



    @Inject
    public MainMenu(UserModel user, UserService userService, ReviewService reviewService, GameService gameService) {
        this.user = user;
        this.userService = userService;
        this.reviewService = reviewService;
        this.gameService = gameService;
    }

    public List<List> cartList = new ArrayList<>();

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\n" + ("| Welcome to main menu  |" + user.getUsername()));

                System.out.println("[1] View all games");
                System.out.println("[2] View game reviews");
                System.out.println("[3] View cart");
                System.out.println("[x] Sign out");

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewGames();
                        break;
                    case "2":
                        viewGameReviews();
                        break;
                    case "3": viewCart();
                    break;

                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }



    private void viewGameReviews() {
        Scanner scan = new Scanner(System.in);
        List<Game> games = gameService.getAllGames();

        while (true) {
            System.out.println("\n+-------------------------------------------+");
            System.out.println("| Please select a Game to see reviews |");
            System.out.println("+-------------------------------------------+");
            if (games.size() == 0) {
                System.out.println("There are currently no games in store");
                break;
            }




            try{
                for (int i = 0; i < games.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + games.get(i).getGameName());
                }

                System.out.print("\nEnter: ");
                int input = scan.nextInt() - 1;

                if (input >= 0 && input < games.size()) {
                    Game gamesSelected = games.get(input);
                    List<Review> reviews = reviewService.getReviewByGame(gamesSelected.getGameUPC());

                    System.out.println("\n" + drawHorizontalLine("| Reviews for  |", gamesSelected.getGameName()));
                    System.out.println("| Reviews for " + gamesSelected.getGameName() + " |");
                    System.out.println(drawHorizontalLine("| Reviews for  |", gamesSelected.getGameName()));

                    if (!reviews.isEmpty()) {
                        exit:
                        {
                            while (true) {
                                int newLine = 0;
                                for (Review g : reviews) {
                                    UserModel userReview = userService.getUserById(g.getUser_id());
                                    System.out.println(g.getReview() + "\nRating: " + g.getRating() + "\nUser: " + userReview.getUsername());

                                    if (newLine < reviews.size() - 1) System.out.println();

                                    newLine++;
                                }

                                scan.nextLine();

                                System.out.println("\nDo you want to leave a review? (y/n)");
                                System.out.print("\nEnter: ");

                                switch (scan.nextLine()) {
                                    case "y":
                                        break;
                                    case "n":
                                        break exit;
                                    default:
                                        System.out.println("\nInvalid input!");
                                        break;
                                }
                            }
                        }
                    } else{
                        exit:
                        {
                            scan.nextLine();

                            System.out.println("No reviews ");
                            System.out.println("\nDo you want to leave a review? (y/n)");
                            System.out.print("\nEnter: ");
                            String option = scan.nextLine();

                            switch (scan.nextLine()) {
                                case "y":
                                    break;
                                case "n":
                                    break exit;
                                default:
                                    System.out.println("\nChoose either y or n");
                                    break;
                            }
                        }
                    }
                } else {
                    System.out.println("\nInvalid Game selection.");
                }

            }
            catch(NullPointerException error){System.out.println("Reviews is Empty");

                exit:
                {
                    scan.nextLine();

                    System.out.println("No reviews ");
                    System.out.println("\nDo you want to leave a review? (y/n)");
                    System.out.print("\nEnter: ");

                    switch (scan.nextLine()) {
                        case "y":

                            break;
                        case "n":
                            break exit;
                        default:
                            System.out.println("\nChoose either y or n");
                            break;
                    }
                }
                break;}

            catch(InputMismatchException e){
                System.out.println("Enter a number");
                break;
            }
        }

    }

    private void viewCart(){
        //Scanner scan = new Scanner(System.in);
        if(cartList.size() == 0){
            System.out.println("Your cart is currently empty");
        }
        else {
            for (int i = 0; i < cartList.size(); i++) {
                System.out.println(cartList.get(i));
            }
        }


    }


    private String drawHorizontalLine(String msg, String nameLength) {
        int len = msg.length() + nameLength.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (i == 0 || i == len - 1) sb.append("+");
            else sb.append("-");
        }

        return sb.toString();
    }

    private void viewGames() {

        List<Game> games = gameService.getAllGames();
        List<String> myList = new ArrayList<>();

        Exit:
        {
            while (true) {
                System.out.println("\n+----------------------------+");
                System.out.println("| Please select a Game to buy |");
                System.out.println("+------------------------------+");
                if (games.size() == 0) {
                    System.out.println("There are currently no games in store");
                    break;
                } else {
                    for (int i = 0; i < games.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + games.get(i).getGameName());
                    }

                    System.out.print("\nSelect the game you want to buy buy the corresponding number: ");

                    Scanner scan = new Scanner(System.in);


                    try{

                        int input = scan.nextInt()- 1;
                        if (input >= 0 && input < games.size()) {
                            Game gamesSelected = games.get(input);

                            Salir:
                            {
                                System.out.println(gamesSelected.getGameName() + "Has been selected");
                                System.out.println("confirm purchase (y/n)");
                                myList.add(gameService.addToCart(gamesSelected.getGameName(), gamesSelected.getPrice()));
                                Scanner choice = new Scanner(System.in);
                                String option = choice.nextLine();
                                while (true) {
                                    switch (option) {

                                        case "y":
                                            Date tommorow = new Date(122,6,3);

                                            System.out.println("userID: " + user.getUsername());
                                            userService.getDeeID(user);
                                            OrderModel order = new OrderModel(UUID.randomUUID().toString(), gamesSelected.getGameUPC(), 1, gamesSelected.getPrice(), user.getUserId(), tommorow);
                                            System.out.println("Game(s) purchased: " + gamesSelected.getGameName() + " Price: " + gamesSelected.getPrice());
                                            gameService.buyGame(order);
                                            break Salir;
                                        case "n":
                                            break Salir;

                                        default:System.out.println("enter either (y/n)");
                                        break;

                                    }

                                }

                            }



                        }

                        else {
                            System.out.println("\nInvalid Game selection.");
                        }

                    }
                    catch(InputMismatchException e){ System.out.println("Invalid Selection");}

                }


                System.out.println("would you like to purchase another game? y/n");
                Scanner choice = new Scanner(System.in);
                while (true) {
                    switch (choice.nextLine()) {
                        case "y":
                            break;
                        case "n"://transfer to database order table
                            break Exit;
                        default:
                            System.out.println("Enter either y or n");
                    }
                }

            }
        }

        cartList.add(myList);
    }



}

