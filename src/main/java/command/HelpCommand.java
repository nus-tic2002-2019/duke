package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;

/**
 * Represents the help command.
 * */

public class HelpCommand extends Command{
    public HelpCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Listing all the commands for user to input
     * */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        System.out.println("This is the list of commands:\n");
        System.out.println("1)bye - To exit program \n");
        System.out.println("2)todo - Input todo task by typing todo <task name>\n");
        System.out.println("3)deadline - Input deadline task by typing deadline <task name> /<dd-MM-yyyy HH:mm:ss>\n");
        System.out.println("4)event - Input event task by typing event <task name> /<dd-MM-yyyy HH:mm:ss>\n");
        System.out.println("5)delete - Delete task by typing delete + number of the task listed\n");
        System.out.println("6)list - To list out all the existing tasks\n");
        System.out.println("7)done - To add a tick to a completed task\n");
        System.out.println("8)save - To save existing task list\n");
        System.out.println("9)saveTo - To save existing task list to a specific pathing\n");
        System.out.println("10)loadFrom - To load the task list from a file\n");
        System.out.println("11)undo - To undo the previous task\n");
    }
}
