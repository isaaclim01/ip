public class AddDeadlineCommand implements Command {
    @Override
    public void execute(String input) {
        String[] splitInput = input.substring(9).split("/");
        if (splitInput[1].startsWith("by ")) {
            splitInput[1] = splitInput[1].substring(3);
        }
        Deadline addTask = new Deadline(splitInput[0].trim(), splitInput[1].trim());
        Squiddy.list[Squiddy.counter] = addTask;
        Squiddy.counter++;
        System.out.println("Let me write this down: \n" +
                addTask.toString().indent(8));
        System.out.println(String.format("You have %d tasks recorded", Squiddy.counter + 1));
    }
}
