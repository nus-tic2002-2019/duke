package Commands;

import Parser.Parser;
import Parser.myMethods;
import Tasks.*;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class addTodoCommand extends Command{
    public static final String COMMAND = "todo";

    public addTodoCommand(String des){
        super(des);

    }


    public void execute(TaskList tasklist, Ui ui, Storage storage){
        String tmp = new myMethods().parsed(description)[1];
        Task newTask = new toDos(description);
        tasklist.addTask(newTask);
//        ui.newTask(newTask, tasklist);
        ui.newTodo(tasklist);
    }
}
