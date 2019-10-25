package duke;

import java.util.Scanner;

public class UI {
    Scanner sc = new Scanner(System.in);
    public UI(){

    }

    /**
     * This display the project DUKE
     */
    public void showWelcome() {
        String logo = " __        _        \n"
                + "|  _ \\ _   | | __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| || | || |   <  __/\n"
                + "|/ \\,||\\\\__|\n";
        System.out.println(logo);
        System.out.println("____________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________");
    }

    public void showEnd() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine(){
        System.out.println("________");
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public void showList(Tasklist tasks) {
        if (tasks.getSize() >= 0) {
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                System.out.println(i + 1 + "." + tasks.getTasks().get(i).toString());
            }
        }
    }

    public void showOthers() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showAdd(Task task, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public void showDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:  ");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showFind(Tasklist tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        showList(tasks);
    }
}
