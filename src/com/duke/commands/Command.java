package com.duke.commands;

import com.duke.task.TaskList;

public class Command {

    protected TaskList taskList;

    private int targetIndex = -1;

    protected Command() {
    }

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    private void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
    public int getTargetIndex() {
        return targetIndex;
    }

    public void execute() {
    }

    public void setData(TaskList taskList){
        this.taskList=taskList;
    }


}
