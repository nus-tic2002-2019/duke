package command; /**
 * Constructs a new AddEventCommand and initialise with isExit boolean value
 */

import exceptions.DukeEmptyException;
import exceptions.DukeException;
import task.Event;
import task.Storage;
import task.TaskList;
import ui.UI;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class AddEventCommand extends Command {
    public static final String INPUT_WORD = "event";
    private Event event;

    public AddEventCommand(boolean isExit, String input){
        super(isExit, input);
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeEmptyException, DukeException, IOException{

        if((input.substring(5).trim()).isEmpty()){
            throw new DukeEmptyException("event");
        }
        if(!input.contains("at")){
            throw new DukeException("The date of a event cannot be empty.");
        }
        int index = 0;
        for (String word : input.split(" ")){
            ++index;
            if(word.equals("at")){
                if(String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length)).isEmpty()){
                    throw new DukeException("The date of a event cannot be empty.");
                }
                else {
                    //event = new Event (String.join(" ", Arrays.copyOfRange(input.split(" "), 1, index-1)), String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length)));
                    String description = String.join(" ", Arrays.copyOfRange(input.split(" "), 1, index-1));
                    String date = String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length));
                    event = new Event(description, stringToDate(date));
                    taskList.addToTaskList(event);
                    ui.showOutputToUser("Got it. I've added this task:\n\t " + event.toString() + "\n\t Now you have " + taskList.getSize() + " task in the list.");
                    storage.saveToFile();
                }
            }
        }
    }

    private LocalDateTime stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        }
        catch (DateTimeParseException e){
            throw new DukeException("The format of the date and time must be in this format: dd/mm/yyyy hhss");
        }
    }
}