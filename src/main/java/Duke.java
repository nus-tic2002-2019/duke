import  java.util.Scanner;

public class Duke {
    protected static int size = 0;
    protected static Task[] task = new Task[1000];

    private static void filterTask(Task t) {
        if (size == 0) {
            Task.addTask(task, size, t);
            size++;
        } else if (!checkList(t.getDescription(), size)) {
            Task.addTask(task, size, t);
            size++;
        } else {
            System.out.println("added: " + t.getDescription());
        }
    }

    private static boolean checkList(String arg, int size) {
        int i = 1;

        for (Task content : task) {
            if (i > size) {return false;}
            if (content.getDescription().equals(arg)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            Task t = new Task(line);

            if (line.startsWith("done")) {
                int taskItem = Integer.parseInt(line.substring(5));
                Task.doneTask(task, taskItem);
            } else if (line.equals("list")) {
                Task.listTask(task, size);
            } else if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                filterTask(t);
            }
        }
    }

}
