package main.duke.task;

import main.duke.exception.DukeMissingDescException;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     *
     * @return DateTime representation of this Deadline object
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Sets or updates this object's deadline.
     * @param deadline
     */
    public void setDeadline(String deadline){
        this.deadline = Parser.parseDateTimeStr(deadline);
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
