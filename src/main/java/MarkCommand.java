public class MarkCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException {
        try {
            String numberStr = input.substring(5).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.list.get(number - 1);
            curr.markDone();

            System.out.println("OK, you've completed this: ");
            System.out.print(curr.toString().indent(8));

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'mark' requires a number after");
        } catch (NullPointerException e) {
            throw new UnknownInputException("you can't mark a task that doesn't exist");
        }
    }
}
