import java.io.IOException;
import java.util.Scanner;

// Starts medium Hangman game with 8 guesses
public class MediumHangman extends HangmanGame {
    public MediumHangman(WordProvider wordProvider, Scanner scanner) {
        super(wordProvider, 8, scanner);
    }

    @Override
    public void startGame() throws IOException {
        setSecretWord(wordProvider.getRandomWord("medium"));
        System.out.println("Starting MEDIUM mode (8 guesses)");
    }

}



