package com.company.phrase_hunter;

import java.util.ArrayList;
import java.util.List;

public class Phrase {
    private List<Letter> letters;
    private boolean entirePhraseHasBeenGuessed;

    Phrase(String startingPhrase) {
        this.letters = new ArrayList<>(startingPhrase.length());

        for (int i = 0; i < startingPhrase.length(); i++) {
            this.letters.add(new Letter(startingPhrase.charAt(i)));
        }
    }

    public String toString() {
        StringBuilder shownString = new StringBuilder();

        for (Letter letter : this.letters) {
            shownString.append(letter.toString());
        }

        return shownString.toString().trim();
    }

    boolean searchForChar(char guess) {
        boolean allCharsBeenGuessed = true;
        boolean foundAMatch = false;

        for (Letter letter : this.letters) {
            if (!letter.isAlreadyGuessed()) {
                if (letter.checkGuess(guess)) {
                    foundAMatch = true;
                } else {
                    allCharsBeenGuessed = false;
                }
            }
        }

        this.entirePhraseHasBeenGuessed = allCharsBeenGuessed;

        return foundAMatch;
    }

    boolean isEntirePhraseHasBeenGuessed() {
        return entirePhraseHasBeenGuessed;
    }

    int size(){
        return this.letters.size();
    }
}
