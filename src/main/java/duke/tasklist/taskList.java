package duke.tasklist;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is a TaskList class which manages the list.
 * It is able to store Todo, Deadline and Event objects and the individual task details
 * */
public class TaskList {
    private static ArrayList<Task> taskList;
    private static ArrayList<Task> findTaskInList;
    private static ArrayList<Task> findLateTasks;
    private static Ui ui = new Ui();
    /**
     * The first TaskList constructor which accept no parameters simply create an empty list.
     */
    public TaskList(){
        taskList = new ArrayList<>();
    }
    /**
     * The second TaskList constructor accept a list of tasks, create a new list and load the tasks into the list.
     * @param listTasks a list of containing multiple tasks object
     */
    public TaskList(List<Task> listTasks){
        taskList = new ArrayList<>();
        taskList.addAll(listTasks);
    }
    /**
     * the displayList method display the list to the user
     * @param tList the TaskList which is to be displayed
     * @param command to display various messages according to the command
     */
    public static void displayList (ArrayList<Task> tList, String command){
        assert command != null:"command cannot be null";
        switch (command) {
        case "late":
            if(findLateTasks.size() == 0){
                System.out.println("    You do not have any overdue task");
            }else {
                System.out.println("    Here are the overdue tasks in your list:");
            }
            break;
        case "list":
            if (taskList.size() == 0){
                System.out.println("    Your list is empty");
            } else {
                System.out.println("    Here are the tasks in your list:");
            }
            break;
        case "find":
            if (findTaskInList.size() == 0){
                System.out.println("    We did not find any matching task in your list");
            } else {
                System.out.println("    Here are the matching tasks in your list:");
            }
            break;
        case "sort":
            if (taskList.size() == 0){
                System.out.println("    Your list is empty");
            } else {
                System.out.println("     The tasks in your list are now sorted from High to Low Priority:\n");
            }
            break;
        }
        for (int index = 1; index <= tList.size(); index++){
            System.out.println ("     " + index + "." + tList.get(index-1).getDescription());
        }
    }
    /**
     * markInList method mark the indicated task as done
     * @param textInput is the task number
     */
    public static void markInList(String textInput, boolean taskStatus) throws DukeException {
        try {

            taskList.get(Integer.parseInt(textInput) - 1).markAsDone(taskStatus);
            if (taskStatus){
                System.out.println("     Nice! I've marked this task as done: ");
            } else{
                System.out.println("     Ok! I've marked this task as not done: ");
            }

            System.out.println ("     " + taskList.get(Integer.parseInt(textInput) - 1).getDescription());


        } catch (NumberFormatException e){
            throw new DukeException("    Please indicate task number");
        } catch (IndexOutOfBoundsException e){
            if (taskList.size() == 0){
                throw new DukeException("    Duke cannot mark a task as done or not done in an empty list");
            }else {
                throw new DukeException("    Please indicate a task number within the list");
            }
        }

    }
    /**
     * deleteFromList method delete a task from list
     * @param textInput is the task number
     * @throws DukeException if the user did not indicate a task number, the task number did not exist,
     * or the user attempt to delete from an empty list
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
            if (taskList.size() == 0){
                throw new DukeException("    Duke cannot delete from an empty list");
            }else {
                throw new DukeException("    Please indicate a task number within the list");
            }
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
    /**
     * setTaskPriority method is to set the priority for the indicated task index
     * @param textInput is the task index in the list provided by the user
     * @param taskPriorityLevel is the priority level which the user wish to set for the task index indicated
     * @throws DukeException when the task index is missing from the input
     */
    public static void setTaskPriority(String textInput, Priority taskPriorityLevel) throws DukeException {
        try {

            taskList.get(Integer.parseInt(textInput) - 1).setTaskPriority(taskPriorityLevel);
            System.out.println("    Got it! I've set the priority for this task: ");
            System.out.println ("     " + taskList.get(Integer.parseInt(textInput) - 1).getDescription());


        } catch (NumberFormatException e){
            throw new DukeException("    Please indicate task number");
        }

    }
    /**
     * priorityHighToLow method is to sort the tasks in the list in the order from High to Low Priority level
     * @return returns the sorted list
     */
    public static ArrayList<Task> priorityHighToLow() {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.getTaskPriority() == t2.getTaskPriority()){
                    return t1.getDescription().compareTo(t2.getDescription());
                } else
                return t1.getTaskPriority().compareTo(t2.getTaskPriority());
            }
        });
        return taskList;
    }
    /**
     * findInList method is to find a keyword matching the task description in the list
     * @return returns a list of tasks which has the matching keyword in it's description
     */
    public static ArrayList<Task> findInList(String searchStr){
        findTaskInList= new ArrayList<Task>();
        for (Task task:taskList){
            if(task.getDescription().toLowerCase().contains(searchStr)){
                findTaskInList.add(task);
            }
        }
        return findTaskInList;
    }
    /**
     * lateTask method is to find tasks which is overdue from today's date
     * @return returns a list of tasks which has the matching keyword in it's description
     */
    public static ArrayList<Task> lateTask(){
        findLateTasks = new ArrayList<>();
        for (Task task:taskList){
            if(task.getDate().compareTo(LocalDate.now()) < 0){
                findLateTasks.add(task);
            }
        }
        return findLateTasks;
    }
}
