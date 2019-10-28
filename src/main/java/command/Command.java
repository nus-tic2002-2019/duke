package command;

import Task.*;
import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;


public abstract class Command {

    String command;
    String description;
    String date;
    int index;
    public boolean isExit = false;

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public Command(String command, String description, String date) {
        this.command = command;
        this.description = description;
        this.date = date;
    }

    public Command(String command, int index){
        this.command = command;
        this.index = index;
    }

    public Command(boolean value){
        this.isExit = value;
    }

    public Command(){};


    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        switch(command) {
            case "event":
                task.addTask(new Event(description, date));
                break;
            case "deadline":
                task.addTask(new Deadline(description, date));
                break;
            case "todo":
                task.addTask(new Todo(description));
                break;
            case "done":
                task.doneTask(index);
                break;
            case "bye":
                isExit = true;
                break;
            case "list":
                ui.showToUser(task.getDescription());
                break;
            case "delete":
                task.deleteTask(index);
                break;
            default:
                throw new IllegalStringException();
        }
    }

    public boolean isExit(){
        return isExit;
    }
}
