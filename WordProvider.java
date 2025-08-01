import java.io.IOException;


// Interface for word providers
public interface WordProvider{
    String getRandomWord(String difficulty) throws IOException;
}
