package com.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;
    private static final String SEPARATE_LINE="_________________________________________________";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String ERROR_LOADING_MSG="Failed to load this Duke Application from storage file.";
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(SEPARATE_LINE);
        System.out.println("Hello! I am Duke\nWhat can I do for you?");
        System.out.println(SEPARATE_LINE);
    }

    /**
     * read command from the userInput
     * @return the string of user input
     */
    public String readCommand() {
        out.print("Enter Command: ");
        String line=in.nextLine();
        return line;
    }

    /**
     * show the divider line.
     */
    public void showLine() {
        out.println(SEPARATE_LINE);
    }

    /**
     * show the error message
     * @param message the passed message to show
     */
    public void showError(String message) {
        out.println(message);
    }

    /**
     * show the loading error message to the user.
     */
    public void showLoadingError() {
        out.print(ERROR_LOADING_MSG);
    }
}
