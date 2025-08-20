public class RepeatCommand implements Command {
    @Override
    public void execute(String input) {
        System.out.println(String.format("What would you like me to do with '%s'?", input));
    }
}
