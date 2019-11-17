package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;

/**
 *
 */
public class NewTodoCommand extends Command {
    /**
     *
     * @param taskDes
     */
    public NewTodoCommand(String taskDes){
        super(taskDes);
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(5);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Todo command can't be empty");
        }
        ToDos todo = new ToDos(taskItem.substring(5));
        assert todo.getTaskType() == TaskType.TODOS;
        tasks.addTask(todo);
        storage.save(tasks);
    }

}
