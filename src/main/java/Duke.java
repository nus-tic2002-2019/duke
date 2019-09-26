package main.java;

import java.util.Scanner;

public class Duke {
    private static Scanner input = new Scanner(System.in);
    private static Task[] tasks = new Task[100];
    private static int size = 0;

    public static void printTasks(Task[] tasks) {
        for (int i = 0; tasks[i] != null; i++) {
            System.out.format("%d: %s\n", i + 1, tasks[i]);
        }
    }

    public static void processTask(String inputStr) throws DukeUnknownException, DukeMissingDescException {
        String tempStr1, tempStr2;
        int delimiterIndex;
        tempStr1 = inputStr.split(" ", 2)[0];
        switch (tempStr1) {
            default:
                throw new DukeUnknownException();
            case "todo":
                tempStr1 = inputStr.replaceFirst("(todo)", "").strip();
                if (tempStr1.isEmpty() || tempStr1.isBlank())
                    throw new DukeMissingDescException("todo");
                tasks[size] = new ToDo(tempStr1);
                break;
            case "deadline":
                delimiterIndex = inputStr.indexOf("/by");
                tempStr1 = inputStr.substring(8, delimiterIndex).strip();   //start after deadline, end at start of /by
                if (tempStr1.isEmpty() || tempStr1.isBlank())
                    throw new DukeMissingDescException("deadline");
                tempStr2 = inputStr.substring(delimiterIndex + 3).strip();  //start of /by + length of "/by"
                tasks[size] = new Deadline(tempStr1, tempStr2);
                break;
            case "event":
                delimiterIndex = inputStr.indexOf("/at");
                tempStr1 = inputStr.substring(5, delimiterIndex).strip();   //start after event, end at start of /by
                if (tempStr1.isEmpty() || tempStr1.isBlank())
                    throw new DukeMissingDescException("event");
                tempStr2 = inputStr.substring(delimiterIndex + 3).strip();  //start of /at + length of "/at"
                tasks[size] = new Event(tempStr1, tempStr2);
                break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[size++].toString());   //increment size after printing the task added.
        System.out.println(String.format("Now you have %d %s in the list.", size, (size > 1) ? "tasks" : "task"));
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        while (true) {
            String inputStr;
            try {
                inputStr = input.nextLine().strip();
            } catch (Exception e) {
                System.out.println("Error parsing input. Try again.");
                continue;
            }
            String[] strings = inputStr.split(" ");
            String firstStr = strings[0].strip().toLowerCase();

            switch (firstStr) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    printTasks(tasks);
                    break;
                case "done":
                    int pos;
                    try {
                        pos = Integer.parseInt(strings[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("inputted index is not valid. Try again.");
                        continue;
                    }
                    System.out.println(String.format("Completed: %d", pos--));//shift pos back by one here after printing as arrays are zero indexed.
                    if (pos >= size) {
                        System.out.println("No such item in task list.");
                    } else if (tasks[pos].isDone()) {
                        System.out.println("Task is already done.");
                    } else {
                        tasks[pos].setDone(true);
                        System.out.println(String.format("Nice! I've marked this task as done:\n%s", tasks[pos]));
                    }
                    break;
                default:    //task processing.
                    try {
                        processTask(inputStr);
                    } catch (DukeMissingDescException e) {
                        System.out.println(String.format("OOPS!!! The description of %s cannot be empty.", e.getMessage()));
                    } catch (DukeUnknownException e) {
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    break;
            }
        }
    }
}
