package com.duke.commands;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD="delete";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(){
        taskList.removeTask(getTargetIndex()-1);
    }
}
