package tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import exception.DukeException;

public class TaskList {

    //private static ArrayList<Task> taskitems = new ArrayList<>();
    private static ArrayList<Task> taskitems;

    /**
     * Constructor of Tasklist object which will hold a list of tasks.
     * @param taskinput ArrayList of task to be held by Tasklist object
     * @throws DukeException
     */
    public TaskList(ArrayList<Task> taskinput) throws DukeException
    {
        if (taskinput.isEmpty())
        {
            taskitems = new ArrayList<>();
            System.out.println("OOPS!!! There is empty task");
        }
        taskitems = taskinput;
    }

    /**
     *
     * @return
     */
    public ArrayList<Task> getTaskitems(){
        return taskitems;
    }

    /**
     * To obtain the size of the tasklist, to indicate number of tasks held.
     * @return integer size of tasklist
     */
    public int size(){
        return taskitems.size();
    }

    /**
     * To obtain the task from a given position
     * @param position integer value that indicates the position of task in the tasklist
     * @return task object of specified position
     */
    public Task get(int position){
        return taskitems.get(position);
    }

    /**
     * Constructor of Tasklist object
     */
    public TaskList()
    {
        taskitems = new ArrayList<>();
    }

    /**
     * To add new Task based on Task object
     * @param t Task object to be added
     */
    //public static void addTask(Task t, boolean bWrite){
    public static void addTask(Task t){
        taskitems.add(t);
        System.out.println("added:" + t.toString());
    }

    /**
     * To display Task available in the tasklist object
     */
    public static void displayTasks()
    {
        for(int i=0; i < taskitems.size(); i++)
        {
            System.out.println(i + 1 + ". " + taskitems.get(i)); // or tasks[i].toString()
        }
    }

    /**
     *
     * @param pos
     */
    public static void markDone(int pos) {
        taskitems.get(pos-1).markDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    [" + taskitems.get(pos - 1).getStatusIcon() + "]" + taskitems.get(pos - 1).toString());
    }

    /**
     * Remove a task from the tasklist given the serial number/running number
     * @param pos position of task in the tasklist based on what user see
     */
    public static void removeItem(int pos) throws DukeException {
        System.out.println("pos: " + pos + " size:" + taskitems.size());
        if (pos>taskitems.size())
        {
            throw new DukeException("Invalid serial number. task number is greater than number of tasks available");
        }
        if (pos<=0)
        {
            throw new DukeException("Invalid serial number. index is out of bound");
        }

        System.out.println("Noted. I've removed this task: ");
        System.out.println("    [" +  taskitems.get(pos-1).getStatusIcon() + "]" +  taskitems.get(pos-1).toString());
        taskitems.remove(pos-1);
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
    }

    /**
     * This method only search date which is equal to a task of a type Deadline (as deadline only have date time attributes)
     * @param searchdate
     */
    public static void findDeadline(LocalDate searchdate){
        System.out.println("Searching date : " + searchdate + " ...");
        boolean isFound = false;
        Integer count = 0;
        for (Task task : taskitems) {
            LocalDate date;
            if (task instanceof Deadlines) {
                Deadlines deadline = (Deadlines) task;
                deadline.getDateTime().format(DateTimeFormatter.ofPattern("MMM d yyyy  HH:mma"));
                date = deadline.getDateTime().toLocalDate();
                if (date.equals(searchdate))
                {   count++;
                    System.out.println( count + ". " + deadline);
                    isFound=true;
                }
            }else if (task instanceof Events) {
                Events event = (Events) task;
                event.getDateTime().format(DateTimeFormatter.ofPattern("MMM d yyyy  HH:mma"));
                date = event.getDateTime().toLocalDate();
                if (date.equals(searchdate))
                {   count++;
                    System.out.println( count + ". " + event);
                    isFound=true;
                }
            }
        }
        if (!isFound)
        {
            System.out.println("Found 0 task. Unable to find date : " + searchdate + " (" + searchdate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")");
        }
        else {
            System.out.println("Found " + count + " task(s)");
        }
    }

    /**
     * To sort task based on the date - for events and deadline task only
     * Todo tasks will not be sorted
     * @param taskitem ArraList of Task objects
     * @throws DukeException
     */
    public static void sortTaskDate(ArrayList<Task> taskitem) throws DukeException{
        // sort todo tasks that cannot be sorted first, and then
        // sort deadline and event which contains date
        int TodoListCount = 0;
        for ( int i=0 ; i<taskitem.size() ; i++ ){
            String command = taskitem.get(i).toString().substring(0,3);
            if ( command.equals("[T]") ){
                Collections.swap(taskitem, TodoListCount,i);
                TodoListCount += 1;
            }
        }
        boolean isSwap = true;
        while (isSwap){
            isSwap = false  ;
            for (int i = TodoListCount + 1; i<taskitem.size() ; i++ ){
                if (taskitem.get(i-1).getDateTime().isAfter(taskitem.get(i).getDateTime()) ){
                    Collections.swap(taskitem,i-1,i);
                    isSwap = true;
                }
            }
        }
        for(int i=0; i < taskitem.size(); i++)
        {
            System.out.println(i + 1 + ". " + taskitem.get(i));
        }
    }

    /**
     * To find text based on a search keyword
     * @param keyword search keyword provided by the user
     */
    public static void findText(String keyword){
        boolean isFound=false;
        int found = 0;
        for (Task task : taskitems) {
            if (task.getDescription().toLowerCase().indexOf(keyword) >= 0)
            {
                System.out.println((found+1) + ". " + task.toString());
                found++;
                isFound = true;
            }
        }

        if (!isFound){
            System.out.println("Oops, unable to find keyword");
        }
        else
        {
            System.out.println("Found " + found + " occurence(s)");
        }
    }
}
