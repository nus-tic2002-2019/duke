package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import java.text.ParseException;
import java.util.Arrays;
import duke.priority.Priority;
;

/**
 * AddCommand class is an extension of Command class that
 * processes and executes commands that add new tasks
 * to Duke's array list.
 * @author Guan Hao
 */
public class AddCommand extends Command {
    /**
     * Constructor method for AddCommand class
     * @param commandName user's command into the system
     * Inherits commandName from superclass Command
     */
    public AddCommand(String commandName){
        super(commandName);
    }
    /**
     * Override isExit method in Command class
     * Method to determine if user had choose to command to exit the system
     * AddCommand will only return false as adding Tasks does not terminate the
     * program.
     * @return 
     * returns
     * true when exiting program
     * false when processing other commands
     */
    @Override
    public boolean isExit(){
        return false;
    }
    /**
     * Override execute method in Command class
     * Execute user's command after processing 
     * and understanding user's command 
     * @param tasks Task list in duke
     * @param ui User interaction class
     * @param storage Storage interaction, able to save and load the data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String line = ui.getLine();
        String PriorityArray[] = {"LOW","MEDIUM","HIGH"};
        Ui.response("Please insert priority level of task. (LOW, MEDIUM, HIGH)");
        String p = ui.readCommand().toUpperCase();
        assert (p.equals("LOW") || p.equals("MEDIUM") || p.equals("HIGH")) : 
                "Priority only has low, medium and high values";
        try {
            if ( !Arrays.asList(PriorityArray).contains(p.toUpperCase()) ){
                throw new DukeException();
            }
        } catch (DukeException e){
            Ui.response("☹ OOPS!!! Please enter a value in the Priority.");
            return;
        }
        
        Priority taskPriority = Priority.valueOf(p);
        if ( getCommand().equals("todo") ){
            tasks.getTaskList().add(new Todo( line.replaceFirst("todo ", ""),taskPriority));
            Ui.addResponse(tasks.getTask(tasks.getSize()-1).printTask(),tasks.getSize());
        }
        
        if ( getCommand().equals("event") ){
            try {
                String words[] = line.replaceFirst("event ", "").split(" /at ",2);
                Ui.convertDate(words[1]);
                tasks.getTaskList().add(new Event(words[0],taskPriority,words[1]));
                Ui.addResponse(tasks.getTask(tasks.getSize()-1).printTask(),tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e){
                Ui.response("☹ OOPS!!! Event description needs to include a '/at' followed by the date.");
            } catch (ParseException e) {
                Ui.response("☹ OOPS!!! Please include a valid date description after '/at'. (yyyy-mm-dd)");
            } catch (DukeException e){
                Ui.response("☹ OOPS!!! Please include a date description after '/at'.");
            }
        }
        
        if ( getCommand().equals("deadline") ){
            try {
                String words[] = line.replaceFirst("deadline ", "").split(" /by ",2);
                Ui.convertDate(words[1]);
                tasks.getTaskList().add(new Deadline(words[0],taskPriority,words[1]));
                Ui.addResponse(tasks.getTask(tasks.getSize()-1).printTask(),tasks.getSize());    
            } catch (ArrayIndexOutOfBoundsException e){
                Ui.response("☹ OOPS!!! Deadline description needs to include a '/by' followed by the date.)");
            } catch (ParseException e) {
                Ui.response("☹ OOPS!!! Please include a valid date description after '/by'. (yyyy-mm-dd");
            } catch (DukeException e){
                Ui.response("☹ OOPS!!! Please include a date description after '/by'.");
            }
        }
        storage.save(tasks);
    }
}
