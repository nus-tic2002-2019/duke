package duke.task;

import java.time.LocalDate;

/** Represents a generic task. */
public class Task {
    protected String desc;
    /** Represents the status of the task. */
    protected boolean isDone;
    protected String type;
    protected int doAfter = -1;
    protected int doBefore =-1;

    /**
     * @param desc is the description of the task.
     * @param type is the type of the task (deadline, event or todo).
     */
    public Task(String desc, String type) {
        this.desc = desc;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Sets the task status based on the param.
     *
     * @param status used to overwrite the task status.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Sets the doAfter task based on the param.
     *
     * @param index task index.
     */
    public void setDoAfter(int index) {
        this.doAfter = index;
    }

    /**
     * Sets the doBefore task based on the param.
     *
     * @param index task index.
     */
    public void setDoBefore(int index) {
        this.doBefore = index;
    }

    /**
     * Returns the task status (isDone) in the form of an icon.
     *
     * @return a tick or cross icon.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /** @return task description. */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns the task status.
     *
     * @return true if task is completed.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /** @return the task type, task status (as a icon) and task description. */
    public String getStatusIconAndDesc() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.desc;
    }

    /** @return the task type (deadline, event or todo. */
    public String getType() {
        return this.type;
    }

    /**
     * Used mainly for subclasses like event and deadline.
     *
     * @return null by default.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * @return doAfter task index.
     */
    public int getDoAfter() {
        return this.doAfter;
    }

    /**
     * @return doBefore task index.
     */
    public int getDoBefore() {
        return this.doBefore;
    }

    /** Change the task status to complete. */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return true if there is no Do After task.
     */
    public boolean isDoAfterEmpty() {
        return this.doAfter == -1;
    }

    /**
     * @return true if there is no Do Before task.
     */
    public boolean isDoBeforeEmpty() {
        return this.doBefore == -1;
    }

}