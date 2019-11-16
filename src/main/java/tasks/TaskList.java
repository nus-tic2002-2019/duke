package tasks;

import uiParser.Parser;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> userArr;

    public TaskList(ArrayList<Task> loadfile) {
        userArr = new ArrayList<Task>(loadfile);
    }

    public TaskList() {
        userArr = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return userArr;
    }

    public void addTasks(String task) {
        userArr.add(new Todo(task));
        this.printAdded();
    }

    public void addTasks(String task, String actionType, String taskTime){

        switch (actionType) {
            case "event":
                LocalDate dateE = new Parser().parseDate(taskTime);
                LocalTime time = new Parser().parseTime(taskTime);
                userArr.add(new Event(task, dateE, time));
                break;
            case "deadline":
                /*String[] d = new Parser().parseDateTime(taskTime);
                LocalDate date = new Parser().parsedDate(d[0]);*/
                LocalDate dateD = new Parser().parseDate(taskTime);
                userArr.add(new Deadline(task, dateD));
                break;
            default:
                break;
        }
        this.printAdded();
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
         userArr.get(num).isDone();
         printDone(num);
    }

    public void deleteTasks(String taskNo){
        int num = Integer.parseInt(taskNo) - 1;
        userArr.remove(num);
        printDelete(num);
    }

    public void getKeyWordTask(int taskNo) {
        System.out.println("." + userArr.get(taskNo));
    }

    public void findTasks(String keyword) {
        int i = 1;
        int count = 0;
        for (Task t : userArr) {
            if (t.getTask().contains(keyword)) {
                System.out.print(i);
                i++;
                getKeyWordTask(count);
            }
            count++;
        }
    }
}

