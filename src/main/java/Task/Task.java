package Task;

import TaskList.TaskList;

public class Task {
    protected String description;
    protected boolean isDone;

    private static Task[] tasks_addTask = new Task[100];  //*****
    private static int taskCount_addTask = 0;

    /***
     * Task
     * @param description description for different Task - event, todo, deadline
     */

    public Task(String description) {

            this.description = description;
            this.isDone = false;
    }

    /***
     *
     * @param tasks add task (Todo Task, Deadline Task, Event Task)
     */
    public static void addTask(Task tasks){
        tasks_addTask[taskCount_addTask] = tasks;
        TaskList.addToTaskList(tasks_addTask[taskCount_addTask].toString());

        taskCount_addTask++;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    /***
     *
     * @return done return tick else return X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString(){
        return description;
    }
}