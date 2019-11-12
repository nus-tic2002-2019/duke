public class NewTodoCommand extends Command{

    NewTodoCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(taskItem.substring(5).isEmpty()){
            throw new DukeException("ToDo task can't be empty");
        }
        tasks.addTask(new ToDos(taskItem.substring(5)));
        storage.save(tasks);
    }

}
