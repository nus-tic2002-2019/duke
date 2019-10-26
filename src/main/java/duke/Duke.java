package duke;

import parser.Parse;
import storage.Storage;
import task.Task;
import ui.Ui;

import java.util.ArrayList;



/**
 * Represent a task scheduler using console for input of task and schedule
 * and output task list and its status to the console.
 */
public class Duke{

    private static ArrayList<Task> tasklist = new ArrayList<Task>();

    public static void main(String[] args) {

        Ui ui = new Ui();
        Parse parser = new Parse();
        Boolean isExit = false ;

        ui.welcome();

        Storage store = new Storage("D:\\git\\output.txt");

        store.LoadFile(tasklist);

        while (!isExit){
            parser.parser(tasklist);
            isExit = Parse.isExit();
        }

        store.SaveFile(tasklist);

    }

}