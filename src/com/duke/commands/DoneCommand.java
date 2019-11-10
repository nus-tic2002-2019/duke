package com.duke.commands;

/**
 * mark a task as done statement based on the index.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD="done";

    public DoneCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(){
        taskList.getTaskByIdx(getTargetIndex()).markAsDone();
        System.out.print( taskList.getTaskByIdx(getTargetIndex())+" is done.\n");
    }
}
