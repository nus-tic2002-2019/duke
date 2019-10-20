package tasklist;

import java.io.IOException;
import java.util.ArrayList;

import storage.Storage;
import ui.Ui;

public class taskList {
    private static ArrayList<Task> taskList = new ArrayList();
    private static Ui ui = new Ui();
    private static String fileName = new String("data/tasks.txt");
    private static Storage storage = new Storage(fileName);

    public static void displayList (){
        System.out.println("     Here are the tasks in your list:");
        for (int index = 1; index <= taskList.size(); index++){
            System.out.println ("     " + index + "." + taskList.get(index-1).getDescription());
        }
    }
    public static void markInList(String textInput) {
        try {

            taskList.get(Integer.parseInt(textInput) - 1).markAsDone(true);
            System.out.println("    Nice! I've marked this task as done: ");
            System.out.println ("     " + taskList.get(Integer.parseInt(textInput) - 1).getDescription());


        } catch (NumberFormatException e){

        }

    }
    public static void deleteFromList(String textInput){
        try {

            System.out.println("    Noted. I've removed this task: ");
            System.out.println("     " + taskList.get(Integer.parseInt(textInput) - 1).getDescription());
            taskList.remove(Integer.parseInt(textInput) - 1);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list.");

        } catch (NumberFormatException e){

        }
    }

    public static void addTodo (Todo td){
        taskList.add(td);
        ui.dukeReply(taskList);
    }

    public static void addDeadlines (Deadlines dl){
        taskList.add(dl);
        String details = dl.getDescription();
        try{
            storage.save(fileName, details);
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ui.dukeReply(taskList);
    }

    public static void addEvent (Event e){
        taskList.add(e);
        ui.dukeReply(taskList);
    }
}
