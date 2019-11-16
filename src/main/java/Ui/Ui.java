package Ui;

import Tasks.Task;
import TaskList.*;
import java.util.Scanner;
import Parser.*;

public class Ui {
    private static final String line = "    ____________________________________________________________";
    /**
     * Prints welcome message of duke.
     * */
    public static void Greet(){
//        String line = ;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLines(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLines(line);

    }

    public String userInput;
    public Scanner scan = new Scanner( System.in ); // To getting userinput


    /**
     * to Read user input
     */
    public String readCommand(){
        userInput = scan.nextLine();
        return userInput;
    }

    /**
     *
     * @param insert
     * @param store
     */
    public void newTask(Task insert, TaskList store){
        printLines(line);
        System.out.println("      Got it. I've added this task:");
        System.out.println("       " + insert.toString());
        System.out.println("     Now you have " +  Integer.toString(store.getSize()) +  " tasks in the list.");
        printLines(line);
    }

    /**
     * prints the Todo Messages
     * @param store
     */
    public void newTodo(TaskList store){

        printLine();
        System.out.println("      Got it. I've added this task:");
        System.out.println("     " + store.getTask(store.getSize()-1).getDescription());
        printLine();
    }

    public void newDone(){
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        //System.out.println("      " + store.getTask(new Parser().parsedInput())

    }

    /**
     * Show goodbye message
     */
    public void showGoodBye(){
        System.out.println(userInput);
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Return Loading error message
     */
    public void showLoadingError(){
        System.out.println(" :(  Loading Error!");
    }


    //Displaying Task class list
    public void displayTaskList(String input,TaskList tasklist){
        System.out.println(input);
        printLines(line);
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < tasklist.getSize(); i++){
            int idx = i+1;
            System.out.println("     "+ idx + ". " + tasklist.getTask(i).toString() );
        }
        printLines(line);
    }



    /**
     * Return UI of lines when invoked
     */
    public static void printLines(String k){
        System.out.println(k);
    }

    public static void printLine(){
        System.out.println(line);
    }

    public void Line(){
        System.out.println(line);
    }

    public String getLine(){
        return line;
    }

}
