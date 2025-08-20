public class ListCommand implements Command {
    @Override
    public void execute(String input) {
        System.out.println("Let's see what you've got: ");

        for (int i = 0; i < Squiddy.counter; i++) {
            Task curr = Squiddy.list[i];
            System.out.printf("%d.[%s] %s \n", i + 1,
                    curr.getStatusIcon(),
                    curr.getDescription());
        }
    }
}
