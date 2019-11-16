package Commands;
import Storage.*;
import Ui.*;
import TaskList.*;

import Tasks.Task;

public abstract class Command {
    public boolean exit = false;
    protected String userInput;
    protected int idx;
    protected String description;

    /**
     * Empty Constructor
      */
    public Command(){

    }

    /**
     * Constructor for Command exit
     * @param exit
     */
    public Command(boolean exit){
        this.exit = exit;
    }

    /**
     * Constructor for done Command
     * @param idx
     */
    public Command(int idx){

        this.idx = idx;
    }

    /**
     * Constructor for Todo Command
     * @param des
     */
    public Command(String des){
        this.description = des;
    }
    /**
     *
     * @param input
     * @param exit
     */
    public Command(String input, boolean exit){
        this.exit = exit;
        this.userInput = input;
    }


    public boolean isExit(){
        return this.exit;
    }

    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);
}
