import java.util.HashMap;
import java.util.Map;

public class CommandMapper {
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandMapper() {
        this.commandMap.put("list", new ListCommand());
        this.commandMap.put("mark", new MarkCommand());
        this.commandMap.put("unmark", new UnmarkCommand());
        this.commandMap.put("add", new AddTaskCommand());
    }

    public Command getCommand(String input) {
        if (input.equals("list")) {
            return commandMap.get("list");
        } else if (input.startsWith("mark ")) {
            return commandMap.get("mark");
        } else if (input.startsWith("unmark ")) {
            return commandMap.get("unmark");
        } else {
            return commandMap.get("add");
        }
    }
}
