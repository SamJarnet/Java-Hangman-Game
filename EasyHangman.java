import java.io.IOException;
import java.util.Scanner;

// Starts easy Hangman game with 10 guesses
public class EasyHangman extends HangmanGame {
    public EasyHangman(WordProvider wordProvider, Scanner scanner) {
        super(wordProvider, 10, scanner);
    }

    @Override
    public void startGame() throws IOException {
        setSecretWord(wordProvider.getRandomWord("easy"));
        System.out.println("Starting EASY mode (10 guesses)");
    }

}


