//level 7.more oop

/**
 * Main page of Duke
 */

import exceptions.DukeException;
import exceptions.Errortype;
import parser.Parse;
import storage.Storage;
import task.Deadlines;
import task.Events;
import task.Task;
import task.Todo;
import ui.Ui;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Duke {
    //more oop

    //private Storage store;
    private static ArrayList<Task> TaskList = new ArrayList<Task>();
    private static Ui ui = new Ui();
    private static Parse parser = new Parse();
    private static Storage store = new Storage("src/main/java/data/Duke.txt");


    public static void main(String[] args){

        Boolean isExit = false;
        ui.welcome();
        store.loadFile(TaskList);

        while(!isExit){
            parser.parser(TaskList);
            isExit = Parse.isExit();
            store.saveFile(TaskList);
        }

    }

}

/**
    private static ArrayList<Task> taskItems = new ArrayList<>();
    private static String strFilePath = "src/main/java/data/Duke.txt" ;

    //public Duke(String filename){
    //   store = new Storage(filename);
    //}

    public static void saveFile(String filePath, String textToAdd, boolean isAppend)  throws IOException {
        FileWriter fw;
        if (isAppend==true)
        {
            fw = new FileWriter(filePath,true);
        }
        else {
            fw = new FileWriter(filePath);

        }
        fw.write(textToAdd);
        fw.write(System.getProperty( "line.separator"));

        fw.close();
    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeTasks() {
        try{
            if (taskItems.size() == 0) {
                saveFile(strFilePath, "", false);
            }
            else {
                for (int i = 0; i < taskItems.size(); i++) {
                    saveFile(strFilePath, taskItems.get(i).SaveFile(), i == 0 ? false : true);
                }
            }
        } catch (IOException e){
            System.out.println("File not found! A new file - Duke.txt will create");
            }
    }


    public static void readTasksFromFile() {
        //String strFilePath ="Duke.txt";
        String [] line_arr;
        String line;
        int i=0;
        try {
            File f = new File(strFilePath); // create a File for the given file path

            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                line = s.nextLine();
                line_arr = line.split(" \\| ");
                i++;
                switch (line_arr[0].toUpperCase()) {
                    case "T": // Task
                        Duke.addTask(new Todo(line_arr[2]), false);
                        if (line_arr[1].equals("1")) {
                            markDone(i, false);
                        }
                        break;
                    case "D": // Deadline
                        Duke.addTask(new Deadlines(line_arr[2], line_arr[3]), false);
                        if (line_arr[1].equals("1")) {
                            markDone(i, false);
                        }
                        break;
                    case "E": // Event
                        Duke.addTask(new Events(line_arr[2], line_arr[3]), false);
                        if (line_arr[1].equals("1")) {
                            markDone(i, false);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("File not found! A new file - Duke.txt will create");
        }
    }


    public static void displayTasks() {
        System.out.println("\t--------------------------------------------------");
        //for (int i = 0; i < Count; i++) {
        for (int i = 0; i < taskItems.size(); i++) {
            //System.out.println(i + 1 + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            //System.out.println(i + 1 + ". " + tasks[i]);
            System.out.println(i + 1 + ". " + taskItems.get(i));
        }
        System.out.println("\t--------------------------------------------------");
    }


    //level 3 -> level 6
    public static void addTask(Task t, boolean booWrite) {
        //tasks[Count] = t;
        taskItems.add(t);
        if(booWrite == true){
            System.out.println("\t--------------------------------------------------");
            System.out.println("added: " + t.getDescription());
            System.out.println("\t--------------------------------------------------");
            writeTasks();
        }

        //Count++;
    }

    //level 4 -> level 6
    public static void addTask(Todo t, boolean booWrite) {
        //tasks[Count] = t;
        taskItems.add(t);
        if(booWrite == true) {
            System.out.println("\t--------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(t);
            //Count++;
            //System.out.println("Now you have " + Count + " tasks in the list");
            System.out.println("Now you have " + taskItems.size() + " tasks in the list");
            System.out.println("\t--------------------------------------------------");
            writeTasks();
        }
    }

    public static void addTask(Deadlines t, boolean booWrite) {
        //tasks[Count] = t;
        taskItems.add(t);
        if(booWrite == true) {
            System.out.println("\t--------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(t);
            //Count++;
            //System.out.println("Now you have " + Count + " tasks in the list");
            System.out.println("Now you have " + taskItems.size() + " tasks in the list");
            System.out.println("\t--------------------------------------------------");
            writeTasks();
        }
    }

    public static void addTask(Events t, boolean booWrite) {
        //tasks[Count] = t;
        taskItems.add(t);
        if(booWrite == true) {
            System.out.println("\t--------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(t);
            //Count++;
            //System.out.println("Now you have " + Count + " tasks in the list");
            System.out.println("Now you have " + taskItems.size() + " tasks in the list");
            System.out.println("\t--------------------------------------------------");
            writeTasks();
        }
    }

    public static void markDone(int post, boolean booPrompt) {
        //tasks[post - 1].markDone(true);
        taskItems.get(post - 1).markDone(true);
        if(booPrompt == true) {
            System.out.println("\t--------------------------------------------------");
            System.out.println("Nice! I've marked this task as done:");
            //System.out.println("    [" + tasks[post - 1].getStatusIcon() + "] " + tasks[post - 1].getDescription());
            System.out.println("    [" + taskItems.get(post - 1).getStatusIcon() + "] " + taskItems.get(post - 1).getDescription());
            System.out.println("\t--------------------------------------------------");
            writeTasks();
        }
    }

    public static void removeItem(int post){
        System.out.println("\t--------------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("    [" + taskItems.get(post - 1).getStatusIcon() + "] " + taskItems.get(post - 1).toString());
        //System.out.println(taskItems.get(post - 1).toString());
        taskItems.remove(post - 1);
        System.out.println("Now you have " + taskItems.size() + " tasks in the list.");
        System.out.println("\t--------------------------------------------------");
        writeTasks();

    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        readTasksFromFile();

        String line;
        String [] line_arr;

        while (true) {
            Scanner in = new Scanner(System.in);
            int index_date;
            line = in.nextLine();
            line_arr = line.split(" ", 2);

            try {
                switch (line.toUpperCase()) {

                    case "LIST":
                        displayTasks();
                        break;
                    case "BYE":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;

                    default:

                        switch (line_arr[0].toUpperCase()) {
                            case "DELETE":
                                removeItem(Integer.parseInt(line_arr[1]));
                                break;
                            //case "REMOVE":
                            //    removeItem(Integer.parseInt(line_arr[1], true));
                            //    break;
                            case "DONE":
                                markDone(Integer.parseInt(line_arr[1]), true);
                                break;
                            case "TODO":
                                try {
                                    addTask(new Todo(line_arr[1]), true);
                                } catch(IndexOutOfBoundsException e) {
                                    System.out.println("☹ OOPS!!! The description of a Todo cannot be empty.");
                                }
                                break;
                            case "DEADLINE":
                                try {
                                    String lineDeadline[] = line_arr[1].split("/by", 2);
                                    addTask(new Deadlines(lineDeadline[0], lineDeadline[1]), true);
                                } catch(IndexOutOfBoundsException e) {
                                    System.out.println("☹ OOPS!!! The description of a Deadline cannot be empty.");
                                }
                                break;
                            case "EVENT":
                                try {
                                    String lineEvent[] = line_arr[1].split("/at", 2);
                                    addTask(new Events(lineEvent[0], lineEvent[1]), true);
                                } catch(IndexOutOfBoundsException e) {
                                    System.out.println("☹ OOPS!!! The description of a Event cannot be empty.");
                                }
                                break;
                            default:
                                //addTask(new Todo(line));
                                throw new DukeException();
                        }

                        //break;
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" );
            } catch (Exception e) {
                System.out.println("Exception caught" + e.getMessage());
            }

        }
    }
}

 **/
