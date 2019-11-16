package task;

import java.util.ArrayList;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() { return description; }

    public void setIsDoneTrue() {isDone = true;}

    public String getStatusIcon() {return (isDone ? "\u2713" : "\u2718");}

    public void printTask(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        System.out.println("Now you have " + (tasks.indexOf(this) + 1) + " tasks in the list");
    }

    public static void listTask(ArrayList<Task> task) {
        if (task.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task t : task) {
                System.out.println((task.indexOf(t) + 1) + "." + t);
            }
        }
    }

    public static void doneTask(ArrayList<Task> task, int taskItem) {
        task.get(taskItem - 1).setIsDoneTrue();
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + task.get(taskItem - 1));
    }

    public static void deleteTask(ArrayList<Task> task, int taskItem) {
        Task t = task.get(taskItem - 1);
        task.remove((taskItem - 1));
        System.out.println("Noted. I've remove this task:" + System.lineSeparator() + t + System.lineSeparator() + "Now you have " + task.size() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return " [" + getStatusIcon() + "]" + getDescription();
    }

}
