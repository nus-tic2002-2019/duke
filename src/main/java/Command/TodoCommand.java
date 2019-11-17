package command;
import storage.Storage;
import tasklist.TaskList;
import tasklist.Todo;
import ui.Ui;
import exception.DukeException;
import java.io.IOException;
import parser.Parser;

public class TodoCommand extends Command{
    public TodoCommand(String commandline){
        super(commandline);
    }

    /**
     * This method is a method to add a todo for Tasklist (which contains a list of tasks)
     * @param tasks tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try{
            //tasks.addTask(new Todo(Parser.parseTodo(commandline)),true);
            tasks.addTask(new Todo(Parser.parseTodo(commandline)));
        }
        catch (DukeException e)
        {
            Ui.showError(e.getMessage());
        }
    }

    /**
     * To read file from file and add it into a tasklist
     * @param tasks Tasklist object to add todo task into
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void readFileFormat(TaskList tasks) throws DukeException, IOException {
        try{
            tasks.addTask(new Todo(Parser.parseTodoFile(commandline)[2]));
            if(Parser.parseTodoFile(commandline)[1].equals("1")){
                tasks.markDone(tasks.size());
            }
        }
        catch (DukeException e)
        {
            Ui.showError(e.getMessage());
        }
    }
}
