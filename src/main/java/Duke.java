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
        System.out.println("Enter 'help' for a list of commands.");

        Scanner read = new Scanner(System.in);
        ArrayList<Task> userList = new ArrayList<Task>(); //store tasks
        Task task = null;

        boolean continueInput = true; //flag counter for switch case

        do {
            String input = read.nextLine();
            String inputCommand = input.split(" ")[0];
            switch (inputCommand) {
                case "bye":
                    continueInput = false;
                    break;

                case "help":
                    System.out.println("[1] todo <task>: Enter a task.");
                    System.out.println("[2] deadline <task> /by <date>: Enter a task with deadline.");
                    System.out.println("[3] event <task> /at <time or location>: Enter a task with date or location.");
                    System.out.println("[4] list: Display all tasks.");
                    System.out.println("[5] done <enter task number>: Mark a task as done.");
                    System.out.println("[6] bye: Exit application");
                    break;

                //case "bye":

                case "list":
                    System.out.println("Your current List of tasks:");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println(userList.get(i).toString());
                    }
                    break;

                case "done":
                    String[] split = input.split(" ");
                    int doneNum = Integer.parseInt(split[1]); //storing list number
                    //int doneNum = Integer.parseInt(input.substring(4));
                    task = userList.get(doneNum - 1);
                    task.markAsDone();
                    System.out.println("Done!");
                    break;

                case "todo":
                    try {
                        if (input.equals("todo")) {
                            throw new DukeChildException.nullDescription("Task description required!");
                        }
                        String todo = input.substring(4);
                        Todo todoTask = new Todo(todo);
                        userList.add(todoTask);
                        System.out.println("Okie dokie! Task added: " + System.lineSeparator() + todoTask.toString());
                    } catch (DukeChildException.nullDescription e) {
                        e.printStackTrace();
                    }
                    break;

                case "deadline":
                    String[] dsplit = input.split("/");
                    String deadTask = dsplit[0];
                    String deadBy = dsplit[1];

                    deadTask = deadTask.substring(9);
                    deadBy = deadBy.substring(3);
                    //System.out.println(deadTask);
                    //System.out.println(deadBy);

                    Deadline deadline = new Deadline(deadTask, deadBy);
                    userList.add(deadline);

                    System.out.println("Procrastination is forbidden. Deadline added: " + System.lineSeparator() + deadline.toString());
                    break;

                case "event":
                    String[] esplit = input.split("/");
                    String eventTask = esplit[0];
                    String eventAt = esplit[1];

                    eventTask = eventTask.substring(6);
                    eventAt = eventAt.substring(3);
                    //System.out.println(deadTask);
                    //System.out.println(deadBy);

                    Event event = new Event(eventTask, eventAt);
                    userList.add(event);

                    System.out.println("Don't you DARE come late. Event added: " + System.lineSeparator() + event.toString());
                    break;

                default:
                    try {
                        throw new DukeChildException.invalidInput("Invalid format! Enter 'help' for assistance");
                    } catch (DukeChildException.invalidInput e) {
                        e.printStackTrace();
                    }
                    continueInput = true;
            }
        }
        while (continueInput);
    }
}



//////////////////////////////////////OLD CODE////////////////////////////////////////////////////////////////////////
/*

            if (input.contains("help")) {
                System.out.println("[1] todo <task>: Enter a task.");
                System.out.println("[2] deadline <task> /by <date>: Enter a task with deadline.");
                System.out.println("[3] event <task> /at <time or location>: Enter a task with date or location.");
                System.out.println("[4] list: Display all tasks.");
                System.out.println("[5] done <enter task number>: Mark a task as done.");
                System.out.println("[6] bye: Exit application");
                input = read.nextLine();
            }

            if(input.contains("list")) {
                System.out.println("Your current List of tasks:");
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println(userList.get(i).toString());
                }
                input = read.nextLine();
                //System.out.println("List");
            }
            if (input.contains("bye")) {
                System.out.println("Bye");
                System.exit(0);
            }

            if (input.contains("done")) {
                String[] split = input.split(" ");
                int doneNum = Integer.parseInt(split[1]); //storing list number
                //int doneNum = Integer.parseInt(input.substring(4));
                task = userList.get(doneNum - 1);
                task.markAsDone();
                System.out.println("Done!");
                input = read.nextLine();
            }

            if(input.contains("todo")) {
                String todo = input.substring(4);
                Todo todoTask = new Todo(todo);
                userList.add(todoTask);

                System.out.println("Okie dokie! Task added: " + System.lineSeparator() + todoTask.toString());
                input = read.nextLine();
            }

            if (input.contains(("deadline"))) {
                String[] split = input.split("/");
                String deadTask = split[0];
                String deadBy = split[1];

                deadTask = deadTask.substring(9);
                deadBy = deadBy.substring(3);
                //System.out.println(deadTask);
                //System.out.println(deadBy);

                Deadline deadline = new Deadline(deadTask, deadBy);
                userList.add(deadline);

                System.out.println("Procrastination is forbidden. Deadline added: " + System.lineSeparator() + deadline.toString());
                input = read.nextLine();
            }

            if (input.contains("event")) {
                String[] split = input.split("/");
                String eventTask = split[0];
                String eventAt = split[1];

                eventTask = eventTask.substring(6);
                eventAt = eventAt.substring(3);
                //System.out.println(deadTask);
                //System.out.println(deadBy);

                Event event = new Event(eventTask, eventAt);
                userList.add(event);

                System.out.println("Don't you DARE come late. Event added: " + System.lineSeparator() + event.toString());
                input = read.nextLine();
            }

            if ((!input.contains("list")) &&
                    (!input.contains("bye")) &&
                    (!input.contains("done")) &&
                    (!input.contains("deadline")) &&
                    (!input.contains("event")) &&
                    (!input.contains("help"))) {
                task = new Task(input);
                userList.add(task);
                System.out.println("added:" + input);
                input = read.nextLine();
            }

            */