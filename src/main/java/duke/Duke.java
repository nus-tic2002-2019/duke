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
    private static Ui ui = new Ui();
    private static Parse parser = new Parse();
    private static Storage store = new Storage("D:\\output.txt");

    public static void main(String[] args) {

        Boolean isExit = false ;

        ui.welcome();

        store.LoadFile(tasklist);

        while (!isExit){
            parser.parser(tasklist);
            store.SaveFile(tasklist);
            isExit = parser.isExit();
        }
    }
}