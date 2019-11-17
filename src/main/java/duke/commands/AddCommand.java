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

    public static final String MESSAGE_USAGE= "||"+COMMAND_WORD_ONE+": Adds a Todo Task to the task list. \n" +
                                            "Syntax: todo DESCRIPTION\n" +
                                            "Example: " + COMMAND_WORD_ONE +" borrow book\n" +
                                            "||"+ COMMAND_WORD_TWO+": Adds a Deadline task to the task list.\n" +
                                            "Syntax: deadline DESCRIPTION by/TIME(YYYY-MM-dd HHmm)\n" +
                                            "Example: "+COMMAND_WORD_TWO +" return book by/2019-12-01 1200\n" +
                                             "||"+ COMMAND_WORD_THREE+": Adds an Event task to the task list.\n" +
                                            "Syntax: event DESCRIPTION at/TIME(YYYY-MM-dd HHmm)\n" +
                                            "Example: "+ COMMAND_WORD_THREE+" project meeting at/2019-12-01 1200";

    private final Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute() {
        try {
            taskList.addTask(toAdd);
            System.out.println("New "+toAdd.getTaskType()+" Added: "+toAdd.toString());
            System.out.println("Now you have "+taskList.getSize()+" tasks in the list.");
        } catch (TaskList.DuplicateTaskException e) {
            e.printStackTrace();
        }

    }


}
