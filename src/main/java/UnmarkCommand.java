public class UnmarkCommand implements Command {
    @Override
    public void execute(String input) {
        try {
            String numberStr = input.substring(7).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.list[number - 1];
            curr.unmarkDone();

            System.out.println("I hope this empty box make you feel bad for procrastinating: ");
            System.out.println(curr.toString().indent(8));

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Who uses unmark regularly? Use a more common word like 'erase'.");
        }
    }
}
