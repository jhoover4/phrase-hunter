package com.company.phrase_hunter;

class Letter {
    private char letter;
    private boolean alreadyGuessed;

    Letter(char letter) {
        this.letter = Character.toLowerCase(letter);
        alreadyGuessed = false;
    }

    boolean isAlreadyGuessed() {
        return alreadyGuessed;
    }

    boolean checkGuess(char guessedCharacter) {
        this.alreadyGuessed = (guessedCharacter == this.letter);
        return this.alreadyGuessed;
    }

    public String toString() {
        if (this.alreadyGuessed) {
            return this.letter + " ";
        } else {
            return "_ ";
        }
    }
}
