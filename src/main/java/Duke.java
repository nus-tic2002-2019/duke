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
                    System.out.println("Bye! I better see you again soon!");
                    continueInput = false;
                    break;

                case "help":
                    /*
                        Lists available commands for user.
                        Reason for implementing this: just in case future work interviews request to see this.
                     */
                    System.out.println("[1] todo <task>: Enter a task.");
                    System.out.println("[2] deadline <task> /by <date>: Enter a task with deadline.");
                    System.out.println("[3] event <task> /at <time or location>: Enter a task with date or location.");
                    System.out.println("[4] list: Display all tasks.");
                    System.out.println("[5] done <enter task number>: Mark a task as done.");
                    System.out.println("[6] bye: Exit application");
                    break;

                case "list":
                    System.out.println("__________________________________________________________________");
                    System.out.println("Your current List of tasks:");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println( (i+1) + ". " + userList.get(i).toString());
                    }
                    System.out.println("__________________________________________________________________");
                    break;

                case "done":
                    try {
                        if (input.equals("done")) {
                            throw new DukeChildException.invalidInput("Task number required!");
                        }
                        String[] split = input.split(" ");
                        int doneNum = Integer.parseInt(split[1]); //storing list number
                        //int doneNum = Integer.parseInt(input.substring(4));
                        task = userList.get(doneNum - 1);
                        task.markAsDone();

                        System.out.println("__________________________________________________________________");
                        System.out.println("Done!");
                        System.out.println("__________________________________________________________________");
                    } catch (DukeChildException.invalidInput e) {
                        e.printStackTrace();
                    }
                    break;

                case "todo":
                    try {
                        if (input.equals("todo")) {
                            throw new DukeChildException.nullDescription("Task description required!");
                        }
                        String todo = input.substring(4);
                        Todo todoTask = new Todo(todo);
                        userList.add(todoTask);
                        System.out.println("__________________________________________________________________");
                        System.out.println("Okie dokie! Task added: " + System.lineSeparator() + todoTask.toString());
                        System.out.println("__________________________________________________________________");
                    } catch (DukeChildException.nullDescription e) {
                        e.printStackTrace();
                    }
                    break;

                case "deadline":
                    try{
                        if (input.equals("deadline")){
                            throw new DukeChildException.nullDescription("Task description required!");
                        }
                        if (!input.contains("/by")){
                            throw new DukeChildException.nullDescription("Description has invalid format!");
                        }
                        if (input.equals("deadline /by")) {
                            throw new DukeChildException.nullDescription("Description has invalid format!");
                        }
                        if (input.split("/")[1].equals("by")) {
                            throw new DukeChildException.nullDescription("Date required!");
                        }
                        String[] dsplit = input.split("/");
                        String deadTask = dsplit[0];
                        String deadBy = dsplit[1];

                        deadTask = deadTask.substring(9);
                        deadBy = deadBy.substring(3);
                        //System.out.println(deadTask);
                        //System.out.println(deadBy);

                        Deadline deadline = new Deadline(deadTask, deadBy);
                        userList.add(deadline);

                        System.out.println("__________________________________________________________________");
                        System.out.println("Procrastination is forbidden. Deadline added: " + System.lineSeparator() + deadline.toString());
                        System.out.println("__________________________________________________________________");

                    } catch (DukeChildException.nullDescription e) {
                        e.printStackTrace();
                    }
                    break;

                case "event":
                    try {
                        if (input.equals("event")) {
                            throw new DukeChildException.nullDescription("Task description required!");
                        }
                        if (!input.contains("/at")) {
                            throw new DukeChildException.nullDescription("Description has invalid format!");
                        }
                        if (input.equals("event /at")) {
                            throw new DukeChildException.nullDescription("Description has invalid format!");
                        }
                        if (input.split("/")[1].equals("at")) {
                            throw new DukeChildException.nullDescription("Time or location required!");
                        }

                        String[] esplit = input.split("/");
                        String eventTask = esplit[0];
                        String eventAt = esplit[1];

                        eventTask = eventTask.substring(6);
                        eventAt = eventAt.substring(3);
                        //System.out.println(deadTask);
                        //System.out.println(deadBy);

                        Event event = new Event(eventTask, eventAt);
                        userList.add(event);

                        System.out.println("__________________________________________________________________");
                        System.out.println("Don't you DARE come late. Event added: " + System.lineSeparator() + event.toString());
                        System.out.println("__________________________________________________________________");

                    } catch (DukeChildException.nullDescription e) {
                        e.printStackTrace();
                    }
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

