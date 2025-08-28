import java.io.FileNotFoundException;

public interface Command {
    void execute(String input) throws UnknownInputException, FileNotFoundException, FileCorruptedException;
}
