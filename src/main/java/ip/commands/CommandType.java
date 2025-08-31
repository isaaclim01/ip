package ip.commands;

public enum CommandType {
    LIST("list", new ListCommand()),
    MARK("mark", new MarkCommand()),
    UNMARK("unmark", new UnmarkCommand()),
    REPEAT("repeat", new RepeatCommand()),
    TODO("todo", new AddToDoCommand()),
    DEADLINE("deadline", new AddDeadlineCommand()),
    EVENT("event", new AddEventCommand()),
    TEST("test", new TestCommand()),
    DELETE("delete", new DeleteCommand()),
    EXIT("bye", new ExitCommand());

    private final String keyword;
    private final Command command;

    CommandType(String keyword, Command command) {
        this.keyword = keyword;
        this.command = command;
    }

    public static CommandType findCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return REPEAT;
        }

        for (CommandType type : values()) {
            if (input.equals(type.keyword)) {
                return type;
            }
        }

        for (CommandType type : values()) {
            if (input.startsWith(type.keyword + " ")) {
                return type;
            }
        }

        return REPEAT;
    }

    public Command getCommand() {
        return this.command;
    }
}
