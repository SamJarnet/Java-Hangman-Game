import java.io.IOException;
import java.util.Scanner;

// Factory class to create HangmanGame instance based on chosen difficulty
public class World {
    public static HangmanGame createGame(String difficulty, Scanner scanner) throws IOException {
        WordProvider provider = new DefaultWordProvider();
        return switch (difficulty.toLowerCase()) {
            case "easy" -> new EasyHangman(provider, scanner);
            case "medium" -> new MediumHangman(provider, scanner);
            case "hard" -> new HardHangman(provider, scanner);
            default -> throw new IllegalArgumentException("Invalid difficulty! Please write either easy, medium or hard.");
        };
    }
}

