import java.util.Scanner;
import duke.ui.ui;
import duke.storage.storage;
import duke.tasklist.tasklist;
import duke.parser.parser;

public class Duke {
    private static ui ui;
    private static storage storage;
    private static parser parser;
    private static tasklist tasklist;

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

    public static void main(String[] args) {
        new Duke();
    }
}