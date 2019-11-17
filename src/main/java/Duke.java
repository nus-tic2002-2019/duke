import java.util.Scanner;
import duke.ui.ui;
import duke.storage.storage;
import duke.tasklist.tasklist;
import duke.parser.parser;

/**
 * Start of Duke.
 */
public class Duke {
    /**
     * Create variables for user interface (ui), list storing (storage), command parsing (parser) and task handling (taskList)
     */
    private static ui ui;
    private static storage storage;
    private static parser parser;
    private static tasklist tasklist;

    /**
     *takes user input and sends it to the parser
     */
    public Duke() {
        ui = new ui();
        parser = new parser();
        ui.welcome();
        Scanner input = new Scanner(System.in);
        String rawInput = "";
        while ( !rawInput.equals("bye") ) {
            rawInput = input.nextLine();
            parser.rawParser(rawInput);
        }
    }

    /**
     * Main method for starting the rest of the program
     */
    public static void main(String[] args) {
        new Duke();
    }
}