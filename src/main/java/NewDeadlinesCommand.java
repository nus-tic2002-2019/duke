import java.time.LocalDate;

public class NewDeadlinesCommand extends Command{
    NewDeadlinesCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(taskItem.substring(9).isEmpty()){
            throw new DukeException("Deadline task can't be empty");
        }
        if(!taskItem.contains(" /by ")){
            throw new DukeException("Please state /by yyyy-mm-dd");
        }

        int dividerPosition2 = taskItem.indexOf(" /by ");
        String taskDes = taskItem.substring(9, dividerPosition2);
        String taskTime = taskItem.substring(dividerPosition2+5);
        LocalDate d1 = LocalDate.parse(taskTime);
        tasks.addTask(new Deadlines(taskDes, d1));
        storage.save(tasks);
    }
}
