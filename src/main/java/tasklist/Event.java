package tasklist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDate Date;
    protected LocalTime startTime, endTime;
    private DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    private DateTimeFormatter displayTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
    private DateTimeFormatter saveDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter saveTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public Event(String description, LocalDate Date, LocalTime startTime, LocalTime endTime){
        super(description);
        this.Date = Date;
        this.startTime = startTime;
        this.endTime = endTime;
        super.isDone = false;
        super.taskPriority = Priority.LOW;
    }

    public Event(String description, LocalDate Date, LocalTime startTime, LocalTime endTime, boolean isDone){
        super(description);
        this.Date = Date;
        this.startTime = startTime;
        this.endTime = endTime;
        super.isDone = isDone;
        super.taskPriority = Priority.LOW;
    }

    public Event(String description, LocalDate Date, LocalTime startTime, LocalTime endTime, boolean isDone, Priority taskPriority){
        super(description);
        this.Date = Date;
        this.startTime = startTime;
        this.endTime = endTime;
        super.isDone = isDone;
        super.taskPriority = taskPriority;
    }

    @Override
    public String getDescription() {
        return "[E]" + "[" + getStatusIcon() + "][" + super.getTaskPriorityToString() + "]" + super.getDescription() + " (at:" + Date.format(displayDateFormat) + " " + startTime.format(displayTimeFormat) + " - " + endTime.format(displayTimeFormat) + ")";
    }
    @Override
    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "E | " + taskStatus + " | " + super.getTaskPriorityToString() + " | " + super.getDescription() + " | " + Date.format(saveDateFormat) + " " + startTime.format(saveTimeFormat) + " - " + endTime.format(saveTimeFormat) + "\r";
    }
}
