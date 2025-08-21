public class AddEventCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException {

        if (input.length() == 5) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        if (!input.contains("/from") || !input.contains("/to")) {
            throw new UnknownInputException("Your Event has to have a duration inputted with '/from' and '/to'");
        }

        String[] splitInput = input.substring(6).split("/");

        if (splitInput[0].trim().isEmpty()) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        if (splitInput[1].startsWith("from ")) {
            splitInput[1] = splitInput[1].substring(5);
            if (splitInput[1].isEmpty()) {
                throw new UnknownInputException("Your Event has to have a start time inputted with '/from'");
            }
        } else {
            throw new UnknownInputException("Your Event has to have a start time inputted with '/from'");
        }

        if (splitInput[2].startsWith("to ")) {
            splitInput[2] = splitInput[2].substring(3);
        } else {
            throw new UnknownInputException("Your Event has to have a end time inputted with '/to'");
        }

        Event addTask = new Event(splitInput[0].trim(), splitInput[1].trim(), splitInput[2].trim());
        Squiddy.list.add(addTask);
        System.out.print("Let me write this down: \n" +
                addTask.toString().indent(8));
        System.out.println(String.format("You have %d tasks recorded", Squiddy.list.size()));
    }
}
