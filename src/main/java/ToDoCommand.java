import java.io.IOException;

public class ToDoCommand extends Command{

    private Todo todo;

    public ToDoCommand(String input){
        super(input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException{
        todo = new Todo(input.substring(5));
        TaskList.addList(todo);
        ui.showOutputToUser("Got it. I've added this task:\n\t" + todo.toString() + "\n\t Now you have " + TaskList.getSize() + " tasks in the list.");
        storage.saveToFile();
    }
} 