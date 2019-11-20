package main.duke.ui;

import main.duke.task.Task;
import main.duke.task.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Primarily for printing messages to console, also passes input to Parser.
 */
public class Ui {
    private Scanner input;

    public void printTask(Task t) {
        System.out.println(t.toString());
    }

    public void printEventsOnDateMsg(LocalDateTime dt) {
        System.out.println(String.format("Events and Deadlines on %s", dt.toLocalDate().toString()));
    }

    public void printNoEventOnDateMsg() {
        System.out.println("No events on this date.");
    }

    public void printFindResultsMsg(ArrayList<Task> results){
        if(results.size() != 0) {
            System.out.println("Here are the matching tasks in your list:\n");
            System.out.println(TaskList.listTasks(results));
        }else {
            System.out.println("No results found!");
        }
    }

    public void printEditTaskMsg(Task t) {
        System.out.println(String.format("Editing task: %s", t.toString()));
        System.out.println(String.format("Input new description for %s.", t.getClass().getSimpleName()));
    }

    public void printEditDateMsg() {
        System.out.println("Input new date for event/deadline.");
    }

    public void printAddMsg(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());   //increment size after printing the task added.
        System.out.println(String.format("Now you have %d %s in the list.", tasks.size(), (tasks.size() > 1) ? "tasks" : "task"));
    }

    public void printByeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printDeleteMsg(Task currTask, TaskList tasks) {
        System.out.println(String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.", currTask, tasks.size()));
    }

    public void printDoneMsg(TaskList t, int pos) {
        Task currTask = t.get(pos);
        if (currTask.isDone()) {
            System.out.println("Task is already done.");
        } else {
            System.out.println(String.format("Nice! I've marked this task as done:\n%s", currTask));
        }
    }

    public void printErrorMsg(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printHelloMsg() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    /**
     * @return reads user's input to be parsed by Parser
     */
    public String readCommand() {
        String inputStr = "";
        while (inputStr.isBlank() || inputStr.isEmpty()) {
            try {
                inputStr = input.nextLine().strip();
            } catch (Exception e) {
                System.out.println("Error parsing input. Try again.");
            }
        }
        return inputStr;
    }

    public Ui() {
        input = new Scanner(System.in);

    }
}
