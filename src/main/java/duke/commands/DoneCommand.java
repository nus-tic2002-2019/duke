package main.java.duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * mark a task as done statement based on the index.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD="done";

    public static final String MESSAGE_USAGE="||"+COMMAND_WORD+": mark a task as done status, there are two syntax.\n" +
            "Syntax 1(without finish time): done INDEX\n" +
            "Example: "+COMMAND_WORD +"2 (this command will mark the No.2 task as Done status and the default finish time is the current time)\n" +
            "Syntax 2(with finish time): done INDEX on/TIME(YYYY-MM-dd HHmm)\n" +
            "Example: "+COMMAND_WORD+" 2 on/2019-12-01 1200(this command will mark the No.2 task as Done status and finish time is 01 Dec 2019 12:00";
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
