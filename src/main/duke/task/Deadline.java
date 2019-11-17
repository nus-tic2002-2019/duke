package main.duke.task;

import main.duke.exception.DukeMissingDescException;

public class Deadline extends Task{
    private String deadline;

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline) throws DukeMissingDescException {
        super(description);
        setDeadline(deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDeadline());
    }
}
