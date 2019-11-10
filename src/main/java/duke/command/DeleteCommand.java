package duke.command;

import duke.Storage;
import duke.task.TaskList;
import java.io.IOException;
import java.util.List;

public class DeleteCommand implements Command {
    private final TaskList tasks;
    private Storage storage;

    public DeleteCommand(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Remove a task according to the index input by the user.
     *
     * @param fullCommand array of command from the user input.
     * @return message to the user.
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
                int j = 1;
                for (int i = 0; i < indexList.length; i++) {
                    int k = Integer.parseInt(indexList[i]);
                    tasks.remove(k - j);
                    storage.store(tasks.ConvertAsLines());
                    j++;
                }
            } else if (indexNumber.contains("-")) {
                String[] indexList = indexNumber.split("-");
                int firstIndex = Integer.parseInt(indexList[0]);
                int lastIndex = Integer.parseInt(indexList[1]);
                int j = 1;
                for (int i = firstIndex; i <= lastIndex; i++){
                    tasks.remove(i - j);
                    storage.store(tasks.ConvertAsLines());
                    j++;
                }
            } else {
                tasks.remove(Integer.parseInt(fullCommand[1]) - 1);
                storage.store(tasks.ConvertAsLines());
            }
            return List.of("    Noted. I've removed the task item: " + System.lineSeparator() + "    " + fullCommand[1]);
        } catch (NumberFormatException e) {
            return List.of("    ☹ OOPS!!! This is not a number: " + fullCommand[1]);
        } catch (IndexOutOfBoundsException e) {
            return List.of("    ☹ OOPS!!! The index out of bound: " + fullCommand[1]);
        }
    }
}
