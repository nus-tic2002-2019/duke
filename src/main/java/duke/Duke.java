package duke;

import java.io.*;
import java.util.*;
import duke.error_handling.*;
import duke.command.*;
import duke.storage.*;
import duke.UI.*;
import duke.parse.*;

public class Duke {

    private Message ui;
    private Storage file;
    private TempTaskList list;
    private CommandList keywords;

    public Duke() {
        ui = new Message();
        list = new TempTaskList();

    };

    public static void main(String[] args) throws Exception {
        new Duke().start();
    }

    /**
     * main logic:
     * identify user identity
     * read from stored file if any to restore the tasks in temporary list
     * read from user input, if command matches in keyword in HashMap,
     * method will automatically be triggered to run
     * "bye" to exit
     * @throws Exception
     */
    private void start() throws Exception {
        ui.showGreetingMessage();

        Scanner in = new Scanner(System.in);
        String userInput = "";

        String username = in.nextLine();
        String filename = Parser.convertFileName(username);
        assert !filename.equals("") : "empty filename no good";

        try {
            file = new Storage(filename);
            if (file.get().createNewFile()) {
                ui.newUser();
            } else {
                ui.existingUser(username);
            }
        } catch (IOException e) {
            ui.errorFileMessage();
        }

        keywords = new CommandList(list, file);
        file.read(list); // READ


// MAIN LOGIC
        while(!userInput.equals("bye")) {
            userInput = ui.read();

            // sometimes users may hit the space bar/enter, it's stupid to keep showing alerts for that
            if (userInput.trim().equals(""))
                continue;

            String firstWord = keywords.splitKeyword(userInput)[0];
            // Some duke.command is single word; Some duke.command must have second part after space
            String content = null;

            if (keywords.splitKeyword(userInput).length != 1) {
                content = keywords.splitKeyword(userInput)[1];
            }

            try {
                if (!keywords.containsKey(firstWord)){
                    throw new InvalidCommandException();
                }
            } catch (DukeException ex) {
                ui.dontKnowWhatToDoMessage();
                continue;
            }
            // THIS
            keywords.get(firstWord).run(content);

        }

        ui.showExitMessage();

    }


    /*
    public static boolean containsKeyword(String userInput, HashMap keywords) throws InvalidCommandException {
        String[] parts = userInput.split(" ", 2);

        if (keywords.containsKey(parts[0])){
            return true;
        }
        throw new InvalidCommandException();
    }
     */

/*
    public static void cmdPrintList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list: ");
        int taskNumber = 1;
        for (Task duke.task : list) {
            System.out.printf("\t%d.%s" + System.lineSeparator(),taskNumber, duke.task);
            ++taskNumber;
        }
    }

    public static String removeKeyword(String userInput) {
    String[] parts = userInput.split(" ", 2);
    return parts[1];
    }

    public static int getIntStringSpace(String userInput) {
    //userInput = userInput.substring(userInput.indexOf(" ") + 1); //split number str
    userInput = removeKeyword(userInput);
    return Integer.parseInt(userInput); // get number
    }
*/

    /*
    @Override
    public String toString() {
        return "|"  + logo + "|" ;
    } */


}
