package duke;

import java.util.List;
import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println("    Hello from\n" + logo);
        showLine();
        System.out.println("    Hello! I'm duke.Duke" + System.lineSeparator() + "    What can I do for you?");
        showLine();
    }

    private Scanner in = new Scanner(System.in);

    /**
     * Return whether there is another line of input.
     *
     * @return whether there is another line of input.
     */
    boolean hasNextLine() {
        return in.hasNextLine();
    }

    /**
     * Read a line from the user input.
     *
     * @return next line of input.
     */
    String readCommand() {

        return in.nextLine();
    }

    public void showLoadingError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Show an error message.
     *
     * @param message error message.
     */
    public void showError(String message) {

        System.out.println(message);

    }

    /**
     * Show output of a list messages.
     *
     * @param messages the list of messages.
     */
    public static void printCommand(List<String> messages) {
        for (String message : messages){
            System.out.println(message);
        }
    }
    public static void showLine() {
        System.out.println("    ________________________");
    }
}
