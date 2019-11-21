package main.duke.command;

import main.duke.exception.DukeException;
import main.duke.exception.DukeUnknownException;
import main.duke.storage.Storage;
import main.duke.task.*;
import main.duke.ui.Ui;

public class UpdateCommand extends Command {


    public enum Operation {
        Done, Delete, Add, Edit;
    }

    private Operation operation;
    private int position;
    private Task task;

    public Task getTask() {
        return task;
    }

    public Operation getOperation() {
        return operation;
    }

    /**
     * Checks Operation tagged to command, executes it accordingly
     * Add: Adds task and prints added task to console
     * Done: prints task which was done and tags it as done.
     * Delete: removes task from tasks before printing message.
     * Edit: edits tasks and replaces previous iteration of task in list.
     * Default: Throws DukeUnknown error as UpdateCommand should only have Add, Done, Delete.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (operation) {
            case Add:
                tasks.add(task);
                ui.printAddMsg(tasks);
                break;
            case Done:
                ui.printDoneMsg(tasks, position);
                tasks.done(position);
                break;
            case Delete:
                ui.printDeleteMsg(tasks.remove(position), tasks);
                break;
            case Edit:
                Task e_task = tasks.get(position);
                ui.printEditTaskMsg(e_task);
                e_task.setDescription(ui.readCommand());
                if (e_task.getClass() == Deadline.class || e_task.getClass() == Event.class) {
                    ui.printEditDateMsg();
                    String date_str = ui.readCommand();
                    if (e_task.getClass() == Deadline.class) {
                        Deadline d = (Deadline) e_task;
                        d.setDeadline(date_str);
                        e_task = d;
                    } else {
                        Event e = (Event) e_task;
                        e.setStart_endTime(date_str);
                        e_task = e;
                    }
                }
                tasks.edit(position, e_task);
                ui.printTask(tasks.get(position));
                break;
            default:
                ui.printErrorMsg(new DukeUnknownException());
                break;
        }
    }

    public UpdateCommand(Operation operation, int pos) {    //inputted index is not valid. Try again.
        this.operation = operation;
        position = pos;
    }

    public UpdateCommand(Operation operation, Task task) {
        this.operation = operation;
        this.task = task;
    }
}
