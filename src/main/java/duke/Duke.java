package duke;

import parser.Parse;
import storage.Storage;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasklist = new ArrayList<Task>();
    private Storage store ;

    public Duke(String filename){
        //ui = new ui.Ui();
        store = new Storage(filename);
/*        try {
            tasks = new TaskList(storage.load());
        } catch (exceptions.DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } */
    }

    public void run(){
        //ui.welcome();
    }

    public static void main(String[] args) {

        Ui ui = new Ui();
        Parse parser = new Parse();
        Boolean isExit = false ;

        ui.welcome(); // duke.Duke welcome message

        Storage store = new Storage("D:\\git\\output.txt");

        store.LoadFile(tasklist);

        while (!isExit) {
            parser.parser(tasklist);
            isExit = parser.isExit();
        }

        store.SaveFile(tasklist);
    }

}