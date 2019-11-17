//level 7.more oop
/**
 *  Task command for storing Task List
 */

package task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public void taskUndo() {
        this.isDone = false;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "√" : "X") + "] " + description;

    }


}

