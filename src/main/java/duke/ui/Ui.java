package duke.ui;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    protected Scanner in;
    protected static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected static final String LINE = "_________________________________________________________________________\n";
    protected static final String MSG_END = "Oyasumi~";
    protected static final String MSG_GREETING = "Harrowwwwwww\nWut iz up?";
    protected static final String MSG_UNKNOWN = "Sorry, I don't understand this :(";

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * A standardised format for all prints in the application.
     *
     * @param input is the string to be printed.
     */
    public static void print(String input) {
        input = input.replace("\n", "\n\t");
        System.out.println("\t" + input);
    }

    /**
     *  Prints the welcome message when application is initialised.
     */
    public void showWelcome() {
        System.out.println(LOGO + "\n" + MSG_GREETING);
        showLine();
    }

    /**
     * Reads and return the user input
     *
     * @return user input as String
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints a single line. Used mainly as separators between commands and results.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a farewell message after application has been terminated by the user.
     */
    public void showEnd() {
        print(MSG_END);
    }

    /**
     * Prints the error message of an exception.
     *
     * @param err is the exception
     */
    public void showError(String err) {
        print(err);
    }

    /**
     * Prints a message when command is unknown to the application.
     */
    public void showUnknown() {
        print(MSG_UNKNOWN);
    }

}
