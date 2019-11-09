import java.io.*;
import java.util.*;
import error_handling.*;
import command.*;
import storage.*;
import UI.*;
import parse.*;

public class Duke {

    private Message ui;
    private Storage file;
    private TempTaskList list;
    private CommandList keywords;

    public Duke() throws Exception{
        ui = new Message();
        list = new TempTaskList();
        keywords = new CommandList(list);
    };

    public static void main(String[] args) throws Exception {
        new Duke().start();
    }

    private void start() throws Exception {
        ui.showGreetingMessage();

        Scanner in = new Scanner(System.in);
        String userInput = "";

        String username = in.nextLine();
        String filename = Parser.convertFileName(username);

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
        file.read(list);


// MAIN LOGIC
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
    @Override
    public String toString() {
        return "|"  + logo + "|" ;
    } */


}
