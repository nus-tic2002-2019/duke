/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
;

/**
 *
 * @author lug3g
 */
public class AddCommand extends Command {
    public AddCommand(String commandName){
        super(commandName);
    }
    
    public boolean isExit(){
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if ( getCommand().equals("todo") ){
            tasks.getTaskList().add(new Todo( ui.getLine().replaceFirst("todo ", "")));
            ui.addResponse(tasks.getTask(tasks.getSize()-1).printTask(),tasks.getSize());
        }
        
        if ( getCommand().equals("event") ){
            try {
                String words[] = ui.getLine().replaceFirst("event ", "").split(" /at ",2);
                ui.convertDate(words[1]);
                tasks.getTaskList().add(new Event(words[0],words[1]));
                ui.addResponse(tasks.getTask(tasks.getSize()-1).printTask(),tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e){
                ui.response("☹ OOPS!!! Event description needs to include a '/at' followed by the date.");
            } catch (ParseException e) {
                ui.response("☹ OOPS!!! Please include a valid date description after '/at'. (yyyy-mm-dd)");
            } catch (DukeException e){
                ui.response("☹ OOPS!!! Please include a date description after '/at'.");
            }
        }
        
        if ( getCommand().equals("deadline") ){
            try {
                String words[] = ui.getLine().replaceFirst("deadline ", "").split(" /by ",2);
                ui.convertDate(words[1]);
                tasks.getTaskList().add(new Deadline(words[0],words[1]));
                ui.addResponse(tasks.getTask(tasks.getSize()-1).printTask(),tasks.getSize());    
            } catch (ArrayIndexOutOfBoundsException e){
                ui.response("☹ OOPS!!! Deadline description needs to include a '/by' followed by the date.)");
            } catch (ParseException e) {
                ui.response("☹ OOPS!!! Please include a valid date description after '/by'. (yyyy-mm-dd");
            } catch (DukeException e){
                ui.response("☹ OOPS!!! Please include a date description after '/by'.");
            }
        }
        storage.save(tasks);
    }
}
