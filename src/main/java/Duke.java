import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Task todoList = new Task();
    private static Parser parse = new Parser();
    private static FileUtils file = new FileUtils("/Users/Dax/Desktop/school/java/2019/sem1/duke/src/main/save.txt");

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String choice = parse.getChoice(line);
        System.out.println(choice);
        try {
            while (!choice.equals("bye")) {
                switch (choice) {
                    case "hello":
                    case "hi":
                    case "hey":
                        System.out.println("Hey there! :)");
                        break;
                    case "list":
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
                            int itemNumber = parse.getItemNumber(line);
                            if (todoList.setDone(itemNumber, true)) {
                                System.out.println("____________________________________________________________");
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println("[✓] " + todoList.getTask(itemNumber).display());
                                System.out.println("____________________________________________________________");
                            }
                        } catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException error) {
                            throw new DukeException("Unable to mark task as done.");
                        }
                        break;
                    case "todo":
                    case "event":
                    case "deadline":
                        String description = parse.getBody(line);
                        if (choice.equals("todo")) {
                            todoList.addTodo(description);
                        } else if (choice.equals("event")) {
                            todoList.addEvent(description);
                        } else if (choice.equals("deadline")) {
                            todoList.addDeadline(description);
                        }
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(description);
                        System.out.println("Now you have " + todoList.getSize() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;
                    case "remove":
                    case "delete":
                        try {
                            int itemNumber = parse.getItemNumber(line);
                            Todo removedItem = todoList.getTask(itemNumber);
                            if (todoList.removeTask(itemNumber)) {
                                System.out.println("____________________________________________________________");
                                System.out.println("Noted. I've removed this task:");
                                System.out.println("[" + removedItem.getType() + "][" + (removedItem.getDone() ? "✓" : "✗") + "] " + removedItem.display());
                                System.out.println("Now you have " + todoList.getSize() + " tasks in the list.");
                                System.out.println("____________________________________________________________");
                            }
                        } catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException error) {
                            throw new DukeException("Unable to delete selection.");
                        }
                        break;
                    case "save":
                        try {
                            file.clearFile();
                            file.setAppend(true);
                            for (int i = 0; i < todoList.getSize(); i++) {
                                Todo item = todoList.getTask(i);
                                file.writeToFile(item.getType() + " | " + item.getDone() + " | " + item.getTodo());
                            }
                            file.setAppend(false);
                            System.out.println("Saved successfully.");
//                            T | 1 | read book
//                            D | 0 | return book | June 6th
//                            E | 0 | project meeting | Aug 6th 2-4pm
//                            T | 1 | join sports club
                        } catch (IOException error) {
                            throw new DukeException("Unable to save to file.");
                        }
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                line = in.nextLine();
                choice = parse.getChoice(line);
            }
            System.out.println("Bye. Hope to see you again soon!");
        } catch (DukeException e) {
            echo();
        }
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
