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
        Task[] taskList = new Task[100];
        Scanner in = new Scanner(System.in);
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?");
        int index = 0;

        while (true) {
            line = in.nextLine();

            if (line.equals("bye"))  {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1 + ". " + taskList[i].toString());
                }
            } else if (line.contains("done")) {
                try {
                    int numberList = Integer.valueOf(line.substring(5, line.length()));
                    IndexOutOfRange(index+1,  numberList);
                    Task t = taskList[numberList - 1];
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
                    taskList[index] = new Event(taskDescription, extractDayTime);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + taskList[index].toString());
                    System.out.println("Now you have " + (index + 1) + " tasks in the list.");

                    index++;
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
                    taskList[index] = new Deadline(taskDescription, extractDay);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + taskList[index].toString());
                    System.out.println("Now you have " + (index + 1) + " tasks in the list.");

                    index++;
                }
                catch (StringFormatException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } else if (line.contains("todo")) {
                String taskDescription = line.substring(5);
                taskList[index] = new ToDo(taskDescription);

                System.out.println("Got it. I've added this task:");
                System.out.println(" " + taskList[index].toString());
                System.out.println("Now you have " + (index+1) + " tasks in the list.");

                index++;
            }else {
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

    static void IndexOutOfRange(int size,  int index) throws IndexOutOfRangeException {
        if (index >= size || index < 0) {
            throw new IndexOutOfRangeException();
        }
    }


}

