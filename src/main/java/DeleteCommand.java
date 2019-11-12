public class DeleteCommand extends Command {

    DeleteCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(taskItem.substring(7).isEmpty()){
            throw new DukeException("Delete task can't be empty");
        }
        try {
            int taskIndex = Integer.parseInt(taskItem.substring(7))-1;
            String deletedTask = tasks.getTask(taskIndex).toString();
            tasks.remove(taskIndex);
            ui.deleteMsg(deletedTask);
            storage.save(tasks);
        }
        catch(NumberFormatException e){
            throw new DukeException("Please key in task number");
        }
    }


}
