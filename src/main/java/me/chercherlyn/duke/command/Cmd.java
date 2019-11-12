package me.chercherlyn.duke.command;

import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Represents a command.
 */
public abstract class Cmd {
    
    protected Ui ui;
    protected TaskList tasks;
    protected Storage storage;
    
    /**
     * Creates Cmd instance.
     *
     * @param ui user interface
     * @param tasks tasks
     * @param storage storage
     */
    public Cmd(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }
    
    /**
     * Execute command with given args.
     *
     * @param args user arguments after command name
     */
    public abstract void execute(String[] args);
}
