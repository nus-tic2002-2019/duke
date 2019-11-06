package command;

import error.IllegalStringException;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public abstract class Command {

    protected String description;
    protected int index;
    protected int priority;
    protected boolean isExit = false;

    /**
     * Constructor for command and description
     * @param description
     */
    public Command(String description) {
        this.description = description;
    }

    /**
     * Constructor for command value and description
     * @param description
     */
    public Command(String description, int value) {
        this.description = description;
        this.priority = value;
    }

    /**
     * Constructor for command and index
     * @param index
     */
    public Command(int index){
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

    /**
     * Get description of the task
     * @return String
     */
    public String getDescription(){
        return this.description;
    };


}
