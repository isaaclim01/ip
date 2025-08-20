public class AddTaskCommand implements Command {
    @Override
    public void execute(String input) {
        Squiddy.list[Squiddy.counter] = new Task(input);
        Squiddy.counter++;
        System.out.println("Let me write this down: " + input);
    }
}
