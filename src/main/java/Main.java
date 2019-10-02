import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String logo = " __        _        \n"
                + "|  _ \\ _   | | __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| || | || |   <  __/\n"
                + "|/ \\,||\\\\__|\n";

        // TODO Auto-generated method stub
        Task list[] = new Task[100];
        int counter = 0;
        String input;

        Scanner sc = new Scanner(System.in);

        System.out.println("____________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________");
        input = sc.nextLine();
        while(!input.equalsIgnoreCase("bye")) {
            System.out.println("________");
            try {
                String function = input.split(" ")[0];
                if (function.equalsIgnoreCase("list")) {
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i + 1 + "." + list[i].toString());
                    }
                } else if (function.equalsIgnoreCase("done")) {
                    int taskNo = Integer.parseInt(input.substring(5));
                    list[taskNo - 1].markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + list[taskNo - 1].getStatusIcon() + "] " + list[taskNo - 1].getDescription());
                } else if (function.equalsIgnoreCase("todo")) {
                    if (input.length() > 5) {
                        list[counter] = new Todo(input.substring(5));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(list[counter].toString());
                        counter++;
                        System.out.println("Now you have " + counter + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (function.equalsIgnoreCase("deadline")) {
                    if (input.length() > 9) {
                        list[counter] = new Deadline(input.substring(9, input.indexOf("/") - 1), input.substring(input.indexOf("/") + 3));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(list[counter].toString());
                        counter++;
                        System.out.println("Now you have " + counter + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (function.equalsIgnoreCase("event")) {
                    if (input.length() > 6) {
                        list[counter] = new Event(input.substring(6, input.indexOf("/") - 1), input.substring(input.indexOf("/") + 3));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(list[counter].toString());
                        counter++;
                        System.out.println("Now you have " + counter + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("________");
            input = sc.nextLine();

        }
        System.out.println("____________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________");
    }

}