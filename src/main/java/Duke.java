import tasklist.Deadline;
import tasklist.Event;
import tasklist.Task;
import tasklist.ToDo;
import ui.UI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
   public static ArrayList<Task> taskList = new ArrayList<Task>();

        public static void main(String[] args) throws FileNotFoundException {
            Storage.loadFile();
//            String logo = " ____        _        \n"
//                    + "|  _ \\ _   _| | _____ \n"
//                    + "| | | | | | | |/ / _ \\\n"
//                    + "| |_| | |_| |   <  __/\n"
//                    + "|____/ \\__,_|_|\\_\\___|\n";
//            System.out.println("Hello from\n" + logo);
            String line;
            Scanner in = new Scanner(System.in);
            //System.out.print("Hello! I'm Duke\n" + "What can I do for you?");
            UI.printDuke();

        while (true) {
            line = in.nextLine();

            if (line.equals("bye"))  {
               UI.bye();
               save();

                break;
            } else if (line.equals("list")) {
                list();

            } else if (line.contains("done")) {
                Done(line);

            } else if (line.contains("event")) {
                EventTask(line);

            } else if (line.contains("deadline")) {
                DeadlineTask(line);

            } else if (line.contains("todo")) {
               ToDoTask(line);

            }

            else if (line.contains("delete")){
                DeleteTask(line);

            }else{
                InvalidTask(line);
            }

        }

        }
    static void save() throws FileNotFoundException {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
//                    list += i + 1 + ". " + taskList.get(i).toString() + "\n" ;
            list += taskList.get(i).saveFormat() + "\n";
        }
        Storage.writeToFile(list);

    }
    static void list() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).toString());
        }
    }

    static void Done(String line){
        try {
            int numberList = Integer.valueOf(line.substring(5, line.length()));
            IndexOutOfRange(taskList.size(),  numberList);
            Task t = taskList.get(numberList - 1);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t.toString());
        }
        catch (NumberFormatException e) {
            UI.printNumberFormatException();
        }
        catch (IndexOutOfRangeException e) {
            UI.printIndexOutOfRangeException();
        }
    }

    static void EventTask(String line){
        try {
            if (line.contains("/")) {
                ContainsWord(line);
                int dividerPosition = line.indexOf("/");
                String taskDescription = line.substring(6, dividerPosition - 1);
                String extractDayTime = line.substring(dividerPosition + 4);
                Task t = new Event(taskDescription, extractDayTime);
                taskList.add(t);
            }else {
                String taskDescription = line.substring(6);
                Task t = new Event(taskDescription, "Day and Time not specified");
                taskList.add(t);
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");

        }
        catch (StringFormatException e) {
            UI.printStringFormatException();
        }
    }
    static void DeadlineTask(String line) {
        try {
            if (line.contains("/")) {
                ContainsWord(line);
                int dividerPosition = line.indexOf("/");
                String taskDescription = line.substring(9, dividerPosition - 1);
                String extractDay = line.substring(dividerPosition + 4);
                Task t = new Deadline(taskDescription, extractDay);
                taskList.add(t);
            }else {
                String taskDescription = line.substring(9);
                Task t = new Deadline(taskDescription, "Time not specified");
                taskList.add(t);
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");

        } catch (StringFormatException e) {
            UI.printStringFormatException();
        }
    }

    static void ToDoTask(String line) {
        String taskDescription = line.substring(5);
        Task t = new ToDo(taskDescription);
        taskList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
    }

    static void DeleteTask(String line) {
        try {
            int number = Integer.valueOf(line.substring(7, line.length()));
            IndexOutOfRange(taskList.size(), number);
            Task t = taskList.get(number - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(t.toString());
            taskList.remove(number - 1);
            System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
        } catch (NumberFormatException e) {
            UI.printNumberFormatException();
        } catch (IndexOutOfRangeException e) {
            UI.printIndexOutOfRangeException();
        }
    }
    static void InvalidTask(String line) {
        try {
            CheckEmpty(line);
            ContainsWord(line);
        } catch (EmptyException e) {
            UI.printEmptyException();
        } catch (StringFormatException e) {
            UI.printStringFormatException();
        }
    }








    static void ContainsWord(String description) throws StringFormatException {
        if ( !( description.equals("bye") || description.equals("list") || description.contains("/") ) ){
            throw new StringFormatException ();
        }
    }

    static void CheckEmpty(String description) throws EmptyException {
        if (description.isEmpty()){
            throw new EmptyException ();
        }
    }

    static void IndexOutOfRange(int size,  int number) throws IndexOutOfRangeException {
        if (number > size || number < 0) {
            throw new IndexOutOfRangeException();
        }


    }




}

