public class TestCommand implements Command {
    @Override
    public void execute(String input) {
        Squiddy.TEST_MODE = !Squiddy.TEST_MODE;
        if (Squiddy.TEST_MODE) {
            System.out.println("Switched to test mode and removed the horizontal lines");
        } else {
            System.out.println("Turned off test mode");
        }
    }
}
