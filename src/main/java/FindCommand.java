import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    FindCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(tasks.getSize()<= 0){
            throw new DukeException("Sorry. There isn't a list");
        }
        if(taskItem.substring(5).isEmpty()){
            throw new DukeException("Find task can't be empty");
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
