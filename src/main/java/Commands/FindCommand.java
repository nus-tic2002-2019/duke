package Commands;

import Exception.DukeException;
import Storage.Storage;
import Tasks.Task;
import Tasks.TaskList;
import UI.Ui;

import java.util.ArrayList;

/**
 *
 */
public class FindCommand extends Command {
    /**
     *
     * @param taskDes
     */
    public FindCommand(String taskDes){
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
        if(tasks.getSize()<= 0){
            throw new DukeException("Sorry. There isn't a list");
        }
        try{
            taskItem.substring(5);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Find command can't be empty");
        }
        ArrayList<Task> tempTasksList = new ArrayList<>();
        String keyword = taskItem.substring(5);
        for(int i = 0; i<tasks.getSize(); i++){
            Task currentTasksClass = tasks.getTask(i);
            String theStringTask = currentTasksClass.getTask();
            if(theStringTask.contains(keyword)){
                tempTasksList.add(currentTasksClass);
            }
        }
        ui.showAllArrayList(tempTasksList);

    }

}
