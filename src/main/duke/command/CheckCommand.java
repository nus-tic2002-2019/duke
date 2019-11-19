package main.duke.command;

import main.duke.storage.Storage;
import main.duke.task.*;
import main.duke.ui.Ui;

import java.time.LocalDateTime;

public class CheckCommand extends Command {
    private LocalDateTime check_time;

    /**
     * Checks TaskList for tasks that have a date, and print them if they are on the same date.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printEventsOnDateMsg(check_time);
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getClass() == Event.class) {
                Event e = (Event) curr;
                if(e.getStart_endTime().toLocalDate().isEqual(check_time.toLocalDate())){
                    ui.printTask(e);
                }
            }
            if (curr.getClass() == Deadline.class) {
                Deadline d = (Deadline) curr;
                if(d.getDeadline().toLocalDate().isEqual(check_time.toLocalDate())){
                    ui.printTask(d);
                }
            }
        }
    }

    public CheckCommand(String time_str) {
        check_time = Parser.parseDateTimeStr(time_str);
    }
}
