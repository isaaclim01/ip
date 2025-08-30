package ip.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

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

    @Override
    public String toDataString() {
        int isDoneInt;
        if (isDone) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return String.format("E / %d / %s / %s / %s", isDoneInt, description, startDate, endDate);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);
        return String.format("[E]" + super.toString() + " (from: %s, to: %s)", startDateString, endDateString);
    }
}
