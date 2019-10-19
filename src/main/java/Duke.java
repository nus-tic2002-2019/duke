import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> taskitems = new ArrayList<>();
    private static String strFilePath ="data/Duke.txt";

    public static void addTask(Task t, boolean bWrite){
        taskitems.add(t);
        if (bWrite == true){
            System.out.println("-----------------------------------------------");
            System.out.println("added:" + t.getDescription());
            System.out.println("-----------------------------------------------");
            writeTasks();}
    }

    public static void addTask(Todo t, boolean bWrite) {
        taskitems.add(t);
        if (bWrite == true){
            System.out.println("-----------------------------------------------");
            System.out.println("Got it. I've added this task: :");
            System.out.println(t);
            System.out.println("Now you have " + taskitems.size() + " tasks in the list");
            System.out.println("-----------------------------------------------");
            writeTasks();
        }
    }

    public static void addTask(Deadlines t, boolean bWrite){
        taskitems.add(t);
        if (bWrite == true){
            System.out.println("-----------------------------------------------");
            System.out.println("Got it. I've added this task: :");
            System.out.println(t);
            System.out.println("Now you have " + taskitems.size() + " tasks in the list");
            System.out.println("-----------------------------------------------");
            writeTasks();}
    }

    public static void addTask(Events t, boolean bWrite){
        taskitems.add(t);
        if (bWrite == true){
            System.out.println("-----------------------------------------------");
            System.out.println("Got it. I've added this task: :");
            System.out.println(t);
            System.out.println("Now you have " + taskitems.size() + " tasks in the list");
            System.out.println("-----------------------------------------------");
            writeTasks();}
    }

    public static void displayTasks()
    {
        System.out.println("-----------------------------------------------");
        for(int i=0; i < taskitems.size(); i++)
        {
            System.out.println(i + 1 + ". " + taskitems.get(i)); // or tasks[i].toString()
        }
        System.out.println("-----------------------------------------------");
    }

    public static void markDone(int pos, boolean bPrompt) {
        taskitems.get(pos-1).markDone(true);
        if (bPrompt==true) {
            System.out.println("-----------------------------------------------");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    [" + taskitems.get(pos - 1).getStatusIcon() + "]" + taskitems.get(pos - 1).getDescription());
            System.out.println("-----------------------------------------------");
            writeTasks();
        }
    }

    public static void removeItem(int pos) {
        System.out.println("-----------------------------------------------");
        System.out.println("Noted. I've removed this task: ");
        System.out.println("    [" +  taskitems.get(pos-1).getStatusIcon() + "]" +  taskitems.get(pos-1).toString());
        taskitems.remove(pos-1);
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
        System.out.println("-----------------------------------------------");
        writeTasks();
    }

    public static void saveFile(String filePath, String textToAdd, boolean isAppend)  throws IOException
    {
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

    public static void printFileContents(String filePath) throws FileNotFoundException
    {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeTasks()
    {
        try{
            if (taskitems.size() == 0) {
                saveFile(strFilePath, "", false);
            }
            else {
                for (int i = 0; i < taskitems.size(); i++) {
                    //System.out.println("Writing .. " + taskitems.get(i).writeToFile());
                    saveFile(strFilePath, taskitems.get(i).writeToFile(), i == 0 ? false : true);
                }
            }
        } catch (IOException e)
        {
            System.out.println("unable to write into file!!");
        }
    }

    public static void readTasksFromFile()
    {
        //String strFilePath ="data/Duke.txt";
        String [] linearr;
        String line;
        int i=0;
        try{
            File f = new File(strFilePath); // create a File for the given file path

            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                line = s.nextLine();
                linearr = line.split(" \\| ");
                i++;
                switch (linearr[0].toUpperCase())
                {
                    case "T": // Task
                        addTask(new Todo(linearr[2]), false);
                        if(linearr[1].equals("1")){
                            markDone(i,false);
                        }
                        break;
                    case "D": // Deadline
                        addTask(new Deadlines(linearr[2], linearr[3]), false);
                        if(linearr[1].equals("1")){
                            markDone(i,false);
                        }
                        break;
                    case "E": // Event
                        addTask(new Events(linearr[2], linearr[3]), false);
                        if(linearr[1].equals("1")){
                            markDone(i,false);
                        }
                        break;
                }
            }
        } catch (IOException e)
        {
            System.out.println("unable to read from file");
        }
    }

    public static void main(String[] args) throws DukeException  {

        readTasksFromFile();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        String [] linearr;

        while(true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            linearr = line.split(" ", 2);
            try{
                switch (line.toUpperCase()) {
                case "LIST":
                    displayTasks();
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:

                    switch (linearr[0].toUpperCase())
                    {
                        case "DONE":
                            markDone(Integer.parseInt(linearr[1]),true  );
                            break;
                        case "DELETE":
                            removeItem(Integer.parseInt(linearr[1]));
                            break;
//                        case "WRITE":
//                            writeTasks();
//                            break;
                        case "TODO":
                            try {
                                addTask(new Todo(linearr[1]),true);
                            } catch (IndexOutOfBoundsException e)
                            {
                                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            }
                            break;
                        case "DEADLINE":
                            try {
                                String lineDeadline[] = linearr[1].split("/by", 2);
                                addTask(new Deadlines(lineDeadline[0], lineDeadline[1]),true);
                            } catch (IndexOutOfBoundsException e)
                            {
                                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                            }
                            break;
                        case "EVENT":
                            try {
                                String lineEvent[] = linearr[1].split("/at", 2);
                                addTask(new Events(lineEvent[0], lineEvent[1]),true);
                            } catch (IndexOutOfBoundsException e)
                            {
                                System.out.println("OOPS!!! The description of an event cannot be empty.");
                            }
                            break;
                        default:
                                throw new DukeException();
                    }

            }
            } catch (DukeException e)
            {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" );
            }catch (Exception e) {
                System.out.println("Exception caught" + e.getMessage());
            }
        }
    }
}
// list
//  todo borrow book
//  deadline return book /by Sunday
//  event project meeting /at Mon 2-4pm
// todo read this