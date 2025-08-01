import java.io.IOException;
import java.util.Scanner;

// Main class to run Hangman game
public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String difficulty;

            // Get valid difficulty input
            while (true) {
                System.out.println("Choose difficulty (easy/medium/hard): ");
                difficulty = scanner.nextLine().trim().toLowerCase();
                if (difficulty.matches("easy|medium|hard")) {
                    break;
                }
                System.out.println("Invalid difficulty! Please write either easy, medium or hard.");
            }

            // Start game
            HangmanGame game = World.createGame(difficulty, scanner);
            game.startGame();

            // Game loop
            while (!game.isWordGuessed() && game.getRemainingGuesses() > 0) {
                game.makeGuess();
                game.displayProgress();
            }

            // Win condition
            if (game.isWordGuessed()) {
                System.out.println("You won! The word was: " + game.getSecretWord().toUpperCase());
            } else {
                System.out.println("Game over! The word was: " + game.getSecretWord().toUpperCase());
            }

            // Check for file error or game errors
        } catch (IOException e) {
            System.err.println("Error loading words: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Game error: " + e.getMessage());
        }
    }
}

