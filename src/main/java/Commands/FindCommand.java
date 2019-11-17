package Commands;

import Exception.DukeException;
import Storage.Storage;
import Tasks.Task;
import Tasks.TaskList;
import UI.Ui;

import java.util.ArrayList;

/**
 * Command to show the list of task the user want to find using a keyword
 */
public class FindCommand extends Command {
    /**
     * Constructs the Find command
     * @param taskDes the command the user input
     */
    public FindCommand(String taskDes){
        super(taskDes);
    }

    /**
     * Execute the find command by finding the keyword from the list of task
     * @param tasks the task list
     * @param ui the Ui
     * @param storage the Storage
     * @throws DukeException any expected error
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
