package tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate Date;
    private DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    private DateTimeFormatter saveDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Deadlines(String description, LocalDate Date){

        super(description);
        this.Date = Date;
        super.isDone = false;
        super.taskPriority = Priority.LOW;
    }

    public Deadlines(String description, LocalDate Date, boolean isDone){

        super(description);
        this.Date = Date;
        super.isDone = isDone;
        super.taskPriority = Priority.LOW;
    }

    public Deadlines(String description, LocalDate Date, boolean isDone, Priority taskPriority){

        super(description);
        this.Date = Date;
        super.isDone = isDone;
        super.taskPriority = taskPriority;
    }

    @Override
    public String getDescription() {
        return "[D]" + "[" + getStatusIcon() + "][" + super.getTaskPriorityToString() + "]" + super.getDescription() + " (by:" + Date.format(displayDateFormat) + ")";
    }

    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "D | " + taskStatus + " | " + super.getTaskPriorityToString() + " | " + super.getDescription() + " | " + Date.format(saveDateFormat) + "\r";
    }



}
