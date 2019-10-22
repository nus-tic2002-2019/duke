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

    static void CheckFirstWord(String s) throws CommandException {
        if (!(s.equals("todo" ) || s.equals( "deadline") || s.equals( "event")
                || s.equals( "bye") || s.equals( "list") || s.equals( "done")))
            throw new CommandException();
    }

    static void CheckCommandDone(int size,  int index) throws CommandDoneException {
        if (index >= size)
            throw new CommandDoneException();
        if (index < 0)
            throw new CommandDoneException();
    }

    static void CheckDescription (String s1,  String s2) throws CommandAddException {
        if (  (s1.equals("todo") && s2.length() == 4) ||
                (s1.equals("deadline") && s2.length() == 8) ||
                (s1.equals("event") && s2.length() == 5)  )
            throw new CommandAddException();
    }

    public static void chatting(Task[] tasks, int size) {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] s1 = line.split(" ");
        String first_word = s1[0].toLowerCase();
        String[] s2 = line.split("/");
        String description = s2[0].replace(s1[0] + " ", "");
        split_line();

        try{
            CheckFirstWord(first_word);
            CheckDescription(first_word, line);
        }
        catch (CommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            chatting(tasks, size);
        }
        catch (CommandAddException e){
            System.out.println("☹ OOPS!!! Pls key in the description for the task");
            chatting(tasks, size);
        }

        if (line.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            split_line();
        } else if (line.toLowerCase().equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println(i + 1 + "." + tasks[i].toString());
            }
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("done")) {
            int index = Integer.parseInt(line.substring(5)) - 1;
            try{
                CheckCommandDone(size, index);
            }
            catch (CommandDoneException e){
                System.out.println("☹ OOPS!!! The number of task is invalid! Please key in again!");
                chatting(tasks, size);
            }
            tasks[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[index].toString());
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("todo")) {
            Task t = new Todo(line.replace(s1[0] + " ", ""));
            tasks[size] = t;
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("deadline")) {
            String time = s2[1].replace("by ", "");
            Task t = new Deadline(description, time);
            tasks[size] = t;
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("event")) {
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
    }
}