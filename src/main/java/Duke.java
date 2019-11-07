import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line;
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?");

        while (true) {
            line = in.nextLine();

            if (line.equals("bye"))  {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i).toString());
                }
            } else if (line.contains("done")) {
                try {
                    int numberList = Integer.valueOf(line.substring(5, line.length()));
                    IndexOutOfRange(taskList.size(),  numberList);
                    Task t = taskList.get(numberList - 1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.toString());
                }
                catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                catch (IndexOutOfRangeException e) {
                    System.out.println("☹ OOPS!!! Out of Range!");
                }

            } else if (line.contains("event")) {
                try {
                    ContainsWord(line);
                    int dividerPosition = line.indexOf("/");
                    String taskDescription = line.substring(6, dividerPosition - 1);
                    String extractDayTime = line.substring(dividerPosition + 4);
                    Task t = new Event(taskDescription, extractDayTime);
                    taskList.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + taskList.get(taskList.size()-1).toString());
                    System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");

                }
                catch (StringFormatException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } else if (line.contains("deadline")) {
                try {
                    ContainsWord(line);
                    int dividerPosition = line.indexOf("/");
                    String taskDescription = line.substring(9, dividerPosition - 1);
                    String extractDay = line.substring(dividerPosition + 4);
                    Task t = new Deadline(taskDescription, extractDay);
                    taskList.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + taskList.get(taskList.size()-1).toString());
                    System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");

                }
                catch (StringFormatException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } else if (line.contains("todo")) {
                String taskDescription = line.substring(5);
                Task t = new ToDo(taskDescription);
                taskList.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + taskList.get(taskList.size()-1).toString());
                System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");

            }

            else if (line.contains("delete")){
                try {
                    int number = Integer.valueOf(line.substring(7, line.length()));
                    IndexOutOfRange(taskList.size(),  number);
                    Task t = taskList.get(number - 1);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(t.toString());
                    taskList.remove(number - 1);
                    System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
                }
                catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                catch (IndexOutOfRangeException e) {
                    System.out.println("☹ OOPS!!! Out of Range!");
                }
            }else{
                try {
                    CheckEmpty(line);
                    ContainsWord(line);
                }
                catch (EmptyException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                catch (StringFormatException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            }

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

