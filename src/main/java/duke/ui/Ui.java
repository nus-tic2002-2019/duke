package duke.ui;

import duke.parser.Parser;
import duke.tasklist.*;
import duke.exception.DukeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.storage.Storage;

/**
 * The Ui class handles the user interaction
 */
public class Ui {
    public static Scanner textInput;
    /**
     * The Ui constructor create a new Scanner object to receive the user input
     */
    public Ui(){
        textInput = new Scanner(System.in);
    }

    /**
     * The showLine method creates a line at the start and end of Duke's reply
     */
    public void showLine(){
        System.out.println("    _____________________________________________________________________________________________________________");
    }
    /**
     * The showWelcome method shows the logo of Duke
     */
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeGreet();
    }
    /**
     * The dukeGreet method is Duke's greeting. This is a private method which is only used within Ui class
     */
    private void dukeGreet(){

        System.out.println ("    Hello! I'm Duke");
        System.out.println ("    What can I do for you?");

    }
    /**
     * The dukeBye method is Duke's final message before ending the program
     */
    public void dukeBye(){

        System.out.println("     Bye. Hope to see you again soon!");

    }
    /**
     * The showLoadingError method simply provide a message to indicate that there is an issue with loading from the file
     */
    public void showLoadingError(){

        System.out.println ("    File not located");

    }
    //probably not needed. Need to review.
    public void showError(String errorMsg){
        System.out.println(errorMsg);
    }
    /**
     * The dukeReply method provides a way for Duke to reply with details of the task added.
     */
    public void dukeReply(ArrayList<Task> taskList){


        System.out.println("     Got it. I've added this task: ");
        System.out.println("     " + taskList.get(taskList.size()-1).getDescription());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");

    }
    /**
     * The dukeInput method takes a string input and process the following commands.
     * find, sort, list, done, delete, set, todo, deadline, event, bye.
     * @param tasks is the list which which the command will apply to
     * @param textInput is the text input by the user
     * @throws DukeException if the command is unknown (i.e. not as of the above)
     */

    public void dukeInput (taskList tasks, String textInput) throws DukeException {
        Parser processCommand = new Parser(textInput);
        try {
            switch (processCommand.getCommand()) {
            case "find":
                taskList.displayList(taskList.findInList(processCommand.getTaskDescription()));
                break;
            case "sort":
                taskList.displayList(taskList.priorityHighToLow());
                break;
            case "list":
                taskList.displayList(taskList.getList());
                break;
            case "done":
                taskList.markInList(processCommand.getListIndex());
                break;
            case "delete":
                taskList.deleteFromList(processCommand.getListIndex());
                break;
            case "set":
                taskList.setTaskPriority(processCommand.getListIndex(), processCommand.getTaskPriority());
                break;
            case "todo":
                taskList.addTodo(new Todo(processCommand.getTodoDescription(),
                        false,
                        processCommand.getTaskPriority()));
                break;
            case "deadline":
                taskList.addDeadlines(new Deadlines(processCommand.getDeadlineDescription(),
                        processCommand.getDeadlineDate(),
                        false,
                        processCommand.getTaskPriority()));
                break;
            case "event":
                taskList.addEvent(new Event(processCommand.getEventDescription(),
                        processCommand.getEventDate(),
                        processCommand.getEventStartTime(),
                        processCommand.getEventEndTime(),
                        false,
                        processCommand.getTaskPriority()));
                break;
            case "bye":
                dukeBye();
                break;
            }
            if (!processCommand.getCommand().equals("find") || !processCommand.getCommand().equals("sort"))
                Storage.saveList(Storage.getFile().getAbsolutePath(), tasks);
        }
        catch (IOException e){
            throw new DukeException("    Unable to save to file");
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("    Unknown Command");
        }

    }
    /**
     * The getTextInput method returns the Scanner object created by the UI constructor
     */
    public static Scanner getTextInput(){return textInput;};

}
