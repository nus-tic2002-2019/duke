package command;

import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import tasklist.Events;
import ui.Ui;

import java.io.IOException;
import exception.DukeException;

import static util.Util.convertDateTime;

public class EventCommand extends Command {

    /**
     * Constructor for EventCommand. The class is process event related task.
     * @param commandline a string commandline contains a string of command to be processed to mark a task as done.
     */
    public EventCommand(String commandline){
        super(commandline);
    }

    /**
     * This method is a method to add a new event into a list of tasks
     *
     * @param tasks tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related processes
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            tasks.addTask(new Events(Parser.parseEvent(commandline)[0], convertDateTime(Parser.parseEvent(commandline)[1])),false   );
//            tasks.addTask(new Deadlines(Parser.parseDeadlineFile(commandline)[2], convertDateTime(Parser.parseDeadlineFile(commandline)[3])), false);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }

    /**
     * To read file and add deadline tasks to the memory, and store in tasks object to be used by application
     *
     * @param tasks tasks object passed and used throughtout the program
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void readFileFormat(TaskList tasks) throws DukeException, IOException {
        try {
            tasks.addTask(new Events(Parser.parseEventFile(commandline)[2], convertDateTime( Parser.parseEventFile(commandline)[3])),true);
            if(Parser.parseEventFile(commandline)[1].equals("1")){
                tasks.markDone(tasks.size(),true);
            }
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
