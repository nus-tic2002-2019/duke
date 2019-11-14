package main;

import DukeTasks.Task;
import java.util.ArrayList;

public interface UI {

    /**
     * Prints the first message when Duke is started.
     *
     * @return The starting message.
     */

    static String start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
				
        return ("Hello from \n" + logo + "What can I do for you?");

    }
	static String bye() {
        return ("Bye. Hope to see you again soon!");
    }
	
	/**
     * Prints the contents of the TaskList (given as an argument) whenever a new task is added to the TaskList.
     *
     * @param taskList A TaskList instance unique to each instance of Duke.
     * @return The message explaining a new task.
     */

    static String newTask(ArrayList<Task> taskList) {
        return "Got it. I've added this task: \n"
                +   "    "  + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list. \n";
    }
	 /**
     * Prints that a given Task is designated as done, typically when a DoneCommand is excuted.
     *
     * @param task The Task designated as done.
     * @return The done message.
     */

    static String done(Task task) {
        return "Nice! I've marked this task as done:\n" + " " + task + "\n";
    }
	/**
     * Prints the size of the TaskList (given as an argument) and the Task to be removed (also given as an argument)
     * of the TaskList whenever a Task is removed from the TaskList. Typically occurs when a RemoveCommand is executed.
     *
     * @param task The Task to be removed from the TaskList.
     * @param listSize The size of the TaskList, after removal of Task.
     *
     * @return The message explaining the deletion of a task.
     */

    static String removedTask(Task task, int listSize) {
        return " Noted. I've removed this task: \n"
                + "    " + task + "\n"
                + "Now you have " + listSize + " tasks in the list.\n";

    }
}