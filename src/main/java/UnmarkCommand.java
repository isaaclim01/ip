public class UnmarkCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException {
        try {
            String numberStr = input.substring(7).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.list.get(number - 1);
            curr.unmarkDone();

            System.out.println("I hope this empty box make you feel bad for procrastinating: ");
            System.out.print(curr.toString().indent(8));

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'unmark' requires a number after");
        } catch (NullPointerException e) {
            throw new UnknownInputException("you can't unmark a task that doesn't exist");
        }
    }
}
