/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke;

import static duke.Duke.exit;
import java.util.*;
/**
 *
 * @author lug3g
 */
public class DukeEnum {
    public enum DukeCommand{
        
        LIST(1), TODO(2), EVENT(3), DEADLINE(4), DONE(5), DELETE(6), BYE(7);
        private int commandValue;
        private static Map map = new HashMap<>();
        private String line = "";
        private String words[];
        private ArrayList<Task> Tasks = new ArrayList <>();
        
        private DukeCommand(int commandValue){
            this.commandValue = commandValue;
        }
        
        static {
            for ( DukeCommand command : DukeCommand.values() ){
                map.put(command.commandValue, command);
            }
        }
        
        public static void printCommands(){
            System.out.println("\tHere are the list of commands. Please enter the command index to send the command");
            for ( DukeCommand command : DukeCommand.values() ){
                System.out.printf("\t\t[%d] %s\n", command.commandValue, command.name());
            }
            System.out.println("\tPlease enter your command: ");
        }
        
        public static DukeCommand valueOf(int commandValue) {
            return (DukeCommand) map.get(commandValue);
        }

        public int getValue() {
            return commandValue;
        }
        
        public static void executeCommand(int commandValue){
            
        }
    }   
}
