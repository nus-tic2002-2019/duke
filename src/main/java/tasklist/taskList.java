package tasklist;

import java.util.ArrayList;
import java.util.List;

import exception.DukeException;
import ui.Ui;

/**
 * This is a taskList class which manages the list
 *
 */
public class taskList {
    private static ArrayList<Task> taskList;
    private static Ui ui = new Ui();
    /**
     * The first taskList constructor which accept no parameters simply create an empty list.
     */
    public taskList(){
        taskList = new ArrayList();
    }
    /**
     * The second taskList constructor accept a list of tasks, create a new list and load the tasks into the list.
     * @param listTasks a list of containing multiple tasks object
     */
    public taskList(List<Task> listTasks){
        taskList = new ArrayList();
        taskList.addAll(listTasks);
    }
    /**
     * the displayList method simply show the list on the user interface
     */
    public static void displayList (){
        System.out.println("     Here are the tasks in your list:");
        for (int index = 1; index <= taskList.size(); index++){
            System.out.println ("     " + index + "." + taskList.get(index-1).getDescription());
        }
    }
    /**
     * markInList method provides a way to mark which task is done
     * @param textInput is the task number
     */
    public static void markInList(String textInput) throws DukeException {
        try {

            taskList.get(Integer.parseInt(textInput) - 1).markAsDone(true);
            System.out.println("    Nice! I've marked this task as done: ");
            System.out.println ("     " + taskList.get(Integer.parseInt(textInput) - 1).getDescription());


        } catch (NumberFormatException e){
            throw new DukeException("Please indicate task number");
        }

    }
    /**
     * deleteFromList method provides a way to delete a task from list
     * @param textInput is the task number
     */
    public static void deleteFromList(String textInput) throws DukeException{
        try {

            System.out.println("    Noted. I've removed this task: ");
            System.out.println("     " + taskList.get(Integer.parseInt(textInput) - 1).getDescription());
            taskList.remove(Integer.parseInt(textInput) - 1);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list.");

        } catch (NumberFormatException e){
            throw new DukeException("Please indicate task number");
        }
    }
    /**
     * addTodo method provides a way to add a Todo task to list
     * @param td is the Todo object which you wish to add
     */
    public static void addTodo (Todo td){
        taskList.add(td);
        ui.dukeReply(taskList);
    }
    /**
     * addDeadline method provides a way to add a Deadline task to list
     * @param dl is the Deadline object which you wish to add
     */
    public static void addDeadlines (Deadlines dl){
        taskList.add(dl);
        ui.dukeReply(taskList);
    }
    /**
     * addEvent method provides a way to add an event task to list
     * @param e is the event object which you wish to add
     */
    public static void addEvent (Event e){
        taskList.add(e);
        ui.dukeReply(taskList);
    }
    /**
     * getList method provides a way to retrieve the list
     * @return the current list of tasks
     */
    public static ArrayList<Task> getList (){
        return taskList;
    }
}
