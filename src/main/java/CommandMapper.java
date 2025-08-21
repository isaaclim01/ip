public class CommandMapper {

    public Command getCommand(String input) {
        return CommandType.findCommand(input).getCommand();
    }
}
