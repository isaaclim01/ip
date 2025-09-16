package ip.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start date and end date
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
        return String.format("E / %d / %s / %s / %s", isDoneInt, super.getDescription(), startDate, endDate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);
        return String.format("[E]" + super.toString() + " (from: %s, to: %s)", startDateString, endDateString);
    }
}
