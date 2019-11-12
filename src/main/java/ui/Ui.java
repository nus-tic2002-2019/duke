//level 7.more oop

package ui;

import java.util.Scanner;
import task.Task;
import java.util.ArrayList;

public class Ui {

    public Ui(){

    }

    public void showError(){

    }

    public void welcome(){
        //public static void main(String[] args) throws DukeException {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?\n");

        }

        public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void printUI(String printline){
        System.out.println(printline);
    }

    public static void showLine() {
        printUI( "--------------------------------------------------"+  System.lineSeparator());
    }

    public static void invalid() {
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void showError(String message) {
        printUI( message +  System.lineSeparator());
    }

    public static void bye() {
        System.out.println("\t--------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("\t--------------------------------------------------");
    }

    public static void added(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t.get(index-1));
        System.out.println("\tNow you have " + index + " tasks in the list.");
        System.out.println("\t--------------------------------------------------");
    }

    public static void delete(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + t.get(index-1));
        System.out.println("\tNow you have " + (t.size()-1) + " tasks in the list.");
        System.out.println("\t--------------------------------------------------");
    }

    public static void done(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + t.get(index-1));
        System.out.println("\t--------------------------------------------------");
    }

    public static void list(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<index ; i++) {
            System.out.println("\t" + (i+1) + "." + t.get(i));
        }
        System.out.println("\t--------------------------------------------------");
    }



}
