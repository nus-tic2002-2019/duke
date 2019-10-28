import java.util.Scanner;

public class Duke {
    private static Task todoList = new Task();
    
    private static void echo() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] choice = line.split(" ", 0);
        while (!choice[0].equals("bye")) {
            switch (choice[0].toLowerCase()) {
                case "list":
                    int i = 1;
                    if (todoList.getSize() == 0) {
                        System.out.println("Your list is empty.");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Here are the tasks in your list:");
                        todoList.printTaskList();
                        System.out.println("____________________________________________________________");
                    }
                    break;
                case "done":
                    try {
                        int itemNumber = Integer.parseInt(choice[1]) - 1;
                        if (todoList.setDone(itemNumber, true) && todoList.getSize() > itemNumber) {
                            // TODO: change task to an array of object class instead?
                            System.out.println("____________________________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[✓] " + todoList.getTask(itemNumber).display());
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("Unable to mark task as done.");
                        }
                    } catch (NumberFormatException error) {
                        System.out.println("Unable to mark task as done.");
                    } catch (StringIndexOutOfBoundsException error) {
                        System.out.println("Unable to mark task as done.");
                    } catch (ArrayIndexOutOfBoundsException error) {
                        System.out.println("Unable to mark task as done.");
                    }
                    break;
                case "todo":
                case "event":
                case "deadline":
                    try {
                        if (choice[0].toLowerCase().equals("todo")) {
                            todoList.addTodo(line.split(" ", 2)[1]);
                        } else if (choice[0].toLowerCase().equals("event")) {
                            todoList.addEvent(line.split(" ", 2)[1]);
                        } else if (choice[0].toLowerCase().equals("deadline")) {
                            todoList.addDeadline(line.split(" ", 2)[1]);
                        }
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(line.split(" ", 2)[1]);
                        System.out.println("Now you have " + todoList.getSize() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (Exception e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("____________________________________________________________");
                    }
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
            line = in.nextLine();
            choice = line.split(" ", 0);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo();
    }
}
