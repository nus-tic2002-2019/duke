package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;

/**
 * Command to Create a new ToDos task
 */
public class NewTodoCommand extends Command {
    /**
     * Constructs the ToDo Command
     * @param taskDes the command the user input
     */
    public NewTodoCommand(String taskDes){
        super(taskDes);
    }

    /**
     * Execute the ToDo command to create the ToDos task
     * @param tasks the task list
     * @param ui the Ui
     * @param storage the Storage
     * @throws DukeException any expected error
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(5);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Todo command can't be empty");
        }
        ToDos todo = new ToDos(taskItem.substring(5));
        assert todo.getTaskType() == TaskType.TODOS; //Checking whether the task has been created as a ToDo before adding and saving
        tasks.addTask(todo);
        storage.save(tasks);
    }

}
