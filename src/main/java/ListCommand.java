public class ListCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException {

        if (!input.equals("list")) {
            throw new UnknownInputException("Just enter 'list' by itself");
        }

        if (Squiddy.list.isEmpty()) {
            throw new UnknownInputException("your list is empty");
        }

        System.out.println("Let's see what you've got: ");

        int max = Squiddy.list.size();

        for (int i = 0; i < max; i++) {
            Task curr = Squiddy.list.get(i);
            String taskString = String.format("%d. %s \n", i + 1, curr.toString()).indent(8);
            System.out.printf(taskString);
        }
    }
}
