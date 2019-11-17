package duke.task;

import java.util.ArrayList;

/**
 * The Task class contains all the methods for the keywords used
 * by the Duke program. These methods include getting the description,
 * updating the status, printing the task upon adding, listing all the
 * tasks in the list and deleting task from the list.
 */

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getDescription() { return description; }

    private void setIsDoneTrue() {isDone = true;}

    public String getStatusIcon() {return (isDone ? "\u2713" : "\u2718");}

    /**
     * The printTask method prints the every task that is added in.
     */
    public void printTask(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        System.out.println("Now you have " + (tasks.indexOf(this) + 1) + " tasks in the list");
    }

    /**
     * The listTask method lists all the tasks which are stored in the list.
     */
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

    /**
     * The doneTask method updates the status of the tasks in the list.
     */
    public static void doneTask(ArrayList<Task> task, int taskItem) {
        task.get(taskItem - 1).setIsDoneTrue();
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + task.get(taskItem - 1));
    }

    /**
     * The deleteTask method deletes task in the list.
     */
    public static void deleteTask(ArrayList<Task> task, int taskItem) {
        Task t = task.get(taskItem - 1);
        task.remove((taskItem - 1));
        System.out.println("Noted. I've remove this task:" + System.lineSeparator() + t + System.lineSeparator() + "Now you have " + task.size() + " tasks in the list.");
    }

    /**
     * The findTask method prints all the task that contains certain keyword(s).
     */
    public static void findTask(ArrayList<Task> task, String s) {
        int i = 1;
        boolean status = false;
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : task) {
            if (t.getDescription().contains(s)) {
                status = true;
                System.out.print(i + ".");
                System.out.println(t);
                i++;
            }
        }
        if (!status) {
            System.out.println("Sorry, no match found!");
        }
    }

    @Override
    public String toString() {
        return " [" + getStatusIcon() + "]" + getDescription();
    }

}
