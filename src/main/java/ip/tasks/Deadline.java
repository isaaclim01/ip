package ip.tasks;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toDataString() {
        int isDoneInt;
        if (isDone) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return String.format("D / %d / %s / %s", isDoneInt, description, dueDate);
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: %s)", this.dueDate);
    }

}
