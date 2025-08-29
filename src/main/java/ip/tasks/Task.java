package ip.tasks;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toDataString() {
        int isDoneInt;
        if (isDone) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return String.format("* / %d / %s", isDoneInt, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
