import java.util.ArrayList;
import TaskList.*;

public class Task_old {
    protected String description;
    protected boolean isDone;

//    private  static ArrayList<String> todolistArray = new ArrayList<>();
//    private static Task[] tasks_addTask = new Task[100];  //*****
//    private static int taskCount_addTask = 0;
//    private static int task_count = 0;



    public Task_old(String description) {


            this.description = description;
            this.isDone = false;
    }
//
//    public static Task addTask(Task s){
//        tasks_addTask[taskCount_addTask] = s;
//        todolistArray.add(tasks_addTask[taskCount_addTask].toString());
//        TaskList.add()
//        taskCount_addTask++;
//
//        return s;
//    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String toString(){
        return description;
    }



}