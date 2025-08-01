import java.io.*;
import java.util.*;

// Provides words from word file
public class DefaultWordProvider implements WordProvider {
    private final Map<String, List<String>> wordDifficulty = new HashMap<>();

    // Loads the word based on difficulty
    public DefaultWordProvider() throws IOException {
        wordDifficulty.put("easy", new ArrayList<>());
        wordDifficulty.put("medium", new ArrayList<>());
        wordDifficulty.put("hard", new ArrayList<>());

        try (BufferedReader bf = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split(" "); // Split at space between word and difficulty
                if (split.length != 2) {
                    throw new IOException("Invalid line in words.txt: " + line); // Check for invalid lines
                }
                String difficulty = split[1].toLowerCase();
                wordDifficulty.get(difficulty).add(split[0]);
            }
        }

        // Validate that all difficulties have words
        for (String d : Arrays.asList("easy", "medium", "hard")) {
            if (wordDifficulty.get(d).isEmpty()) {
                throw new IOException("No words found for difficulty: " + d);
            }
        }
    }

    // Return random word for the given difficulty
    @Override
    public String getRandomWord(String difficulty) {
        List<String> words = wordDifficulty.get(difficulty.toLowerCase());
        if (words == null || words.isEmpty()) {
            throw new IllegalStateException("No words for difficulty: " + difficulty);
        }
        return words.get(new Random().nextInt(words.size())).toLowerCase();
    }
}