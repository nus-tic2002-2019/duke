package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class ByeCommand extends Command{
    public static final String INPUT_WORD = "bye";
    public static final String MESSAGE_BYE_ACKNOWLEDGEMENT = "Bye. Hope to see you again soon!";

    /** 
     * Constructs a new ByeCommand and initialise with the specified isExit boolean value and input by the user.
     * @param   isExit  A boolean value whether if the exit condition is true.
     * @param   input   A String inputted by the user.
     */
    public ByeCommand(boolean isExit, String input){
        super(isExit, input);
    }

    /** 
     * Prints a goodbye message using User Interface (UI).
     * @param   taskList            The array of tasks stored in an ArrayList.
     * @param   ui                  The User Interface (UI) to allow interaction with the user.
     * @param   storage             The storage to allow storing and reading of tasks from a file.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage){
        ui.showGoodbyeMessage();
    }
}