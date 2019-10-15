package seedu.duke.command;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.data.task.Task;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;


public class FindCommand extends Command{
    public static final String INPUT_WORD = "find";
    public static final String MESSAGE_FIND_FAILURE = "There are no matching tasks in your list.";
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_INDEXED_LIST_ITEM = "%1$d. %2$s";
    private static String output;

    /** 
     * Constructs a new FindCommand and initialise with the specified isExit boolean value and input by the user.
     * @param   isExit  A boolean value whether if the exit condition is true.
     * @param   input   A String inputted by the user.
     */
    public FindCommand(boolean isExit, String input){
        super(isExit, input);
    }
    
    /** 
     * Searches for the keyword inputted by the user and returns the search results.
     * @param   taskList        The array of tasks stored in an ArrayList.
     * @param   ui              The User Interface (UI) to allow interaction with the user.
     * @param   storage         The storage to allow storing and reading of tasks from a file.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        if(input.split(" ")[1].trim().isEmpty()){
            throw new DukeException("The keyword of a find cannot be empty.");
        }
        if(taskList.getSize() == 0){
            throw new DukeException("The tasks list cannot be empty.");
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
            ui.setOutput(showToUserAsIndexedList(matchedTasks));
        }
        else{
            ui.setOutput(MESSAGE_FIND_FAILURE);
        }
    }

    /** 
     * Formats the list of tasks and return them in a formatted indexed list.
     * @param listItems A list of tasks from the task list.
     * @return String   A formatted String in indexed format containing the tasks from the task list.
     */
    private static String showToUserAsIndexedList(List<String> listItems){
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        output += " Here are the matching tasks in your list:";
        for (String listItem : listItems) {
            output += " \n\t" + getIndexedListItem(displayIndex, listItem);
            displayIndex++;
        }
        return output;
    }
    
    /** 
     * Returns the items in an indexed format with the relevant task.
     * @param visibleIndex  The index of the item in the list.
     * @param listItem      The task to be indexed.
     * @return String       A formatted indexed tasks in String format.
     */
    private static String getIndexedListItem(int visibleIndex, String listItem){
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}