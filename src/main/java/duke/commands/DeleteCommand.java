package main.java.duke.commands;


/**
 * delete a task from the taskList based on the index.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD="delete";

    public static final String MESSAGE_USAGE="||"+COMMAND_WORD+": delete a task from the task list based on the index.\n" +
            "Syntax: delete INDEX\n" +
            "Example: "+COMMAND_WORD+" 2 (this will remove the No.2 task from the task list.)";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(){

        taskList.removeTask(getTargetIndex()-1);
        System.out.print("Remove successfully.\n");
    }
}
