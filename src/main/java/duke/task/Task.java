package duke.task;

import java.time.LocalDate;

public class Task {
    protected String desc;
    protected boolean isDone;
    protected String type;

    public Task(String desc, String type) {
        this.desc = desc;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIconAndDesc() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.desc;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public String getType() {
        return this.type;
    }

    public LocalDate getDate() {
        return null;
    }
}