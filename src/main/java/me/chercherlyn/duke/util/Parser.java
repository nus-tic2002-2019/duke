package me.chercherlyn.duke.util;

import java.util.HashMap;
import java.util.Map;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.command.commands.*;

/**
 * Represents a parser to parse and execute commands from given user input.
 */
public class Parser {
    
    private Ui ui;
    private Map<String, Cmd> commandMap;
    
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.commandMap = new HashMap<>();
        
        this.commandMap.put("bye", new CmdBye(ui, tasks, storage));
        this.commandMap.put("deadline", new CmdDeadline(ui, tasks, storage));
        this.commandMap.put("delete", new CmdDelete(ui, tasks, storage));
        this.commandMap.put("done", new CmdDone(ui, tasks, storage));
        this.commandMap.put("event", new CmdEvent(ui, tasks, storage));
        this.commandMap.put("list", new CmdList(ui, tasks, storage));
        this.commandMap.put("todo", new CmdTodo(ui, tasks, storage));
    }
    
    /**
     * Parse and execute command from given user input.
     *
     * @param input user input line
     */
    public void processCommand(String input) {
        // split input on 2 parts by first space
        // ex.: "todo something hmm" -> ["todo", "something hmm"]
        String[] data = input.split("\\s+", 2);
    
        // get command & args
        // if input length > 1, then args = input[1].split("\\s+")
        // else args = new String[0]
        String command = data[0].toLowerCase();
        String[] args = data.length > 1
                ? data[1].split("\\s+")
                : new String[0];
        
        // get and call command
        try {
            Cmd cmd = commandMap.get(command);
            if (cmd == null)
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            cmd.execute(args);
        } catch (Exception ex) {
            ui.printFancy("\u2639 OOPS!!! %s", ex.getMessage());
        }
    }
}
