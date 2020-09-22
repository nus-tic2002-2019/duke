package command;

import basic.Storage;
import basic.TaskList;
import basic.Ui;
import task.Todo;
import exception.DukeException;

import java.util.ArrayList;

public class AfterCommand extends Command {
    private String input;

    /**
     * @param input A String inputted by the user.
     */
    public AfterCommand(String input) {
        this.input = input;
    }

    private TaskList subTaskList(TaskList tasks, int i, int position) throws DukeException {
        ArrayList<String> array = new ArrayList<String>();
        TaskList taskList = new TaskList(array);
        for (int j = i + 1; j < tasks.sizeOfTask(); j++) {
            taskList.addTask(tasks.returnTask(j));
        }
        return taskList;
    }

    private void deleteTasks(TaskList tasks, int i) {
        for (int j = tasks.sizeOfTask() - 1; j >= i + 1; j--) {
            tasks.deleteTask(j);
        }
    }

    /**
     * @param tasks   The tasks stored in an ArrayList.
     * @param ui      The User Interface (UI).
     * @param storage The storage to allow reading and storing of tasks from and to a txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int position = input.indexOf("after");
        assert input.substring(position + 5).contains(" ") : "wrong format: ' ' needed";

        String editedInput = input.toLowerCase().substring(position + 6);
        ArrayList<String> array = new ArrayList<String>();
        TaskList taskList = new TaskList(array);
        String empty = "";
        Todo todo = new Todo(empty);

        if (editedInput.equals("") || editedInput.equals(" ")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means.");
        } else {
            int count = 0;
            for (int i = 0; i < tasks.sizeOfTask(); i++) {
                if (tasks.returnTask(i).description.contains(editedInput)) {
                    count++;
                    taskList = subTaskList(tasks, i, position);
                    deleteTasks(tasks, i);

                    String task = input.toLowerCase().substring(0, position - 1);
                    todo = new Todo(task);
                    tasks.addTask(todo);
                    break;
                }
            }

            for (int i = 0; i < taskList.sizeOfTask(); i++) {
                tasks.addTask(taskList.returnTask(i));
            }
            if (count == 0) {
                throw new DukeException("☹ Item not found.");
            } else {
                ui.showAdded();
                ui.printTaskNum(tasks, todo);
            }
        }
    }

}
