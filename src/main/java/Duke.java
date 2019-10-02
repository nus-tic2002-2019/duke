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

        Scanner read = new Scanner(System.in);
        ArrayList<Task> userList = new ArrayList<Task>();
        Task task = null;

        String input = read.nextLine();
        while(true) {
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
                    (!input.contains("event"))) {
                task = new Task(input);
                userList.add(task);
                System.out.println("added:" + input);
                input = read.nextLine();
            }
        }
            /*

            if (s.contains("Bye")) {
                ByeDuke.byeDuke();
            }*/
    }



    /*

     */
}
