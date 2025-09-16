package ip.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {

    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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
        return String.format("D / %d / %s / %s", isDoneInt, super.getDescription(), dueDate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
        String dateString = dueDate.format(formatter);

        return String.format("[D]" + super.toString() + " (by: %s)", dateString);
    }

}
