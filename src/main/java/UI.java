/*
 *  UI.java
 *  Defines the UI - User Interface of 'Duke'.
 */

import java.util.Scanner;

public class UI {
    UI() {}

    /*
     * This method shows the Duke's Welcome message
     */
    void showWelcome(){
        showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    /*
     * This method show the divider line.
     */
    void showLine(){
        System.out.println("\t_____________________________________________________________");
    }

    /*
     * Returns input from user
     * @return myObj Input from user
     */
    String readCommand(){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    /*
     * This method show error during loading.
     */
    void showLoadingError() {
        System.out.println("No record loaded into task list.");
    }

    /*
     * This method display error message.
     * @param message Error message
     */
    void displayError(String message){
        System.out.println(message);
    }
}
