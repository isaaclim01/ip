import java.util.HashMap;
import java.util.Map;

public class CommandMapper {
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandMapper() {
        this.commandMap.put("list", new ListCommand());
        this.commandMap.put("mark", new MarkCommand());
        this.commandMap.put("unmark", new UnmarkCommand());
        this.commandMap.put("repeat", new RepeatCommand());
        this.commandMap.put("todo", new AddToDoCommand());
        this.commandMap.put("deadline", new AddDeadlineCommand());
        this.commandMap.put("event", new AddEventCommand());
        this.commandMap.put("test", new TestCommand());
        this.commandMap.put("delete", new DeleteCommand());
    }

    public Command getCommand(String input) {
        if (input.equals("list")) {
            return commandMap.get("list");
        } else if (input.startsWith("mark ") || input.equals("mark")) {
            return commandMap.get("mark");
        } else if (input.startsWith("unmark ") || input.equals("unmark")) {
            return commandMap.get("unmark");
        } else if (input.startsWith("todo ") || input.equals("todo")) {
            return commandMap.get("todo");
        } else if (input.startsWith("deadline ") || input.equals("deadline")) {
            return commandMap.get("deadline");
        } else if (input.startsWith("event ") || input.equals("event")) {
            return commandMap.get("event");
        } else if (input.equals("test")) {
            return commandMap.get("test");
        } else if (input.startsWith("delete ") || input.equals("delete")) {
            return commandMap.get("delete");
        } else {
            return commandMap.get("repeat");
        }
    }
}
