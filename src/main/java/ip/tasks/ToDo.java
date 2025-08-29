package ip.tasks;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        int isDoneInt;
        if (isDone) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return String.format("T / %d / %s", isDoneInt, description);
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
