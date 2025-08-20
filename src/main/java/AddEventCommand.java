public class AddEventCommand implements Command {
    @Override
    public void execute(String input) {
        String[] splitInput = input.substring(6).split("/");
        if (splitInput[1].startsWith("from ")) {
            splitInput[1] = splitInput[1].substring(5);
        }
        if (splitInput[2].startsWith("to ")) {
            splitInput[2] = splitInput[2].substring(3);
        }
        Event addTask = new Event(splitInput[0].trim(), splitInput[1].trim(), splitInput[2].trim());
        Squiddy.list[Squiddy.counter] = addTask;
        Squiddy.counter++;
        System.out.println("Let me write this down: \n" +
                addTask.toString().indent(8));
        System.out.println(String.format("You have %d tasks recorded", Squiddy.counter + 1));
    }
}
