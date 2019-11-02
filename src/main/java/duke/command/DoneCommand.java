package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;
import java.util.List;

public class DoneCommand implements Command {
    private final TaskList tasks;
    private Storage storage;

    public DoneCommand(TaskList tasks, Storage storage){
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     *Marks the task as done and returns the message to user.
     *
     * @param fullCommand array of command from the user input.
     * @return a message to user.
     * @throws NumberFormatException catch a error if the user input is not a number.
     * @throws IndexOutOfBoundsException catch a error if the input index is out of bound.
     * @throws IOException if the task cannot be recorded.
     */
    @Override
    public List<String> run(String[] fullCommand) throws NumberFormatException, IndexOutOfBoundsException, IOException {
        try {
            Task markItem = tasks.get(Integer.parseInt(fullCommand[1]) - 1);
            markItem.markAsDone();
            storage.store(tasks.ConvertAsLines());
            return List.of("    Nice! I've marked this task as done:" + System.lineSeparator() + "    " + markItem);
        }catch (NumberFormatException e){
            return List.of("    ☹ OOPS!!! This is not a number: " + fullCommand[1]);
        }catch (IndexOutOfBoundsException e){
            return List.of("    ☹ OOPS!!! The index out of bound: " + fullCommand[1]);
        }
    }
}
