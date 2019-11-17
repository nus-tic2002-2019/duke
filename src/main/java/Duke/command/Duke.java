package Duke.command;

import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Task;
import Duke.tasks.Todo;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*Duke.java
Run Duke program*/
public class Duke {

    private static ArrayList<Task> task = new ArrayList<Task>();
    /*Main mathod*/
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.command.Duke\n" +" What can I do for you?");

//        String[] list = new String[100];
//        Duke.command.Duke.tasks.Task[] task = new Duke.command.Duke.tasks.Task[100];

        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            line = in.nextLine();
            if( line.length() == 0) {
                System.out.println("Input cannot be empty.");
            }
            else {

                if (line.equals("list")) PrintTask(task);
                else if (line.equals("blah")) System.out.println("blah");
                else if (line.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon");
                    break;
                } else if (line.length() > 3 && line.substring(0, 4).equalsIgnoreCase("done")) {
                    int number = Integer.parseInt(line.substring(5));
                    task.get(number - 1).markAsDone();
                    System.out.println("Nice! I have marked this task as done:");
                    System.out.println(task.get(number - 1).getStatusIcon() + task.get(number - 1).getDescription());

                }
                else if(line.equalsIgnoreCase("load")) task = load();
                else if(line.equalsIgnoreCase("save")) save();
                else StoreTask(task, line);
            }
        }


        Task t = new Task("read book");
        t.markAsDone();

    }
    /*
    Identify user input and Store task object into task list.
    @para task current task list.
    @para input text input from user
    @return task new task list.
    */
    public static ArrayList<Task> StoreTask(ArrayList<Task> task, String input) {
        //int count = list.length;
        int count = 0;
        for(int i = 0; i < task.size(); i++)
        {
            if(null != task.get(i)) count++;
        }
        Task t = null;
        String[] word = input.split(" ");

        if(word[0].equalsIgnoreCase("todo"))
        {
            try {
                t = new Todo(input);
                task.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.get(count).getStatusIcon() + task.get(count).getDescription());
                int temp = count+1;
                System.out.println("Now you have " + temp+" items in the list.");
            }
            catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        }
        else if(word[0].equalsIgnoreCase("deadline"))
        {
            try {
                String[] s1 = input.split("/by");
                t = new Deadline(s1[0], s1[1]);
                task.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.get(count).getStatusIcon() + task.get(count).getDescription());
                int temp = count+1;
                System.out.println("Now you have " + temp+" items in the list.");
            }
            catch (Exception e1){
                checkException("Deadline description and time cannot be empty.");
            }
        }
        else if(word[0].equalsIgnoreCase("event"))
        {
            try {
                String[] s2 = input.split("/at");
                t = new Event(s2[0], s2[1]);
                task.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.get(count).getStatusIcon() + task.get(count).getDescription());
                int temp = count + 1;
                System.out.println("Now you have " + temp + " items in the list.");
            }
            catch (Exception e1)
            {
                checkException("Duke.command.Duke.tasks.Event description and time cannot be empty.");
            }
        }
        else if(word[0].equalsIgnoreCase("delete"))
        {
            try {
//                String[] s2 = input.split("/at");
                int index = Integer.parseInt(word[1]);
//                t = new Duke.command.Duke.tasks.Event(s2[0], s2[1]);
//                task.add(t);
                System.out.println("Noted, I've removed this task: ");
                System.out.println(task.get(index-1).getStatusIcon() + task.get(index-1).getDescription());
                int temp = count - 1;
                System.out.println("Now you have " + temp + " items in the list.");
                deleteTask(index-1);
            }
            catch (Exception e1)
            {
                checkException(e1.getMessage());
            }
        }
        else if(word[0].equalsIgnoreCase("find"))
        {
            try {
                String keyword = word[1];
                int size = 0;
                for(int i = 0; i < task.size(); i++)
                {
                    if(null != task.get(i)) size++;
                }

                System.out.println("Here are the matching tasks in your list:");
                for(int i=0;i<size;i++)
                {
                    String des = task.get(i).getDescription();
                    if(des.contains(keyword)) {
                        System.out.println(i + 1 + "." + task.get(i).getStatusIcon() + task.get(i).getDescription());
                    }
                }
            }
            catch (Exception e1)
            {
                checkException("Keywords cannot be empty.");
            }
        }
        else {
            t = new Task(input);
            System.out.println("Got it. I've added this task:");
            task.add(t);
            int temp = count+1;
            System.out.println("Now you have " + temp+" items in the list.");
        }

        return task;
    }
    /*
    Display all items inside task list.
    @param task current task list.
    @return details of all tasks.
    */
    public static void PrintTask(ArrayList<Task> task)
    {
        int size = 0;
        for(int i = 0; i < task.size(); i++)
        {
            if(null != task.get(i)) size++;
        }

        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<size;i++)
        {

            System.out.println(i + 1 + "." + task.get(i).getStatusIcon()+task.get(i).getDescription());
        }
    }

    /*
    Handle error message
    @param msg customised message to be displayed.
    @return details of error.
    */
    public static void checkException(String msg){
        try{
            throw new DukeException(msg);
        }
        catch (DukeException D1){
            System.out.println(D1.getMessage());
        }
    }

    /*
    remove task from task list
    @param index index of task to be removed
    */
    public static void deleteTask(int index){
        task.remove(index);
    }

    /*
    save current task list into hard disk.
    */
    public static void save() {
        try {
            FileOutputStream fos = new FileOutputStream("c:\\users\\shado\\desktop\\tasks.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(task);
            oos.close();
            System.out.println("Save successfully.");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /*
    load task list from hard disk.
    */
    public static ArrayList<Task> load(){
        ArrayList<Task> task1 = null;
        try{
            FileInputStream fis = new FileInputStream("c:\\users\\shado\\desktop\\tasks.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            task1 = (ArrayList<Task>) ois.readObject();
            ois.close();
            System.out.println("Load successfully.");
            PrintTask(task1);
            return task1;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return task1;
    }
}
