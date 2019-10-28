package ui;

import Task.Task;
import tasklist.*;

import java.util.Scanner;

public class Ui {

    public final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Scanner in = new Scanner(System.in);
    public String input;

    public void showLoadingError(){
        System.out.println("Loading Error!");
    }

    public void showError(String msg){
        System.out.println(msg);
    }

    public void showAdd(Task insert, TaskList store){
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][\u2718] " + insert.getDescription() + "\n" +
                "     Now you have " +  Integer.toString(store.getSize()) +  " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public void showDelete(Task insert, TaskList store){
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "          " + insert.toString() + "\n" +
                "     Now you have " + store.getSize()  + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    public void showToUser(String msg){
        System.out.println(msg);
    }

    public void showWelcome() {
        System.out.println("Hello I'm from\n" + logo + "What can I do for you?");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void showLine(){
       System.out.println("_______");
    }

    public String readCommand(){
        input = in.nextLine();
        return input;
    }
}
