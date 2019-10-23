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

        split_line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        split_line();
        ArrayList<Task> tasks = new ArrayList<Task>();
        int size = 0;

        chatting(tasks, size);
    }

    public static void split_line() {
        System.out.println("--------------------------------------------------------");
    }

    static void CheckFirstWord(String s) throws CommandException {
        if (!(s.equals("todo" ) || s.equals( "deadline") || s.equals( "event") || s.equals( "bye")
                || s.equals( "list") || s.equals( "done") || s.equals("delete")))
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

    static void CheckCommandDelete(int size, int index) throws CommandDeleteException{
        if (index >= size)
            throw new CommandDeleteException();
        if (index < 0)
            throw new CommandDeleteException();
    }

    static void CheckNullNumber (String s1,  String s2) throws NullNumberException {
        if (  (s1.equals("done") && s2.length() == 4) || (s1.equals("delete") && s2.length() == 8) )
            throw new NullNumberException();
    }

    public static void chatting(ArrayList<Task> tasks, int size) {
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
            CheckNullNumber(first_word, line);
        }
        catch (CommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            chatting(tasks, size);
        }
        catch (CommandAddException e) {
            System.out.println("☹ OOPS!!! Pls key in the description for the task");
            chatting(tasks, size);
        }
        catch (NullNumberException e) {
            System.out.println("☹ OOPS!!! Pls key in the number of the task");
            chatting(tasks, size);
        }

        if (line.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            split_line();
        } else if (line.toLowerCase().equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
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
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index).toString());
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("todo")) {
            Task t = new Todo(line.replace(s1[0] + " ", ""));
            tasks.add(t);
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("deadline")) {
            String time = s2[1].replace("by ", "");
            Task t = new Deadline(description, time);
            tasks.add(t);
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("event")) {
            String time = s2[1].replace("at ", "");
            Task t = new Event(description, time);
            tasks.add(t);
            size++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + t.toString());
            System.out.println("Now you have " + size + " tasks in the list.");
            split_line();
            chatting(tasks, size);
        } else if (first_word.equals("delete")) {
            int index = Integer.parseInt(line.substring(7)) - 1;
            try{
                CheckCommandDelete(size, index);
            }
            catch (CommandDeleteException e){
                System.out.println("☹ OOPS!!! The number of task is invalid! Please key in again!");
                chatting(tasks, size);
            }
            System.out.println("Noted. I've removed this task: ");
            System.out.println("  "  + tasks.get(index));
            tasks.remove(index);
            size--;
            System.out.println("Now you have " + size + " tasks in the list.");
            chatting(tasks, size);
        }
    }
}