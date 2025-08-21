public class AddToDoCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException {
        if (input.length() == 4 || input.substring(5).trim().isEmpty()) {
            throw new UnknownInputException("Your ToDo has to have a description!");
        }
        ToDo addTask = new ToDo(input.substring(5).trim());
        Squiddy.list[Squiddy.counter] = addTask;
        Squiddy.counter++;
        System.out.print("Let me write this down: \n" +
                addTask.toString().indent(8));
        System.out.println(String.format("You have %d tasks recorded", Squiddy.counter + 1));
    }
}
