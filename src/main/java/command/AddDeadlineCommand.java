package command;

import command.Command;
import exceptions.DukeEmptyException;
import exceptions.DukeException;
import task.Deadline;
import task.Storage;
import task.TaskList;
import ui.UI;

import java.io.IOException;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class AddDeadlineCommand extends Command {
    public static final String INPUT_WORD = "deadline";
    private Deadline deadline;

    public AddDeadlineCommand(boolean isExit, String input){
        super(isExit, input);
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeEmptyException, DukeException, IOException{
        int index = 0;
        if((input.substring(8).trim()).isEmpty()){
            throw new DukeEmptyException("deadline");
        }
        if(!input.contains("by")){
            throw new DukeException("The date of a deadline cannot be empty.");
        }
        for (String word : input.split(" ")){
            ++index;
            if(word.equals("by")){
                if(String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length)).isEmpty()){
                    throw new DukeException("The date of a deadline cannot be empty.");
                }
                else {
                    //deadline = new Deadline(String.join(" ", Arrays.copyOfRange(input.split(" "), 1, index-1)), String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length)));
                    String description = String.join(" ", Arrays.copyOfRange(input.split(" "), 1, index-1));
                    String date = String.join(" ", Arrays.copyOfRange(input.split(" "), index, input.split(" ").length));
                    deadline = new Deadline(description, stringToDate(date));
                    taskList.addToTaskList(deadline);
                    ui.showOutputToUser("Got it. I've added this task:\n\t " + deadline.toString() + "\n\t Now you have " + taskList.getSize() + " task in the list.");
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
        catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time must be in this format: dd/mm/yyyy hhss");
        }
    }
}
