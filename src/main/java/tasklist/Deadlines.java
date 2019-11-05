package tasklist;

import tasklist.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected String by;
    protected LocalDate Date;

    public Deadlines(String description, LocalDate Date){

        super(description);
        this.Date = Date;
    }

    public Deadlines(String description, LocalDate Date, boolean isDone){

        super(description);
        this.Date = Date;
        super.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return "[D]" + "[" + getStatusIcon() + "]" + super.getDescription() + " (by:" + Date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String deadlineDescription(){
        return super.getDescription();
    }

    public String getBy(){
        return by;
    }

    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "D | " + taskStatus + " | " + super.getDescription() + " | " + Date + "\r";
    }



}
