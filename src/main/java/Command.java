public interface Command {
    void execute(String input) throws UnknownInputException;
}
