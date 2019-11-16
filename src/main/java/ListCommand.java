import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command{
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_INDEXED_LIST_ITEM = "%1$d. %2$s";
    private static String output;

    public ListCommand(String input){
        super(input);
    }

    private String showTaskListView(TaskList taskList) {
        output = "";
        List<String> formattedTask = new ArrayList<>();
        for (int i=0;i<TaskList.getSize();i++) {
            formattedTask.add(TaskList.getTask(i).toString());
        }
        return showToUserAsIndexedList(formattedTask);
    }

    private String showToUserAsIndexedList(List<String> list) {
        return getIndexedListForViewing(list);
    }

    private static String getIndexedListForViewing(List<String> listItems) {
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        output += " Here are the tasks in your list:";
        for (String listItem : listItems) {
            output += " \n\t" + getIndexedListItem(displayIndex, listItem);
            displayIndex++;
        }
        return output;
    }

    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        if(TaskList.getSize() == 0){
            throw new DukeException("The tasks list cannot be empty.");
        }
        ui.showOutputToUser(showTaskListView(taskList));
    }
}