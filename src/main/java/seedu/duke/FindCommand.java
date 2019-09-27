import java.util.List;
import java.util.ArrayList;

public class FindCommand extends Command{

    public static final String INPUT_WORD = "find";
    public static final String MESSAGE_FIND_FAILURE = "There are no matching tasks in your list.";
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_INDEXED_LIST_ITEM = "%1$d. %2$s";
    private static String output;

    public FindCommand(boolean isExit, String input){
        super(isExit, input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        if(input.split(" ")[1].trim().isEmpty()){
            throw new DukeException("The selector of a find cannot be empty.");
        }

        input = input.substring(5);
        boolean isMatch = false;
        List<String> matchedTasks = new ArrayList<>();
        int counter = 1;
        output = "";

        for(int i=0;i<taskList.getSize();i++){
            Task task = taskList.getTask(i);
            if(task.description.contains(input)){
                matchedTasks.add(task.toString());
                isMatch = true;
            }
        }
        if(isMatch){
            ui.showOutputToUser(showToUserAsIndexedList(matchedTasks));
        }
        else{
            ui.showOutputToUser(MESSAGE_FIND_FAILURE);
        }
    }

    private String showToUserAsIndexedList(List<String> list) {
        return getIndexedListForViewing(list);
    }

    private static String getIndexedListForViewing(List<String> listItems) {
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        output += " Here are the matching tasks in your list:";
        for (String listItem : listItems) {
            output += " \n\t" + getIndexedListItem(displayIndex, listItem);
            displayIndex++;
        }
        return output;
    }

    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}