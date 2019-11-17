package tasks;

import uiParser.Parser;
import java.time.*;
import java.util.ArrayList;

/**
 *  Represents an ArrayList of Tasks to be completed by the user.
 *  Return the ArrayList of Tasks
 */

public class TaskList {
    private static ArrayList<Task> userArr;

    /**
     * Constructor for TaskList class if there is an existing file of tasks saved.
     * Load the ArrayList with the data from the .txt file
     * @param loadfile the file to be loaded into the ArrayList
     */

    public TaskList(ArrayList<Task> loadfile) {
        userArr = new ArrayList<Task>(loadfile);
    }

    /**
     * Constructor for TaskList class if there is no existing file of tasks.
     */
    public TaskList() {
        userArr = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return userArr;
    }

    public void addTasks(String task) {
        userArr.add(new Todo(task));
        printAdded();
    }

    public void addTasks(String task, String actionType, String taskTime){
        switch (actionType) {
            case "event":
                LocalDate dateE = new Parser().parseDate(taskTime);
                LocalTime time = new Parser().parseTime(taskTime);
                if (!time.equals(null)) {
                    userArr.add(new Event(task, dateE, time));
                } else {
                    return;
                }
                break;
            case "deadline":
                LocalDate dateD = new Parser().parseDate(taskTime);
                userArr.add(new Deadline(task, dateD));
                break;
            default:
                break;
        }
        printAdded();
    }

    public void printAdded() {
        System.out.println("Got it. I've added this task:\n  " + userArr.get(userArr.size()-1));
        System.out.println("Now you have " + userArr.size() + " tasks in the list.");
    }

    public void printDone(int taskNo) {
        System.out.println("Nice! I've marked this task as done:\n " + userArr.get(taskNo));
    }

    public void printDelete(int taskNo) {
        System.out.println("Noted. I've removed this task:\n " + userArr.get(taskNo));
        System.out.println("Now you have " + userArr.size() + " tasks in the list.");
    }

    public void listTasks(){
        System.out.println("Here are the tasks in your list:");
        int i = 0;
        for (Task t : userArr) {
            System.out.println((i+1) + "." + t);
            i++;
        }
    }

    public void setDone(String taskNo){
         int num = Integer.parseInt(taskNo) - 1;
         if (isTaskNoValid(num)) {
             assert num < userArr.size() : "taskNo should be smaller than total no. of tasks";
             userArr.get(num).isDone();
             printDone(num);
         }
    }

    public void deleteTasks(String taskNo){
        int num = Integer.parseInt(taskNo) - 1;
        if (isTaskNoValid(num)) {
            assert num < userArr.size() : "taskNo should be smaller than total no. of tasks";
            userArr.remove(num);
            printDelete(num);
        }
    }

    public boolean isTaskNoValid(int taskNo) {
        return (taskNo < userArr.size()? true : false);
    }

    public void findTasks(String keyword) {
        int i = 0;
        int count = 0;
        for (Task t : userArr) {
            if (t.getTask().contains(keyword)) {
                System.out.println((i+1) + "." + userArr.get(count));
                i++;
            }
            count++;
        }
        if (i < 1) {
            System.out.println("No tasks are found.");
        }
    }
}

