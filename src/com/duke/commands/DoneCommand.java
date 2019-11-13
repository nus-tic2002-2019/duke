package com.duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * mark a task as done statement based on the index.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD="done";

    private LocalDateTime finishTime;
    public DoneCommand(int targetIndex,LocalDateTime finishTime) {
        super(targetIndex);
        this.finishTime=finishTime;
    }

    @Override
    public void execute(){
        taskList.getTaskByIdx(getTargetIndex()).markAsDone(finishTime);
        System.out.print( taskList.getTaskByIdx(getTargetIndex())+" is done on "+
                finishTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))+"\n");
    }
}
