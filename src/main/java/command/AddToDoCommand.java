package command;

import command.Command;
import exceptions.DukeEmptyException;
import exceptions.DukeException;
import task.Storage;
import task.TaskList;
import task.ToDo;
import ui.UI;

import java.io.IOException;
        //import java.util.Arrays;

public class AddToDoCommand extends Command {
    public static final String INPUT_WORD = "todo";
    private ToDo todo;

    public AddToDoCommand(boolean isExit, String input){
        super(isExit, input);
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeEmptyException, DukeException, IOException{
        int index = 0;
        if((input.substring(4).trim()).isEmpty()){
            throw new DukeEmptyException("todo");
        }
        todo = new ToDo(input.substring(5));
        taskList.addToTaskList(todo);
        ui.showOutputToUser("Got it. I've added this task:\n\t " + todo.toString() + "\n\t Now you have " + taskList.getSize() + " task in the list.");
        storage.saveToFile();
    }
}