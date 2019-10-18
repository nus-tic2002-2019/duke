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
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1 + ". " + taskList[i].toString());
                }
            } else if (line.contains("done")) {
                int numberList = Integer.valueOf(line.substring(5, line.length()));
                Task t = taskList[numberList - 1];
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t.toString());

            } else if (line.contains("event")) {
                int dividerPosition = line.indexOf("/");
                String taskDescription = line.substring(6, dividerPosition-1);
                String extractDayTime = line.substring(dividerPosition + 4);
                taskList[index] = new Event(taskDescription, extractDayTime);

                System.out.println("Got it. I've added this task:");
                System.out.println(" " + taskList[index].toString());
                System.out.println("Now you have " + (index+1) + " tasks in the list.");

                index++;
            } else if (line.contains("deadline")) {
                int dividerPosition = line.indexOf("/");
                String taskDescription = line.substring(9, dividerPosition-1);
                String extractDay = line.substring(dividerPosition + 4);
                taskList[index] = new Deadline(taskDescription, extractDay);

                System.out.println("Got it. I've added this task:");
                System.out.println(" " + taskList[index].toString());
                System.out.println("Now you have " + (index+1) + " tasks in the list.");

                index++;
            } else if (line.contains("todo")) {
                String taskDescription = line.substring(5);
                taskList[index] = new ToDo(taskDescription);

                System.out.println("Got it. I've added this task:");
                System.out.println(" " + taskList[index].toString());
                System.out.println("Now you have " + (index+1) + " tasks in the list.");

                index++;
            }

        }

    }
}

