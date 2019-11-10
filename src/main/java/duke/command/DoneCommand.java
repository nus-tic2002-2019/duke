package duke.command;

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
        String indexNumber = fullCommand[1];
        try {
            if (indexNumber.contains(",")) {
                String[] indexList = indexNumber.split(",");
                for (int i = 0; i < indexList.length; i++) {
                    int k = Integer.parseInt(indexList[i]);
                    assert k >= 1:"The task number must be greater or equal to 1.";
                    Task markItem = tasks.get(k - 1);
                    markItem.markAsDone();
                    storage.store(tasks.ConvertAsLines());
                }
            } else if (indexNumber.contains("-")) {
                String[] indexList = indexNumber.split("-");
                int firstIndex = Integer.parseInt(indexList[0]);
                int lastIndex = Integer.parseInt(indexList[1]);
                if (firstIndex > lastIndex) {
                    for (int i = lastIndex; i <= firstIndex; i++) {
                        Task markItem = tasks.get(i - 1);
                        markItem.markAsDone();
                        storage.store(tasks.ConvertAsLines());
                    }
                } else {
                    for (int i = firstIndex; i <= lastIndex; i++) {
                        Task markItem = tasks.get(i - 1);
                        markItem.markAsDone();
                        storage.store(tasks.ConvertAsLines());
                    }
                }
            } else {
                int k = Integer.parseInt(fullCommand[1]);
                assert k >= 1:"The task number must be greater or equal to 1.";
                Task markItem = tasks.get(Integer.parseInt(fullCommand[1]) - 1);
                markItem.markAsDone();
                storage.store(tasks.ConvertAsLines());
            }
            return List.of("    Nice! I've marked this task as done: " + System.lineSeparator() + "    " + fullCommand[1]);
        } catch (NumberFormatException e) {
            return List.of("    ☹ OOPS!!! This is not a number: " + fullCommand[1]);
        } catch (IndexOutOfBoundsException e) {
            return List.of("    ☹ OOPS!!! The index out of bound: " + fullCommand[1]);
        }
    }
}
