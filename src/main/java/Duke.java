import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke");
        System.out.println("What can I do for you\n" + logo);

        Scanner in = new Scanner(System.in);
        System.out.print("");
        String line;
        //String result[] = new String[10];
        Task[] taskList = new Task[10];
        int counter = 0;
        line = in.nextLine();
        while (true) {
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (line.contains("done")) {
                int counter2 = Integer.valueOf(line.substring(5, line.length()));
                Task t = taskList[counter2 -1];
                t.markAsDone();
                System.out.println("Nice! I've marked this as done:");
                System.out.print("[" + t.getStatusIcon() + "]" + t.description +"\n");
                line = in.nextLine();
            }


            else if (line.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + "." + "[" + taskList[i].getStatusIcon() + "]" + taskList[i].description);
                }
                line = in.nextLine();
            }
            else if (!line.equals("list")) {
                //line = in.nextLine()
                Task newTask = new Task(line);
                taskList[counter] = newTask;
                newTask.description = line;
                System.out.println("added: " + line);
                //result[counter] = line;
                line = in.nextLine();
                counter++;
            }

        }
    }
}

