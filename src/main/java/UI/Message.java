package UI;

import TASK.Task;

import java.io.*;
import java.util.*;

public class Message {
    private Scanner in;
    private PrintStream out;

    public Message () {
        this (System.in, System.out);
    }

    public Message(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /*
    public String readCommand () {


    }
     */

    public void printTaskList (ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("\tCheers, you have a free day!"
                    + System.lineSeparator()
                    + "\tWanna drop things in your mind?");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
        int taskNumber = 1;
        for (Task task : list) {
            System.out.printf("\t%d.%s" + System.lineSeparator(),taskNumber, task);
            ++taskNumber;
        }
    }
    public void addTaskMessage (Task task, int numberOfTask) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + task);
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), numberOfTask);
    }
    public void markDoneMessage (Task task) {
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t  " + task);
    }
    public void showGreetingMessage () {
        System.out.println("\tHey! Duke here, What can I do for you?");
    }
    public void showExitMessage () {
        System.out.println("\tBye. Hope to see you again soon!");
    }
    public void emptyTaskMessage () {
        System.out.println("\t☹ OOPS!!! The description of a task cannot be empty.");
    }
    public void dontKnowWhatToDoMessage () {
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(" );
    }
    public void doneTaskNoMessage () {
        System.out.println("\t☹ OOPS!!! please input a Task Number instead ~");
    }
    public void doneValidTaskNoMessage () {
        System.out.println("\t☹ OOPS!!! please input a valid Task No. ~");
    }

}
