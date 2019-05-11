package com.company.phrase_hunter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userResponse;
        int wins = 0;
        int gamesPlayed = 0;
        Scanner scanner = new Scanner(System.in);

        Game game = new Game("Hello", 2);
        System.out.println(startApp());
        System.out.println(game.displayGameBoard());

        while (!scanner.hasNext("[a-zA-Z]?")) {
            System.out.println("Must use a single alphabet letter.");
            scanner.next();
        }
        userResponse = scanner.next();
        while (!userResponse.toLowerCase().equals("quit")) {
            game.takeTurn(userResponse.trim().charAt(0));
            clearScreen();
            System.out.println(game.displayGameBoard());

            while (!userResponse.toLowerCase().equals("quit") && !scanner.hasNext("[a-zA-Z]?")) {
                System.out.println("Must use a single alphabet letter.");
                scanner.next();
            }
            userResponse = scanner.next();

            if (game.isGameOver()) {
                gamesPlayed++;
                if (game.isGameWon()) wins++;

                if (userResponse.equals("n")) {
                    break;
                }
                userResponse = "blah";
                game = new Game("Test", 2);
                System.out.println(game.displayGameBoard());

                while (!userResponse.toLowerCase().equals("quit") && !scanner.hasNext("[a-zA-Z]?")) {
                    System.out.println("Must use a single alphabet letter.");
                    scanner.next();
                }
                userResponse = scanner.next();
            }
        }

        System.out.println("\nQuitting app...Won " + wins + " out of " + gamesPlayed + " games.\n");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String startApp() {
        String prompt;

        prompt = "---PHRASE HUNTER GAME (AKA Hangman)---\n\n";
        prompt += "Press 'q' or 'quit' at any time to quit.\n\n";

        return prompt;
    }
}