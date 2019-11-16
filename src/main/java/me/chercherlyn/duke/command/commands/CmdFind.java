package me.chercherlyn.duke.command.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.task.Task;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

public class CmdFind extends Cmd {
    /**
     * Creates Cmd instance.
     *
     * @param ui      user interface
     * @param tasks   tasks
     * @param storage storage
     */
    public CmdFind(Ui ui, TaskList tasks, Storage storage) {
        super(ui, tasks, storage);
    }
    
    @Override
    public void execute(String[] args) {
        // check : keywords for every search
        if (args.length < 1 || args[0].isEmpty())
            throw new DukeException("Keywords not specified!");
    
        // check : keywords for regex search
        boolean regexSearch = args[0].equals("-r");
        if (regexSearch && args.length < 2)
            throw new DukeException("Keywords not specified for regex!");
        
        // process
        List<String> found = regexSearch
                ? findRegex(String.join(" ", Arrays.copyOfRange(args, 1, args.length)))
                : findSimple(String.join(" ", args));
        
        if (found.isEmpty()) {
            ui.printFancy("No tasks matches your query!");
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Here are the matching tasks in your list:\n");
            for (String foundLine : found)
                builder.append(foundLine).append("\n");
            builder.deleteCharAt(builder.length() - 1);
            ui.printFancy(builder.toString());
        }
    }
    
    private List<String> findSimple(String input) {
        input = input.toLowerCase();
        List<String> found = new ArrayList<>();
    
        // find
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription().toLowerCase();
            if (description.contains(input))
                found.add((i + 1) + ". " + task.toString());
        }
        
        return found;
    }
    
    private List<String> findRegex(String input) {
        List<String> found = new ArrayList<>();
        
        // create pattern
        Pattern pattern;
        try {
            pattern = Pattern.compile(input, Pattern.CASE_INSENSITIVE);
        } catch (Exception ex) {
            throw new DukeException("Invalid search query. Check regex syntax!");
        }
        
        // find
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String info = task.toString();
            if (pattern.matcher(info).find())
                found.add((i + 1) + ". " + info);
        }
    
        return found;
    }
}
