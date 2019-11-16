package main.java.duke.commands;

import main.java.duke.task.Task;
import main.java.duke.task.TaskList;

/**
 * add a task to the list
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD_ONE="todo";
    public static final String COMMAND_WORD_TWO="deadline";
    public static final String COMMAND_WORD_THREE="event";

    private final Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute() {
        try {
            taskList.addTask(toAdd);
            System.out.println("New "+toAdd.getTaskType()+" Added: "+toAdd.toString());
        } catch (TaskList.DuplicateTaskException e) {
            e.printStackTrace();
        }

    }


}
