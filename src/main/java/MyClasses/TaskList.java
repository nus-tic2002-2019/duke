package MyClasses;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private static final String HoriLine = "--------------------------------------------------\n";
    protected String taskList;
    protected Boolean taskStatus;
    private String Type = "Task";

    public TaskList(String Task) {
        this.taskList = Task;
        this.taskStatus = false;
        System.out.println("Added : " + Task + "\n" + HoriLine);
    }

    public void ListTask(int size) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println(size + 1 + "  . [" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList);
    }

    public void ListTask() {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println("[" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList);
    }

    public String save() {
        return Type + " | " + this.taskStatus + " | " + this.taskList;
    }
//    //Print First x number of tasks
//    public void ListTask() {
//            String temp = this.taskStatus? "\u2713" : "\u2613";
//            System.out.println(". [" + temp + "] " + this.taskList);
//        System.out.println("\n" +do HoriLine);
//    }

    public void DoneTask() {
        this.taskStatus = true;
        System.out.println(" Nice! I've marked this task as done : ");
        System.out.println("[\u2713] " + this.taskList);
        System.out.println("\n" + HoriLine);
    }

//    public void RemoveTask() {
//        String task_item = this.taskList;
//        this.taskList = "";
//        this.taskStatus = false;
//        System.out.println("Removed task " + task_item + "\n" + HoriLine);
//    }
}