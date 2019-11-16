package main.java.duke.commands;


/**
 * delete a task from the taskList based on the index.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD="delete";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(){

        taskList.removeTask(getTargetIndex()-1);
        System.out.print("Remove successfully.\n");
    }
}
