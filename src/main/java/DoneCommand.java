public class DoneCommand extends Command {

    DoneCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(taskItem.substring(5).isEmpty()){
            throw new DukeException("Delete task can't be empty");
        }
        try {
            int taskIndex = Integer.parseInt(taskItem.substring(5)) - 1;
            Task doneTask = tasks.getTask(taskIndex);
            doneTask.edit_done(true);
            Ui.doneMsg(doneTask.toString());
        }
        catch(NumberFormatException e){
            throw new DukeException("Please key in task number");
        }

        storage.save(tasks);
    }

}
