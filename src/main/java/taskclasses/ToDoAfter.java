package taskclasses;

import java.time.LocalDate;

public class ToDoAfter extends Task {
    private String ToDoAfter_Type;
    private Task ToDoAfterTask, task;
    private LocalDate date;

    //Constructor for task type;
    ToDoAfter(String ToDoAfter_Type, Task task, Task ToDoAfterTask){
        this.task = task;
        this.ToDoAfter_Type = ToDoAfter_Type;
        this.ToDoAfterTask = ToDoAfterTask;
    }

    //Constructor for date type;
    ToDoAfter(String ToDoAfter_Type, LocalDate date, Task ToDoAfterTask){
        this.date = date;
        this.ToDoAfter_Type = ToDoAfter_Type;
        this.ToDoAfterTask = ToDoAfterTask;
    }
}
