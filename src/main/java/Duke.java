import jdk.jfr.Description;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static int counter = 0;
    //private static Task[] taskList = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<Task>(100);

    public static void setTask(Task description) {
        //taskList[counter] = description;
        tasks.add(description);
        System.out.println("Got it. I've added this task: \n" + tasks.get(counter).toString() +  "\nNow you have " + ++counter + " tasks in list.");
    }

    public static String getTasks() {
        String output = "\t_______________________________";

        for(int i = 0; i<counter; i++) {
             output += "\n\t" + Integer.toString(i+1) + "." + tasks.get(i).toString();
        }
        return output;
    }

    public static String markAsDone(int task) {
        return tasks.get(task).setDone();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke");
        System.out.println("What can I do for you\n" + logo);

        Scanner in = new Scanner(System.in);
        String line;

        do {
            line = in.nextLine();
            switch(line.split(" ")[0]) {
                case "bye":  System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                case "todo": try {
                    if ((line.substring(line.indexOf("todo")+5, line.length())).trim().equals("")) {
                        throw new DukeException();
                    }
                    setTask(new Todo(line.substring(line.indexOf("todo") + 5, line.length())));
                }   catch (IndexOutOfBoundsException | DukeException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
                    break;
                case "deadline": //setTasksList(new Deadline(line.substring(line.indexOf("deadline")+9, line.indexOf("by")-1), line.substring(line.indexOf("by")+3, line.length())));
                    try{
                        if ((line.substring(line.indexOf("deadline")+9, line.length()).trim()).equals("")){
                            throw new DukeException();
                        }
                        setTask(new Deadline(line.substring(line.indexOf("deadline")+9, line.indexOf("by")-1), line.substring(line.indexOf("by")+3, line.length())));
                    }
                    catch (IndexOutOfBoundsException | DukeException e){
                        System.out.println("\t________________\n\t OOPS!!! The description of a deadline cannot be empty.\n\t");
                    }
                    break;
                case "event": setTask(new Event(line.substring(line.indexOf("event")+6, line.indexOf("at")-1), line.substring(line.indexOf("at")+3, line.length())));
                    break;
                case "list": System.out.println(getTasks());
                    break;
                case "done": //System.out.println(markAsDone(Integer.parseInt(line.split(" ")[1])-1));
                    try{
                        if ((line.substring(line.indexOf("done")+5, line.length()).trim()).equals("")){
                            throw new DukeException();
                        }
                        System.out.println(markAsDone(Integer.parseInt(line.split(" ")[1])-1));
                    }
                    catch (IndexOutOfBoundsException | DukeException e){
                        System.out.println("\t________________\n\t OOPS!!! The description of a done cannot be empty.\n\t");
                    }
                    catch (NullPointerException e){
                        System.out.println("\t________________\n\t OOPS!!! The task list cannot be empty.\n\t");
                    }
                    break;
                case "blah": try {
                    if ((line.substring(line.indexOf("blah") + 4, line.length()).trim()).equals("")) {
                        throw new DukeException();
                    }
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                    catch (Exception e) {                  //IndexOutOfBoundsException | DukeException e) {
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    break;
                default: //setTasksList(new Task(line));
                             System.out.println("added: " + line);
            }
        } while (!(line.equals("bye")));
    }
}

