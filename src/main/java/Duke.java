import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        split_line();
        System.out.println("    Hello from\n" + logo);
        split_line();
        System.out.println("    Hello! I'm Duke" + System.lineSeparator() + "    What can I do for you?");
        split_line();

        String line;
        Scanner in = new Scanner(System.in);
        List<Task> item = new ArrayList<>();

        do {
            line = in.nextLine();
            if (line.toLowerCase().equals("list")) {
                split_line();
                System.out.println("    Here are the task in your list: ");
                for (int i = 0; i < item.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + item.get(i));
                }
                split_line();
            } else if (line.split(" ")[0].toLowerCase().equals("done")) {
                Task markItem = item.get(Integer.parseInt(line.substring(5)) - 1);
                markItem.markAsDone();
                split_line();
                System.out.println("    Nice! I've marked this task as done:" + System.lineSeparator() + "      " + markItem);
                split_line();
            } else if (line.split(" ")[0].toLowerCase().equals("todo")) {
                Task todoTask = new Todo(line.replace(line.split(" ")[0] + " ", ""));
                item.add(todoTask);
                split_line();
                System.out.println("    Got it. I've added this task: ");
                System.out.println("     " + todoTask.toString());
                System.out.println("    Now you have " + item.size() + " task in the list. ");
                split_line();
            } else if (line.split(" ")[0].toLowerCase().equals("deadline")) {
                int position = line.indexOf("/");
                String time = line.split("/")[1].replace("by ", "");
                Task deadlineTask = new Deadline(line.substring(9, position - 1), time);
                item.add(deadlineTask);
                split_line();
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + deadlineTask.toString());
                System.out.println("    Noe you have " + item.size() + " task in the list.");
                split_line();
            } else if (line.split(" ")[0].toLowerCase().equals("event")) {
                int position = line.indexOf("/");
                String time = line.split("/")[1].replace("at ", "");
                Task eventTask = new Event(line.substring(6, position - 1), time);
                item.add(eventTask);
                split_line();
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + eventTask.toString());
                System.out.println("    Now you have " + item.size() + " task in the list");
                split_line();
            } else if (line.toLowerCase().equals("bye")){
                split_line();
                System.out.println("    Bye. Hope to see you again soon!");
                split_line();
            }
            else {
                Task task = new Task(line);
                item.add(task);
                split_line();
                System.out.println("    added:  " + task);
                split_line();
            }
        }while(!line.equals("bye"));

    }

    public static void split_line(){
        System.out.println("    ________________________");
    }
}