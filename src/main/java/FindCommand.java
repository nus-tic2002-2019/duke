import java.util.List;
import java.util.ArrayList;


public class FindCommand extends Command{

    public static final String FailMessage = "There are no matching tasks in your list.";
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String ListItemMessage = "%1$d. %2$s";
    private static String output;

    public FindCommand(String input){
        super(input);
    }

    /**
     *  look for the key work from array list and show to user
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        if(input.split(" ")[1].trim().isEmpty()){
            throw new DukeException("The selector of a find cannot be empty.");
        }

        input = input.substring(5);
        boolean isMatch = false;
        List<String> matchedTasks = new ArrayList<>();
        output = "";

        for(int i=0;i<TaskList.getSize();i++){
            Task task = TaskList.getTask(i);
            if(task.description.contains(input)){
                matchedTasks.add(task.toString());
                isMatch = true;
            }
        }
        if(isMatch){
            ui.showOutputToUser(showUserIndexedList(matchedTasks));
        }
        else{
            ui.showOutputToUser(FailMessage);
        }
    }

    private String showUserIndexedList(List<String> list) {
        return getListForViewing(list);
    }

    private static String getListForViewing(List<String> listItems) {
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        output += " Here are the matching tasks in your list:";
        for (String listItem : listItems) {
            output += " \n\t" + getListItem(displayIndex, listItem);
            displayIndex++;
        }
        return output;
    }

    private static String getListItem(int visibleIndex, String listItem) {
        return String.format(ListItemMessage, visibleIndex, listItem);
    }
} 