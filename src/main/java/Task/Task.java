package Task;

import TaskList.TaskList;

public class Task {
    protected String description;
    protected boolean isDone;

    private static Task[] tasks_addTask = new Task[100];  //*****
    private static int taskCount_addTask = 0;


    public Task(String description) {


            this.description = description;
            this.isDone = false;
    }

    public static void addTask(Task s){
        tasks_addTask[taskCount_addTask] = s;
        TaskList.addToTaskList(tasks_addTask[taskCount_addTask].toString());

        taskCount_addTask++;
    }

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