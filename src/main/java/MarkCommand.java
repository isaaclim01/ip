public class MarkCommand implements Command {
    @Override
    public void execute(String input) {
        try {
            String numberStr = input.substring(5).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.list[number - 1];
            curr.markDone();

            System.out.println("OK, you've completed this: ");
            System.out.print(curr.toString().indent(8));

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Don't start your task with the word 'mark'. Please.");
        }
    }
}
