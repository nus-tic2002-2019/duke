package ui;

import task.Task;
import tasklist.*;

import java.util.Scanner;

public class Ui {

    public final static String logo = "`;-.          ___,\n" +
            "  `.`\\_...._/`.-\"`\n" +
            "    \\        /      ,\n" +
            "    /()   () \\    .' `-._\n" +
            "   |)  .    ()\\  /   _.'\n" +
            "   \\  -'-     ,; '. <\n" +
            "    ;.__     ,;|   > \\\n" +
            "   / ,    / ,  |.-'.-'\n" +
            "  (_/    (_/ ,;|.<`\n" +
            "    \\    ,     ;-`\n" +
            "     >   \\    /\n" +
            "    (_,-'`> .'\n" +
            "pika      (_,'\n";

    public Scanner in = new Scanner(System.in);
    public String input;

    /**
     * Return error message
     */
    public void showLoadingError(){
        System.out.println("Loading Error!");
    }

    /**
     * Return the appropriate error message
     * @param msg
     */
    public void showError(String msg){
        System.out.println(msg);
    }

    /**
     * Return the add UI whenever AddCommand is invoked
     * @param insert
     * @param store
     */
    public void showAdd(Task insert, TaskList store){
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "     " + insert.toString() + "\n" +
                "     Now you have " +  Integer.toString(store.getSize()) +  " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    /**
     * Return the delete UI whenever DeleteCommand is invoked
     * @param insert
     * @param store
     */
    public void showDelete(Task insert, TaskList store){
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "          " + insert.toString() + "\n" +
                "     Now you have " + store.getSize()  + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    /**
     * Return message UI to user
     * @param msg
     */
    public void showToUser(String msg){
        System.out.println(msg);
    }

    /**
     * Return the welcome msg to user
     */
    public void showWelcome() {
        System.out.println("Hello I'm from\n" + logo);
    }

    /**
     * Return the exit UI to user
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Return dividing line to user
     */
    public void showLine(){
       System.out.println("_______");
    }

    /**
     * UI will take in user input command
     * @return the user input
     */
    public String readCommand(){
        input = in.nextLine();
        return input;
    }
}
