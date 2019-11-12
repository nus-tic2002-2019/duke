package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.data.task.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class Duke{

    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /** 
     * Constructs a new Duke and initialised with UI, TaskList and Storge.
     */
    public Duke(){
        ui = new UI();
        try{
            storage = new Storage("data/taskList.txt");
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke program and process the input from the user with corresponding output.
     */
    public void run(){
        boolean isExit = false;
        while (!isExit){
            try{
                String input = ui.readUserInput();
                Command command = Parser.parseInput(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit;
            } catch (Exception e){
                ui.showError(e);
            }
        }
    }
    
    /** 
     * Obtains the response of Duke with reference to the input and returns the output as text to the user.
     * @param input     The input given by the user.
     * @return String   The output response from Duke.
     */
    public String getResponse(String input){
        try{
            Command command = Parser.parseInput(input);
            command.execute(taskList, ui, storage);
            return ui.showOutputToUser();
        } catch (Exception e){
            return ui.showError(e);
        }
    }
    
    /** 
     * The main method to run Duke.
     * @param args  The argument values provided by the user to run Duke.
     */
    public static void main(String[] args){
        new Duke().run();
    }
}