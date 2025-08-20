public class ListCommand implements Command {
    @Override
    public void execute(String input) {
        System.out.println("Let's see what you've got: ");

        for (int i = 0; i < Squiddy.counter; i++) {
            Task curr = Squiddy.list[i];
            String taskString = String.format("%d. %s \n", i + 1, curr.toString()).indent(8);
            System.out.printf(taskString);
        }
    }
}
