package com.duke.commands;

import com.duke.storage.Storage;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.ui.Ui;

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
        taskList.addTask(toAdd);
        System.out.println("New "+toAdd.getTaskType()+" Added: "+toAdd.toString());
    }


}
