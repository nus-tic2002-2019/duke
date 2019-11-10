package duke.command;

import duke.task.TaskList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewSchedulesCommand implements Command {

    private final TaskList tasks;

    public ViewSchedulesCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * To view a schedule according to the input date.
     *
     * @param fullCommand array of command from the user input.
     * @return a list of tasks to match the searching schedule.
     */
    @Override
    public List<String> run(String[] fullCommand) {
        String strDate = String.join(" ", Arrays.copyOfRange(fullCommand, 1, fullCommand.length));
        List<String> schedule = new ArrayList<>();
        schedule.add("    Here are the task list in your searching schedule: ");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(strDate)){
                count++;
                schedule.add("    " + count + "." + tasks.get(i));
            }
        }
        return schedule;
    }
}
