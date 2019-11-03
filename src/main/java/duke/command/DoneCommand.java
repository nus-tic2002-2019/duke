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
        String indexNumber = fullCommand[1];
        try {

            if (indexNumber.contains(",")) {
                String[] indexList = indexNumber.split(",");
                //int j = 1;
                for (int i = 0; i <= indexList.length; i++) {

                    System.out.println(Integer.parseInt(indexList[i]) - 1);
                    //Task deleteItem = tasks.remove(Integer.parseInt(indexList[i]) - j);
                    int k = Integer.parseInt(indexList[i]);
                    //tasks.remove(Integer.parseInt(indexList[i]) - j);
                    //tasks.remove(k - j);
                    Task markItem = tasks.get(k - 1);
                    markItem.markAsDone();
                    storage.store(tasks.ConvertAsLines());

                    //j++;
                }
            } else if (indexNumber.contains("-")){
                String[] indexList = indexNumber.split("-");
                int firstIndex = Integer.parseInt(indexList[0]);
                int lastIndex = Integer.parseInt(indexList[1]);
                //System.out.println(firstIndex);
                //System.out.println(lastIndex);
                //int j = 1;
                for (int i = firstIndex; i <= lastIndex; i++){
                    Task markItem = tasks.get(i - 1);
                    markItem.markAsDone();
                    //tasks.remove(i - j);
                    storage.store(tasks.ConvertAsLines());
                    //j++;
                }
            }else {
                Task markItem = tasks.get(Integer.parseInt(fullCommand[1]) - 1);
                markItem.markAsDone();
                storage.store(tasks.ConvertAsLines());
            }
            return List.of("    Nice! I've marked this task as done: " + System.lineSeparator() + "    " + fullCommand[1]);
        }catch (NumberFormatException e){
            return List.of("    ☹ OOPS!!! This is not a number: " + fullCommand[1]);
        } catch (IndexOutOfBoundsException e){
            return List.of("    ☹ OOPS!!! The index out of bound: " + fullCommand[1]);
        }


/*
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

 */



    }
}
