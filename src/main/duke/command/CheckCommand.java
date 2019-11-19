package main.duke.command;

import main.duke.storage.Storage;
import main.duke.task.Event;
import main.duke.task.Parser;
import main.duke.task.Task;
import main.duke.task.TaskList;
import main.duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class CheckCommand extends Command {
    private LocalDateTime check_time;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getClass() == Event.class) {
                System.out.println("found event.");
            }
        }
    }

    public CheckCommand(String time_str) {
        check_time = Parser.parseDateTimeStr(time_str);
    }
}
