package duke.ui;

import java.util.Scanner;

public class Ui {
    protected Scanner in;
    protected static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected static final String LINE = "_________________________________________________________________________\n";
    protected static final String MSG_SHOWEND = "Oyasumi~";
    protected static final String MSG_GREETING = "Harrowwwwwww\nWut iz up?";
    protected static final String MSG_UNKNOWN = "Sorry, I don't understand this :(";

    public Ui() {
        in = new Scanner(System.in);
    }

    public static void print(String input) {
        input = input.replace("\n", "\n\t");
        System.out.println("\t" + input);
    }

    public void showWelcome() {
        System.out.println(LOGO + "\n" + MSG_GREETING);
        showLine();
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showEnd() {
        print(MSG_SHOWEND);
    }

    public void showError(String err) {
        print(err);
    }

    public void showUnknown() {
        print(MSG_UNKNOWN);
    }

}
