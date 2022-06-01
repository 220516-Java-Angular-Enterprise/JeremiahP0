package com.revature.P0.UserInterface;

import com.revature.P0.Services.GameService;
import com.revature.P0.Utilities.annotations.Inject;
import com.revature.P0.models.Game;
import com.revature.P0.models.UserModel;
//import com.sun.tools.jdeprscan.scan.Scan;
//import com.sun.tools.jdeprscan.scan.Scan;

import java.util.*;

public class AdminMenu implements IMenu{


    @Inject
    private final UserModel user;
    private final GameService gameService;

    @Inject
    public AdminMenu(UserModel user, GameService gameService){
        this.user = user;
        this.gameService = gameService;
    }
    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\n" + drawHorizontalLine("| Welcome to admin menu  |", user.getUsername()));
                System.out.println("| Welcome to admin menu " + user.getUsername() + " |");
                System.out.println(drawHorizontalLine("| Welcome to admin menu  |", user.getUsername()));
                System.out.println("[1] Add a game to the Database");
                System.out.println("[2] Update an existing Game");
                System.out.println("[3] Delete a Game");
                System.out.println("[4] Search Game");
                System.out.println("[x] Sign out");

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1": createGame();
                        break;
                    case "2": updateGame();
                        break;
                    case "3": deleteGame();
                        break;
                    case "4": searchGame();
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

    private void createGame() {
        Scanner scan = new Scanner(System.in);
        Game game = new Game();

        exit:
        {
            while (true) {

                System.out.println("\n+------------------------+");
                System.out.println("| Initializing game creator... |");
                System.out.println("+------------------------+");

                game.setGameUPC(UUID.randomUUID().toString());

                System.out.print("Game Name: ");
                game.setGameName(scan.nextLine());


                System.out.print("\nCondition: ");
                game.setCondition(scan.nextLine());

                System.out.println("\nDescription");
                game.setDescription(scan.nextLine());


                System.out.print("\nSet maturity rating: ");
                game.setRating(scan.nextLine());


                System.out.print("\nSet the game genre: ");
                game.setGameType(scan.nextLine());


                System.out.println("\nSet the game's console type");
                game.setConsole(scan.nextLine());

                System.out.print("\nSet game Price ##.##: ");
                //eat this line
                Scanner input = new Scanner(System.in);
                double price = input.nextDouble();
                game.setPrice(price);



                System.out.println("\nWould you like to add the game to the database? (y/n)");
                System.out.println("\n" + game);

                game.setGameScore(0.0);
                Scanner choice = new Scanner(System.in);


                switch (choice.nextLine()) {
                    case "y":
                        gameService.register(game);
                        break exit;
                    case "n":
                        break;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
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

    private void updateGame(){
        Scanner scan = new Scanner(System.in);

        while(true) {
            List<Game> gameList = gameService.getAllGames();
            for(int i = 0; i < gameList.size();i++){
                System.out.println("[" + (i+1) + "]" + gameList.get(i).getGameName());

            }
            System.out.println("Enter the corresponding number that you want to select\n");
           int input = scan.nextInt() - 1;

           if(input >= 0 && input < gameList.size()){
               Game game = gameList.get(input);

               System.out.println(game);

               System.out.print("New name: ");
               Scanner choice = new Scanner(System.in);
               String name = choice.nextLine();
               game.setGameName(name);
               //scan.nextLine();
               //game.setGameName(name);

               if(gameService.updateName(game.getGameName(), game.getGameUPC())){
                   //gameService.updateName(game.getGameName(),game.getGameUPC());
                   System.out.println("Game Updated Successfully");
                   break;
               }

           }
           else System.out.println("choose a corresponding number with the game");

        }

    }

    private void deleteGame(){
        Scanner scan = new Scanner(System.in);
        while(true){
           List<Game> gameList = gameService.getAllGames();
           for(int i = 0; i < gameList.size();i++){
               System.out.println("[" + (i + 1) + "]" + gameList.get(i).getGameName());
           }
           System.out.println("Enter the corresponding number to delete\n");
           int input = scan.nextInt() - 1;
           if(input > 0 && input < gameList.size()){
               Game gameChoice = gameList.get(input);
               System.out.println(gameChoice + "  selected, would you like to delete this game?");
                if(gameService.deleteGame(gameChoice.getGameUPC())){
                    System.out.println("Game has been deleted from the database");
                    break;
                }
                else System.out.println("Invalid game choice");

           }
           else System.out.println("Choose a corresponding number with the game");
        }



    }

    private void searchGame(){
        //Not finished, must implement a method to purchase games
        Scanner input = new Scanner(System.in);
        while (true) {
            List<Game> gameList = gameService.getAllGames();
            for(int i = 0; i < gameList.size(); i++){
                System.out.println("[" + (i+1) + "]" + gameList.get(i).getGameName());
            }
            System.out.println("Enter the corresponding number, enter a negative number to quit");
            int choice = input.nextInt() - 1;
            if(choice >= 0 && choice < gameList.size()){
                Game gameChoice = gameList.get(choice);
                System.out.println(gameChoice.getGameName() + " has been selected would you like to purchase this game? y for yes any key for no");
                Scanner option1 = new Scanner(System.in);
                while(true) {
                    String which = option1.nextLine().toLowerCase();
                    if (which.equals("y")) {
                        if (gameService.isGamePurchased(gameChoice.getGameUPC())) {
                            System.out.println(gameChoice + "\n Has been added to cart");
                            break;
                        }
                    }
                    else if(which.equals("n")) break;
                    else System.out.println("invalid input");

                }

            }
            else if(choice < 0)break;
            else{System.out.println("choice was Invalid");}

        }
    }

    }

