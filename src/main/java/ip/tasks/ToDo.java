package ip.tasks;

/**
 * Represents a task with only a description
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toDataString() {
        int isDoneInt;
        if (super.getIsDone()) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return String.format("T / %d / %s", isDoneInt, super.getDescription());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
