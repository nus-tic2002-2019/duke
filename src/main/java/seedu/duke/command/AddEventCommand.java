package seedu.duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import seedu.duke.exception.DukeEmptyException;
import seedu.duke.exception.DukeException;
import seedu.duke.data.task.Event;
import seedu.duke.data.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class AddEventCommand extends Command{
    public static final String INPUT_WORD = "event";
    private static final String MESSAGE_ADD_EVENT_SUCCESS = "Got it. I've added this task:\n\t  ";
    private static final String MESSAGE_EMPTY_DATE = "The date of a event cannot be empty.";
    private Event event;

    /** 
     * Constructs a new AddEventCommand and initialise with the specified isExit boolean value and input by the user.
     * @param   isExit  A boolean value whether if the exit condition is true.
     * @param   input   A String inputted by the user.
     */
    public AddEventCommand(boolean isExit, String input){
        super(isExit, input);
    }
    
    /** 
     * Creates a new Event task and checks whether if the date or description is empty before creating.
     * @param   taskList            The array of tasks stored in an ArrayList.
     * @param   ui                  The User Interface (UI) to allow interaction with the user.
     * @param   storage             The storage to allow storing and reading of tasks from a file.
     * @throws  DukeEmptyException  If the user inputs an empty description.
     * @throws  IOException         If an input or output exception occurs.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeEmptyException, DukeException, IOException{
        if((input.substring(5).trim()).isEmpty() || input.split(" ")[1].trim().equals("at")){
            throw new DukeEmptyException("event");
        }
        if(!input.contains("at")){
            throw new DukeException(MESSAGE_EMPTY_DATE);
        }
        int index = 0;
        for(String word : input.split(" ")){
            ++index;
            if(word.equals("at")){
                if(String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length)).trim().isEmpty()){
                    throw new DukeEmptyException(MESSAGE_EMPTY_DATE);
                } else {
                    String description = String.join(" ", Arrays.copyOfRange(input.split(" "), 1, index-1));
                    String date = String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length));
                    event = new Event (description, stringToDate(date));
                    taskList.addToTaskList(event);
                    assert taskList.getSize() > 0;
                    ui.setOutput(MESSAGE_ADD_EVENT_SUCCESS + event.toString() + "\n\t Now you have " + taskList.getSize() + " tasks in the list.");
                    storage.saveToFile();
                }
            }
        }
    }

    /** 
     * Converts the date & time inputted by the user to a LocalDateTime format of (d/MM/yyyy HHmm).
     * @param   date            The date inputted by the user.
     * @return  LocalDateTime   The converted date & time in a LocalDateTime format from the String date.
     * @throws  DukeException   If the input by the user is not of the format (dd/mm/yyyy HHmm) or the input has an invalid date.
     */
    private LocalDateTime stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e){
            throw new DukeException("The format of the date and time must be in this format: dd/mm/yyyy hhss");
        }
    }
}