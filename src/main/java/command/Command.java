package command;

import task.*;
import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;
import java.time.LocalDate;

public abstract class Command {

    String command;
    String description;
    LocalDate date;
    int index;
    public boolean isExit = false;

    /**
     * Constructor for command and description
     * @param command
     * @param description
     */
    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Constructor for command, description and date
     * @param command
     * @param description
     * @param date
     */
    public Command(String command, String description, LocalDate date) {
        this.command = command;
        this.description = description;
        this.date = date;
    }

    /**
     * Constructor for command and index
     * @param command
     * @param index
     */
    public Command(String command, int index){
        this.command = command;
        this.index = index;
    }

    /**
     * Constructor for just boolean value
     * @param value
     */
    public Command(boolean value){
        this.isExit = value;
    }

    /**
     * Empty constructor
     */
    public Command(){};

    /**
     * Execute the appropriate commands
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException;

    /**
     * Getter for the isExit parameter
     * @return boolean
     */
    public boolean isExit(){
        return isExit;
    }
}
