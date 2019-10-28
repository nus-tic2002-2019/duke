import java.util.Scanner;

public class Duke {
    private static Task todoList = new Task();
    private static void printUI() {

    }
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
                    break;
                default:
                    System.out.println("I'm sorry, I didn\'t get that. :(");
                    break;
            }
            line = in.nextLine();
            choice = line.split(" ", 0);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

//        while(!line.equals("bye")){
//            // list
//            if (line.equals("list")) {
//                int i = 1;
//                if (todoList.getItemCount() == 0) {
//                    System.out.println("Your list is empty.");
//                } else {
//                    boolean[] status = todoList.getStatus();
//                    for (String item : todoList.getTask()) {
//                        if (item != null) {
//                            System.out.println(i + "." + "[" + (status[i - 1] ? "✓" : "✗") + "] " + item);
//                            i++;
//                        }
//                    }
//                }
//            // done
//            } else if (line.length() >= 4 && line.substring(0, 4).equals("done")) {
//                try {
//                    int itemNumber = Integer.parseInt(line.substring(5, line.length())) - 1;
//                    if (todoList.doTask(itemNumber) && todoList.getItemCount() > itemNumber) {
//                        // TODO: change task to an array of object class instead?
//                        String[] taskList = todoList.getTask();
//                        boolean[] taskStatus = todoList.getStatus();
//                        System.out.println("Nice! I've marked this task as done:");
//                        System.out.println("[" + (taskStatus[itemNumber] ? "✓" : "✗") + "] " + taskList[itemNumber]);
//                    } else {
//                        System.out.println("Unable to mark task as done.");
//                    }
//                } catch (NumberFormatException error) {
//                    System.out.println("Unable to mark task as done.");
//                } catch (StringIndexOutOfBoundsException error) {
//                    System.out.println("Unable to mark task as done.");
//                } catch (ArrayIndexOutOfBoundsException error) {
//                    System.out.println("Unable to mark task as done.");
//                }
//            // add task
//            } else {
//                System.out.println(line);
//                if (!todoList.setTask(line)) {
//                    System.out.println("Your task list is full, unable to add more task.");
//                }
//            }
//            line = in.nextLine();
//        }
//        System.out.println("Bye. Hope to see you again soon!");
//    }
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
