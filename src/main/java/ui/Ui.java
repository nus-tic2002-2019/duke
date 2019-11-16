//level 7.more oop
/**
 * UI interface for displaying results and user command
 */

package ui;

import task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import task.Task;
import java.util.ArrayList;

public class Ui {

    public Ui() {

    }

    public void welcome() {
        //public static void main(String[] args) throws DukeException {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?\n");
            //System.out.println("Type =>> Help <== for typical commands");

        }

        public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void printUI(String printline){
        System.out.println(printline);
    }

    //public static void showline() {
    //    printUI( "--------------------------------------------------"+  System.lineSeparator());
    //}

    public static void invalid() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means.");
    }

    public static void showError(String message) {
        printUI( message +  System.lineSeparator());
    }

    public static void bye() {
        System.out.println("--------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------");
    }

    public static void added(ArrayList<Task> t, Integer index) {
        System.out.println("--------------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(t.get(index-1));
        System.out.println("Now you have " + index + " tasks in the list.");
        System.out.println("--------------------------------------------------");
    }

    public static void delete(ArrayList<Task> t, Integer index) {
        System.out.println("--------------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.get(index-1));
        System.out.println("Now you have " + (t.size()-1) + " tasks in the list.");
        System.out.println("--------------------------------------------------");
    }

    public static void done(ArrayList<Task> t, Integer index) {
        System.out.println("--------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.get(index-1));
        System.out.println("--------------------------------------------------");
    }

    public static void list(ArrayList<Task> t, Integer index) {
        //String timeString ="";
        System.out.println("--------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for ( int i = 0; i < index ; i++ ) {
            singleList(t, i);
        }
        System.out.println("--------------------------------------------------");


    }

    public static void singleList(ArrayList<Task> t, Integer index) {
        String timeString ="";
        String command = t.get(index).toString().substring(1, 2);
        switch (command) {
            case ("D"):
                timeString = t.get(index).toString().split(" by:")[1].replace(")","");
                break;
            case ("E"):
                timeString = t.get(index).toString().split(" at:")[1].replace(")","");
                break;
        }

        if (command.equals("T")) {
            System.out.println((index + 1) + "." + t.get(index));
        } else {
            System.out.println((index + 1) + "." + t.get(index).toString().replace(timeString,localDateTime(timeString)));

        }

    }

    public static String localDateTime(String timeString){
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");  //Create formatter
        LocalDateTime ldtString = LocalDateTime.parse(timeString);

        return ldtString.format(FORMATTER); //Get formatted String
    }



}
