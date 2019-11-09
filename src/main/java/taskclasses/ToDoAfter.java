package taskclasses;

import java.time.LocalDate;

public class ToDoAfter {
    //For ToDoAfter;
    protected String ToDoAfter_Type;  //Either date or task;
    protected Task ToDoAfterTask, task;
    protected LocalDate date;

    /**
     * To construct a task typ ToDoAfter task;
     * @param ToDoAfter_Type The type of ToDoAfter task, "task" type;
     * @param task The task condition;
     * @param ToDoAfterTask The user will do their task after "task" done;
     */
    public ToDoAfter(String ToDoAfter_Type, Task task, Task ToDoAfterTask){
        this.task = task;
        this.ToDoAfter_Type = ToDoAfter_Type;
        this.ToDoAfterTask = ToDoAfterTask;
    }

    /**
     *
     * @param ToDoAfter_Type The type of ToDoAfter task, "date" type;
     * @param date The date for the task condition
     * @param ToDoAfterTask The user will do their task after "date";
     */
    public ToDoAfter(String ToDoAfter_Type, LocalDate date, Task ToDoAfterTask){
        this.date = date;
        this.ToDoAfter_Type = ToDoAfter_Type;
        this.ToDoAfterTask = ToDoAfterTask;
    }

    /**
     * To get the type of ToDoAfter task
     * @return return the type
     */
    public String getToDoAfter_Type(){
        return this.ToDoAfter_Type;
    }

    /**
     * To get the Date
     * @return return condition date
     */
    public LocalDate getDate(){
        return this.date;
    }

    /**
     * To get the task which the user is going to do after certain date condition is fulfill
     * @return return the task
     */
    public Task getToDoAfterTask (){
        return this.ToDoAfterTask;
    }

    /**
     * To get the condition task which the user will be going to do certain task after this is done.
     * @return return the condition task
     */
    public Task getTask(){
        return this.task;
    }
}
