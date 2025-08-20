public class Deadline extends Task{

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: %s)", this.dueDate);
    }

}
