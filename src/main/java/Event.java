public class Event extends Task{
    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (from: %s to: %s)", this.startDate, this.endDate);
    }
}
