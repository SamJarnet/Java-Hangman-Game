import java.io.IOException;
import java.util.Scanner;


// Starts hard Hangman game with 6 guesses
public class HardHangman extends HangmanGame {
    public HardHangman(WordProvider wordProvider, Scanner scanner) {
        super(wordProvider, 6, scanner);
    }

    @Override
    public void startGame() throws IOException {
        setSecretWord(wordProvider.getRandomWord("hard"));
        System.out.println("Starting HARD mode (6 guesses)");
    }


}
