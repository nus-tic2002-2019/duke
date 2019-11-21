/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.commands;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import java.util.Arrays;

/**
 * Parser class interprets what the user wants the program to do.
 * @author lug3g
 */
public class Parser{
    /** 
     * interprets command and send the command to Command class for execution
     * @param fullCommand takes in full text from the user and interprets it for execution
     * @return the command for execution
     */
    public static Command parse(String fullCommand){
        String c = fullCommand.split(" ",0)[0].toLowerCase();
        
        if ( checkCommandError(fullCommand) || checkMissingDescriptionError(fullCommand) || containForbiddenChar(fullCommand) ){
            return new Command("Error") {
                @Override
                public void execute(TaskList tasks, Ui ui, Storage storage) {
                    
                }
                @Override
                public boolean isExit() {
                    return false;
                }
            };
        }
                
        if ( c.equals("todo") | c.equals("event") | c.equals("deadline") ){
            return new AddCommand(c);
        }
        
        if ( c.equals("bye") ){
            return new ExitCommand(c);
        }
        
        if ( c.equals("done") | c.equals("undone")){
            return new DoneCommand(c);
        }
        
        if ( c.equals("delete") ){
            return new DeleteCommand(c);
        }
        
        if ( c.equals("list") | c.equals("listsameday") | c.equals("find") ){
            return new ListCommand(c);
        }
        
        if ( c.equals("setpriority") ){
            return new SetCommand(c);
        }
        return null;
    }
    
    private static boolean checkCommandError(String line){
        String [] commands = {"bye","done","list","todo","event","deadline","delete","listsameday","find","undone","setpriority"};
        String [] words = line.split(" ",0);
        
        try {
            if ( !Arrays.asList(commands).contains( words[0].toLowerCase() ) ) {
                throw new DukeException();
            }
            return false;
        } catch ( DukeException e ) {
            Ui.response("☹ OOPS!!! I'm sorry, but I don't know what " + words[0] + " means.");
            return true;
        }
    }
    
    private static boolean checkMissingDescriptionError(String line){
        String [] missingDescription = {"todo","event","deadline","done","delete","listsameday","find","undone","setpriority"};
        String [] words = line.split(" ",0);
        
        try {
            if ( Arrays.asList(missingDescription).contains( line.toLowerCase() )){
                throw new DukeException();
            }
            return false;
        } catch ( DukeException e ){
            if ( line != "done" && line != "delete" ){
                Ui.response("☹ OOPS!!! The description of a " + line + " cannot be empty.");
            } else {
                Ui.response("☹ OOPS!!! Please indicated which item you wish to set to " + line + ". You can use the list command to display the items in the list.");
            }
            return true;
        }
    }
    
    private static boolean containForbiddenChar(String line){
        String [] words = line.split(" ",0);
        
        try {
            if ( line.contains("|") ){
                throw new DukeException();
            }
            return false;
        } catch ( DukeException e ){
            Ui.response("☹ OOPS!!! You cannot input the '|' character as it is not allowed in Duke.");
            return true;
        }
    }
}
