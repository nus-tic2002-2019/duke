import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        split_line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        split_line();
        Task[] tasks = new Task[100];
        int size = 0;

        chatting(tasks, size);
    }

    public static void split_line() {
        System.out.println("--------------------------------------------------------");
    }

    public static void chatting(Task[] tasks, int size) {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] s1 = line.split(" ");
        String first_word = s1[0].toLowerCase();
        String[] s2 = line.split("/");
        String description = s2[0].replace(s1[0] + " ", "" );
        split_line();

        if(line.toLowerCase().equals ("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            split_line();
            return;
        }
        else if(line.toLowerCase().equals ("list")) {
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < size;  i++) {
                System.out.println(i+1 + "." + tasks[i].toString());
            }
            split_line();
            chatting(tasks, size);
        }
        else if(first_word.equals("done")) {
            int index = Integer.parseInt(line.substring(5)) - 1;
            tasks[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[index].toString());
            split_line();
            chatting(tasks, size);
        }
        else if(first_word.equals("todo")) {
            Task t = new Todo(line.replace(s1[0] + " ", ""));
            tasks[size] = t;
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        }
        else if(first_word.equals("deadline")) {
            String time = s2[1].replace("by ", "");
            Task t = new Deadline(description, time);
            tasks[size] = t;
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        }
        else if(first_word.equals("event")) {
            String time = s2[1].replace("at ", "");
            Task t = new Event(description, time);
            tasks[size] = t;
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        }
        else {
            System.out.println("added: " + line);
            Task t = new Task(line);
            split_line();
            tasks[size] = t;
            size++;
            chatting(tasks, size);
        }
    }
}