package taskclasses;

import date.time.management.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task {
    private String description;
    protected String type;

    //For Deadline task;
    protected DateTime Deadline_timing;

    //For Event task;
    protected DateTime Starting;
    protected DateTime Ending;

    protected boolean isDone = false;

    /**
     * Constructs a Task class without any initialization.
     */
    public Task(){}

    /**
     * Constructs and initialize a Task class.
     * @param description Task description.
     * @param type Task Type: Todo, Event and Deadline.
     */
    public Task(String description, String type){
        this.description = description;
        this.type = type;
        isDone = false;
    }

    /**
     * To get the Icon base on the Task status.
     * @return return cross or tick.
     */
    public String getStatusIcon(){
        return (isDone ? String.valueOf('T') : "F");
    }

    /**
     * To get the description of the Task.
     * @return return a String, description.
     */
    public String getDescription(){
        return description;
    }

    /**
     * To get a Deadline Task target deadline datetime in default LocalDateTime to String format.
     * @return return String.
     */
    public String getDateTime(){
        //Build formatter
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime Task_DateTime = DateTime.getDateTime_Input();

        return Task_DateTime.format(formatter);
    }

    /**
     * To get Deadline time in DateTime type;
     * @return return DateTime Deadline_timing;
     */
    public DateTime getDeadline_timing(){
        return this.Deadline_timing;
    }

    /**
     * To get Event starting time in DateTime type;
     * @return return DateTime Starting;
     */
    public DateTime getStarting_Time(){
        return this.Starting;
    }

    /**
     * To get Event ending time in DateTime type;
     * @return return DateTime Ending;
     */
    public DateTime getEnding_Time(){
        return this.Ending;
    }

    /**
     * To get Deadline time in String type which transfer from default LocalDateTime to String method;
     * @return return String Deadline_timing;
     */
    public String getDeadline_DateTime_String(){
        return Deadline_timing.getDateTime_Input().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * To get Event starting time in String type which transfer from default LocalDateTime to String method;
     * @return return String starting;
     */
    public String getStarting_DateTime_String(){
        return Starting.getDateTime_Input().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * To get Event ending time in String type which transfer from default LocalDateTime to String method;
     * @return return String ending;
     */
    public String getEnding_DateTime_String(){
        return Ending.getDateTime_Input().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * To get the Task type T, E, or D;
     * @return return String type;
     */
    public String getType(){
        return type;
    }

    /**
     * To get the status of the Task: true or false.
     * @return return boolean, true or false;
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * To update the task's status;
     * @param current the boolean input, current is the new status which needs to be update to isDone in Task;
     */
    public void UpdateIsDone(boolean current){
        this.isDone = current;
    }

    private void descriptionUpdater(String Description){
        description = Description;
    }

    public static void TaskDescriptionUpdate(Task task){
        System.out.println("      Please key-in the new description to replace the original description.");

        Scanner s = new Scanner(System.in);
        String description = s.nextLine();

        task.descriptionUpdater(description);
    }
}
