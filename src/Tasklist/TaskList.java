package Tasklist;

import java.util.ArrayList;
import Exception.DukeException;

//import static Parser.Parser.parseSchedule;

public class TaskList {

    private static ArrayList<Task> taskitems;
//    private static String strFilePath = "";


    public TaskList(ArrayList<Task> taskinput)
    {
        if (taskinput.isEmpty())
        {
            taskitems = new ArrayList<>();
            System.out.println("OOPS!!! There is empty task");
        }
        taskitems = taskinput;
    }

    public ArrayList<Task> getTaskitems(){
        return taskitems;
    }

    public int size(){
        return taskitems.size();
    }

    public Task get(int position){
        return taskitems.get(position);
    }

    public TaskList()
    {
        taskitems = new ArrayList<>();
    }


    public static void addTask(Task t, boolean bWrite){
        taskitems.add(t);
        if (bWrite == true){
            System.out.println("Got it. I've added this task:");
            int k = taskitems.size();
            System.out.println( taskitems.get(k-1));
            System.out.println("Now you have " + taskitems.size() + " tasks in the list");
        }
    }

    public static void displayTasks()
    {
        for(int i=0; i < taskitems.size(); i++)
        {
            System.out.println(i + 1 + ". " + taskitems.get(i));
        }
    }

    public static void markDone(int pos, boolean bPrompt) {
        taskitems.get(pos-1).markDone(true);
        if (bPrompt==true) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println( taskitems.get(pos-1));
        }
    }

    public static void removeItem(int pos) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println( taskitems.get(pos-1).toString());
        taskitems.remove(pos-1);
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
    }

    /**
     * This method find keyword/s within all the task list.
     * @param wordSearch : is the input from user.
     */
    public static void searchTask(String wordSearch) throws DukeException {

        int setFound = 0;
        String[] searchSplit = wordSearch.split(" ",2);

        if (searchSplit.length != 2){
            throw new DukeException("Invalid input");
        }
        if (searchSplit[1].equals("")){
            throw new DukeException("Enter something to search");
        }

        for (int i = 0; i < taskitems.size(); i++) {

            if (taskitems.get(i).getDescription().contains(searchSplit[1])) {
                System.out.println(taskitems.get(i));
                setFound = 1;
            }
        }
        if (setFound == 0){
            throw new DukeException ("No items found");
        }
    }

    public static void searchSchedule(String scheduleSearch) throws DukeException {

        int setFound = 0;
        String[] searchSplit = scheduleSearch.split(" ",2);

        if (searchSplit.length != 2){
            throw new DukeException("Invalid input");
        }
        if (searchSplit[1].equals("")){
            throw new DukeException("Enter something to search");
        }

//        String trimed = parseSchedule(searchSplit[1]);
        String trimed = searchSplit[1].trim();
        System.out.println("Your Schedule for " + trimed + " are as follow");

        for (int i = 0; i < taskitems.size(); i++) {

            if (taskitems.get(i).toString().contains(trimed)) {
                System.out.println(taskitems.get(i));
                setFound = 1;
            }
        }

        if (setFound == 0){
            throw new DukeException ("No items found");
        }
    }
}