import java.io.IOException;
import java.util.*;

// Abstract base class for Hangman game
public abstract class HangmanGame {
    private String secretWord;
    protected Set<Character> guessedLetters = new HashSet<>();
    private int remainingGuesses;
    private boolean isGuessed = false;
    protected Character guessedLetter;
    protected final WordProvider wordProvider;
    protected final Scanner scanner;

    // Initialise attributes
    protected HangmanGame(WordProvider wordProvider, int initialGuesses, Scanner scanner) {
        this.scanner = scanner;
        this.wordProvider = wordProvider;
        this.remainingGuesses = initialGuesses;
    }


    public abstract void startGame() throws IOException;

    protected void setSecretWord(String word){

        this.secretWord = word.toLowerCase();
    }


    protected String getSecretWord(){

        return this.secretWord;
    }

    protected boolean isWordGuessed(){
        return this.isGuessed;
    }

    protected int getRemainingGuesses(){

        return this.remainingGuesses;
    }

    // Handles player's guess input
    public void makeGuess() {
        boolean correct_input = false;
        System.out.println("Enter your guess: ");
        while (!correct_input) {
            String input = this.scanner.nextLine().toLowerCase(Locale.ROOT).trim(); // Remove spaces

            if (input.isEmpty()) { // Check for empty inputs
                System.out.println("Please enter a valid guess: ");
                continue;
            }
            if (input.length() != 1) {
                if (!processGuess(input)){
                    System.out.println("Please enter a single letter: "); // Check for multiple characters
                } else {
                    System.out.println("Please add every letter one by one!"); // Check if answer prematurely entered
                }
            } else {
                this.guessedLetter = input.charAt(0);  // Read user input
                if (!Character.isAlphabetic(this.guessedLetter)) {
                    System.out.println("Please enter a valid letter: ");
                } else {
                    if (processGuess(this.guessedLetter)){
                        correct_input = true;
                    }
                }
            }
        }
    }

    // Process guesses with multiple characters
    public boolean processGuess(String guess) {
        return Objects.equals(guess, secretWord);
    }

    // Process correctly formatted guesses
    public boolean processGuess(Character guess) {
        if (this.guessedLetters.contains(guess)) {
            System.out.println("You already guessed this!");
        } else if (this.secretWord.toLowerCase().indexOf(guess) == -1) {
            System.out.println("Your guess is incorrect!");
            this.remainingGuesses--;
        } else {
            System.out.println("Your guess is correct!");
        }
        this.guessedLetters.add(guess);
        return true;
    }

    // Show current game progress
    public void displayProgress() {
        // Show correctly guessed characters
        StringBuilder progress = new StringBuilder();
        this.isGuessed = true;
        for (char c : this.secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                progress.append(Character.toUpperCase(c)).append(" ");
            } else {
                progress.append("_ ");
                this.isGuessed = false;
            }
        }
        System.out.println(progress + "\nAttempts left: " + this.remainingGuesses  + "\n"); // Show remaining guesses
    }
}