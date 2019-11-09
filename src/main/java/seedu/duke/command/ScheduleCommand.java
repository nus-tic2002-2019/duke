package seedu.duke.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.data.task.Task;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;


public class ScheduleCommand extends Command{
    public static final String INPUT_WORD = "schedule";
    public static final String MESSAGE_SCHEDULE_FAILURE = "There are no matching dates in your list.";
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String MESSAGE_INDEXED_LIST_ITEM = "%1$d. %2$s";
    private static String output;

    /** 
     * Constructs a new ScheduleCommand and initialise with the specified isExit boolean value and input by the user.
     * @param   isExit  A boolean value whether if the exit condition is true.
     * @param   input   A String inputted by the user.
     */
    public ScheduleCommand(boolean isExit, String input){
        super(isExit, input);
    }
    
    /** 
     * Searches for the date inputted by the user and returns the search results in a listed format.
     * @param   taskList        The array of tasks stored in an ArrayList.
     * @param   ui              The User Interface (UI) to allow interaction with the user.
     * @param   storage         The storage to allow storing and reading of tasks from a file.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        LocalDate date;

        if(input.substring(8).trim().isEmpty()){
            throw new DukeException("The keyword of a find cannot be empty.");
        }
        if(taskList.getSize() == 0){
            throw new DukeException("The tasks list cannot be empty.");
        }
        
        input = input.substring(9);
        date = stringToDate(input);
        boolean isMatch = false;
        List<String> matchedTasks = new ArrayList<>();
        output = "";

        for(int i=0;i<taskList.getSize();i++){
            Task task = taskList.getTask(i);
            if(task.getDateTime().toLocalDate().equals(date)){
                matchedTasks.add(task.toString());
                isMatch = true;
            }
        }
        if(isMatch){
            Collections.sort(matchedTasks);
            ui.setOutput(showToUserAsIndexedList(matchedTasks));
        } else{
            ui.setOutput(MESSAGE_SCHEDULE_FAILURE);
        }
    }

    /** 
     * Formats the list of tasks and return them in a formatted indexed list.
     * @param listItems A list of tasks from the task list.
     * @return String   A formatted String in indexed format containing the tasks from the task list.
     */
    private static String showToUserAsIndexedList(List<String> listItems){
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        output += " Here are the matching tasks schedule in your list:";
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

    /** 
     * Converts the date & time inputted by the user to a LocalDateTime format of (d/MM/yyyy HHmm).
     * @param   date            The date inputted by the user.
     * @return  LocalDateTime   The converted date & time in a LocalDateTime format from the String date.
     * @throws  DukeException   If the input by the user is not of the format (dd/mm/yyyy HHmm) or the input has an invalid date.
     */
    private LocalDate stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e){
            throw new DukeException("The format of the date and time must be in this format: dd/mm/yyyy (13/08/2019)");
        }
    }
}