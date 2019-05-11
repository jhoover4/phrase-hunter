package com.company.phrase_hunter;

import java.util.HashSet;
import java.util.Set;

class Game {
    private Phrase phrase;
    private int totalLives;
    private int livesRemaining;
    private boolean correctGuess;
    private boolean firstTurn;
    private Set<Character> usedCharacters;
    private boolean gameOver;
    private boolean gameWon;
    private boolean repeatedGuess;

    boolean isGameOver() {
        return gameOver;
    }

    Game(String phraseString, float totalLivesMultiplier) {
        this.phrase = new Phrase(phraseString);
        this.totalLives = this.initializeTotalLives(totalLivesMultiplier);
        this.livesRemaining = totalLives;

        this.gameWon = false;
        this.firstTurn = true;
        this.gameOver = false;
        this.usedCharacters = new HashSet<>(26);
    }

    boolean isGameWon() {
        return gameWon;
    }

    private int initializeTotalLives(float totalLivesMultiplier) {
        return (int) Math.floor((float) this.phrase.size() * totalLivesMultiplier);
    }

    void takeTurn(char guess) {
        guess = Character.toLowerCase(guess);

        if (this.alreadyGuessedLetter(guess)) {
            return;
        }

        if (this.phrase.isEntirePhraseHasBeenGuessed() || this.livesRemaining <= 0 || this.livesRemaining < this.phrase.size()) {
            this.gameOver = true;
            this.decideGameWon();
        } else {
            this.livesRemaining--;
            this.correctGuess = this.phrase.searchForChar(guess);
            this.usedCharacters.add(guess);
        }
    }

    String displayGameBoard() {
        String prompt = "";

        if (this.gameOver) {
            return this.endGamePrompt();
        }

        if (this.repeatedGuess) {
            return "You've already guessed that letter.";
        }

        if (!this.firstTurn) {
            prompt += this.displayGuessResult() + "\n";
            prompt += "Guessed letters: " + this.usedCharacters.toString() + "\n";
        }
        this.firstTurn = false;

        prompt += this.livesRemaining() + "\n\n" + phrase.toString() + "\n\nGuess a letter: ";

        return prompt;
    }

    private boolean alreadyGuessedLetter(char guess) {
        this.repeatedGuess = this.usedCharacters.contains(guess);
        return this.repeatedGuess;
    }

    private String displayGuessResult() {
        if (this.correctGuess) {
            return "\nGuess was correct!\n";
        } else {
            return "\nGuess was not correct.\n";
        }
    }

    private String livesRemaining() {
        return "You have " + this.livesRemaining + " out of " + this.totalLives + " lives remaining.";
    }

    private void decideGameWon() {
        this.gameWon = this.livesRemaining > 0 && this.livesRemaining < this.phrase.size() - 1;
    }

    private String endGamePrompt() {
        String prompt;

        if (this.gameWon) {
            prompt = "You've won with " + this.livesRemaining + "left! ";
        } else {
            prompt = "Sorry, there not enough tries left for you to guess the word. ";
        }

        prompt += "Want to play again?\n\n(Y)/n> ";
        return prompt;
    }
}
