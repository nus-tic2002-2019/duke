package Ui;

import java.util.Scanner;

/**
 * The user interface of Duke
 */

public class Ui {

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        System.out.println("------------------------------------------------------------");
    }
    public static void showLine(){
        printUI( "------------------------------------------------------------");
    }

    private static void printUI(String printline){
        System.out.println(printline);
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public static void showError(String message){
        printUI( message );
    }

    public static void sayBye(){
        printUI("Bye. Hope to see you again soon!");
    }

}
