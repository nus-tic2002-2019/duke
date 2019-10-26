import jdk.jfr.Description;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
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

    public static String markAsDelete(int task) {
        String output = "\t______________\n\t " + tasks.get(task).setDelete() +  "\n\t Now you have " + --counter + " task in the list." + "\n\t____";
        tasks.remove(task);
        return output;
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
                    if ((line.substring(line.indexOf("todo")+5, line.length())).trim().equals("")){
                        throw new DukeException();
                    }
                    //setTask(new T
                    setTask(new Todo(line.substring(line.indexOf("todo")+5, line.length())));
                }   catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    //System.out.println(e.getMessage());
                }
                    break;
                case "deadline": //setTasksList(new Deadline(line.substring(line.indexOf("deadline")+9, line.indexOf("by")-1), line.substring(line.indexOf("by")+3, line.length())));
                    try{
                        if ((line.substring(line.indexOf("deadline")+9, line.length()).trim()).equals("")){
                            throw new DukeException();
                        }
                        //if (line.substring(line.indexOf("at")+2, line.length()).trim().equals("") || !(line.contains("at"))) {
                          //      throw new DukeException("deadline", (line.substring(line.indexOf("by")+2, line.length()).trim()));
                        //}
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
                case "delete": try {
                    if  ((line.substring(line.indexOf("delete")+7, line.length()).trim()).equals("")) {
                        throw new DukeException();
                    }
                        System.out.println(markAsDelete(Integer.parseInt(line.split(" ")[1])-1));
                    }
                    catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (NumberFormatException e) {
                        System.out.println("\t________________________\n\t OOPS!!! The task number must be a numerical value.\n\t_______");
                    }
                    catch (NullPointerException e) {
                        System.out.println("\t________________________\n\t OOPS!!! The task list cannot be empty.\n\t_______");
                    }
                    break;
                case "save": String file2 = "C:\\Users\\AdminCOOP\\Documents\\2019 Year 3 Sem 1\\TIC2002\\sample.txt"; 
                    try {
                        FileOutputStream output = new FileOutputStream(file2);
                        for (int i = 0; i < counter; i++) {
                            //writeToFile(file2,"." + tasks.get(i).toString());
                            String temp = Integer.toString(i+1) + "." + tasks.get(i).toString() + System.lineSeparator();
                            byte[] strToBytes = temp.getBytes();
                            output.write(strToBytes);
                        }
                        output.close();
                    } catch (IOException e) {
                        System.out.println("Something is wrong: " + e.getMessage());
                    }
                    break;
                default: //setTasksList(new Task(line));
                             System.out.println("added: " + line);
            }
        } while (!(line.equals("bye")));
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}

