package main.duke.ui;

import main.duke.task.Task;
import main.duke.task.TaskList;

import java.util.Scanner;


public class Ui {
    private Scanner input;


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
