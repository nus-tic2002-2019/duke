package Commands;

import Parser.Parser;
import Parser.myMethods;
import Tasks.*;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class addTodoCommand extends Command{
    public static final String COMMAND = "todo";


    /**
     * Constructor for todo Command
     * @param description
     */
    public addTodoCommand(String description){
        super(description);
    }


    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        Task newTask = new toDos(description);
        tasklist.addTask(newTask);
        ui.newTodo(tasklist);
    }
}
