package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import exception.DukeException;
import java.io.IOException;

public class Command {
    protected static String commandline;

    public boolean isExit;

    /**
     * Constructor for the Command object
     * @param commandline a string of commmand passed by the user
     */
    public Command(String commandline){
        this.commandline = commandline;
    }

    /**
     * Constructor for the Command object
     */
    public Command(){

    }

    /**
     * To obtain isExit value of the object, in order to check if the program is ending
     * @return a boolen value isExit if it indicates the end of the running of the program
     */
    public boolean isExit(){
        return this.isExit;
    }

    /**
     * This method is a method to be implemented by each subclass. It executes the operation for each
     * particular command supported by the application
     *
     * @param tasks   tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException return any DukeException if any
     * @throws IOException  return any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        throw new UnsupportedOperationException("");
    }


    /**
     * This method is a method to be implemented by each subclass. It executes the reading operation for each particular
     * file format supported by the application
     * @param tasks tasks object passed and used throughtout the program
     * @throws DukeException return any DukeException if any
     * @throws IOException return any IOException if any
     */
    public void readFileFormat(TaskList tasks) throws DukeException, IOException {
        throw new UnsupportedOperationException("");
    }

}
