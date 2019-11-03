import java.io.IOException;
import java.util.*;
import ERROR_HANDLING.*;
import COMMAND.*;
import STORAGE.*;
import UI.*;

public class Duke {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Storage storage;
    private Message ui;
    private CommandList keywords;


    public Duke(){
        ui = new Message();
        keywords = new CommandList();

        try {
            storage = new Storage("data/taskSheet.txt");
        } catch (IOException ioe) {
            System.out.println("\tFile Error!");
        }

    };

    public static void main(String[] args) throws Exception {
        new Duke().start();
        return;
    }

    private void start() throws Exception {
        ui.showGreetingMessage();

        Scanner in = new Scanner(System.in);

        String userInput = new String();


// MAIN LOGIC
// USER INPUT

        while(!userInput.equals("bye")) {
            userInput = in.nextLine();

            String firstWord = keywords.splitKeyword(userInput)[0];
            // Some command is single word; Some command must have second part after space
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
        for (Task task : list) {
            System.out.printf("\t%d.%s" + System.lineSeparator(),taskNumber, task);
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
    @Ovoutide
    public String toString() {
        return "|"  + logo + "|" ;
    } */


}
