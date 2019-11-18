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
        System.out.println ("    Type \"help\" to see what I can understand");

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
    /**
     * The showError method shows the Unknown Command error if the user enter a command that is not recognize by Duke
     */
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

    public void dukeInput (TaskList tasks, String textInput) throws DukeException {
        Parser processCommand = new Parser(textInput);
        try {
            switch (processCommand.getCommand()) {
            case "help":
                howToUseDuke();
                break;
            case "overdue":
                TaskList.displayList(TaskList.lateTask(), "late");
                break;
            case "find":
                TaskList.displayList(TaskList.findInList(processCommand.getTaskDescription()), "find");
                break;
            case "sort":
                TaskList.displayList(TaskList.priorityHighToLow(), "sort");
                break;
            case "list":
                TaskList.displayList(TaskList.getList(), "list");
                break;
            case "done":
                TaskList.markInList(processCommand.getListIndex(),true);
                break;
            case "undone":
                TaskList.markInList(processCommand.getListIndex(),false);
                break;
            case "delete":
                TaskList.deleteFromList(processCommand.getListIndex());
                break;
            case "set":
                TaskList.setTaskPriority(processCommand.getListIndex(), processCommand.getTaskPriority());
                break;
            case "todo":
                TaskList.addTodo(new Todo(processCommand.getTodoDescription(),
                        false,
                        processCommand.getTaskPriority()));
                break;
            case "deadline":
                TaskList.addDeadlines(new Deadlines(processCommand.getDeadlineDescription(),
                        processCommand.getDeadlineDate(),
                        false,
                        processCommand.getTaskPriority()));
                break;
            case "event":
                TaskList.addEvent(new Event(processCommand.getEventDescription(),
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
            if (!processCommand.getCommand().equals("find") ||
                    !processCommand.getCommand().equals("overdue")){
                Storage.saveList(Storage.getFile().getAbsolutePath(), tasks);
            }
        }
        catch (IOException e){
            throw new DukeException("    Unable to save to file");
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("    I'm not sure what is that. Type \"help\" to see what I can understand");
        }

    }

    /**
     * The getTextInput method returns the Scanner object created by the UI constructor
     */
    public static Scanner getTextInput(){return textInput;}

    private void howToUseDuke(){
        System.out.println("     Things Duke can help you with               | Command:");
        System.out.println("     ____________________________________________|_______________________________________________________________");
        System.out.println("     To show the task list loaded:               | \"list\"");
        System.out.println("     To add new Todo:                            | \"todo description /priority level(high, medium or low)\"");
        System.out.println("     To add new Deadlines:                       | \"deadline description /priority level(high, medium or low) /by yyyy-mm-dd\"");
        System.out.println("     To add new Event:                           | \"event description /priority level(high, medium or low) /at yyyy-mm-dd Start time (HH:mm) - End time (HH:mm)\"");
        System.out.println("     To mark a Task as Done:                     | \"done [list index of task] e.g. done 4\"");
        System.out.println("     To mark a Task as Not Done:                 | \"undone [list index of task] e.g. undone 5\"");
        System.out.println("     To delete a Task from the list:             | \"delete [list index of task] e.g. delete 3\"");
        System.out.println("     To sort the list from High to Low Priority: | \"sort\"");
        System.out.println("     To search for overdue tasks:                | \"overdue\"");
        System.out.println("     To end this program:                        | \"bye\"");
        System.out.println("     NOTE: Your list is automatically save to the file whenever you add a task, delete a task, or mark a task as done/not done!");
    }
;

}
