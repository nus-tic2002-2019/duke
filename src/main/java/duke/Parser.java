package duke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import duke.command.Command;

public class Parser {

    static void CheckWord(String keyword)throws DukeCheckLineException{

        if (keyword == null){
            throw new DukeCheckLineException();
        }
    }
    private Map<String, Command> commands = new HashMap<>();

    void capture(String name, Command command){
        commands.put(name, command);
    }

    Command parse(String[] fullCommand){
        Command command = commands.get(fullCommand[0]);

        try{
            CheckWord(fullCommand[0]);
        }catch (DukeCheckLineException e){
            return (Command) List.of("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
