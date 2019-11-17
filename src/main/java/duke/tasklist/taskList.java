package duke.tasklist;



import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is a taskList class which manages the list.
 * It is able to store Todo, Deadline and Event objects and the individual task details
 *
 */
public class taskList {
    private static ArrayList<Task> taskList, findTaskInList, sortPriorityList;
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
     * the displayList method display the list to the user
     */
    public static void displayList (ArrayList<Task> tList){
        System.out.println("     Here are the tasks in your list:");
        for (int index = 1; index <= tList.size(); index++){
            System.out.println ("     " + index + "." + tList.get(index-1).getDescription());
        }
    }
    /**
     * markInList method mark the indicated task as done
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
     * deleteFromList method delete a task from list
     * @param textInput is the task number
     */
    public static void deleteFromList(String textInput) throws DukeException{
        try {
            String taskDescription = taskList.get(Integer.parseInt(textInput) - 1).getDescription();
            taskList.remove(Integer.parseInt(textInput) - 1);
            System.out.println("    Noted. I've removed this task: ");
            System.out.println("     " + taskDescription);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list.");

        } catch (NumberFormatException e){
            throw new DukeException("    Please indicate task number");
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("    Please indicate a task number within the list");
        }
    }
    /**
     * addTodo method adds a Todo task to list
     * @param td is the Todo object to be added to the list
     */
    public static void addTodo (Todo td){
        taskList.add(td);
        ui.dukeReply(taskList);
    }
    /**
     * addDeadline method adds a Deadline task to list
     * @param dl is the Deadline object to be added to the list
     */
    public static void addDeadlines (Deadlines dl){
        taskList.add(dl);
        ui.dukeReply(taskList);
    }
    /**
     * addEvent method provides adds an Event task to list
     * @param e is the event object to be added to the list
     */
    public static void addEvent (Event e){
        taskList.add(e);
        ui.dukeReply(taskList);
    }
    /**
     * getList method retrieve the list of tasks
     * @return the current list of tasks
     */
    public static ArrayList<Task> getList (){
        return taskList;
    }

    public static void setTaskPriority(String textInput, Priority taskPriorityLevel) throws DukeException {
        try {

            taskList.get(Integer.parseInt(textInput) - 1).setTaskPriority(taskPriorityLevel);
            System.out.println("    Got it! I've set the priority for this task: ");
            System.out.println ("     " + taskList.get(Integer.parseInt(textInput) - 1).getDescription());


        } catch (NumberFormatException e){
            throw new DukeException("Please indicate task number");
        }

    }

    public static ArrayList<Task> priorityHighToLow() {
        sortPriorityList = new ArrayList<Task>();
        sortPriorityList  = (ArrayList<Task>) taskList.clone();
        Collections.sort(sortPriorityList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.getTaskPriority() == t2.getTaskPriority()){
                    return t1.getDescription().compareTo(t2.getDescription());
                } else
                return t1.getTaskPriority().compareTo(t2.getTaskPriority());
            }
        });
        return sortPriorityList;
    }

    public static ArrayList<Task> findInList(String searchStr){
        findTaskInList= new ArrayList<Task>();
        for (Task task:taskList){
            if(task.getDescription().contains(searchStr)){
                findTaskInList.add(task);
            }
        }
        return findTaskInList;
    }


}
