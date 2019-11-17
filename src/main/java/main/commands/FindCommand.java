package main.commands;

import main.DukeException;
import main.UI;
import main.taskLists.Task;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static main.Duke.Tasks;

public class FindCommand extends Command {


    private static final String INPUT_DELIMITER = " ";

    public FindCommand(Object input) throws IOException, DukeException {
        execute(input);
    }

    private String[] parsesearch(Object input) {
        String searchstring = input.toString();
        String[] searchable = searchstring.split(INPUT_DELIMITER);
        return searchable;
    }

    private boolean searchMatch(String searchval, String[] searchable) {
        for (String search : searchable) {
            if (searchval.equals(search))
                return true;
        }
        return false;
    }


    @Override
    public void execute(Object input) throws DukeException, IOException {
        Set<Task> seached_set = new HashSet<Task>();
        for (Task task : Tasks) {
            for (String searchval : task.getDescription().split(INPUT_DELIMITER)) {
                if (searchMatch(searchval, parsesearch(input))) {
                    seached_set.add(task);
                }
            }
        }

        UI.listTasks(seached_set);
    }
}
