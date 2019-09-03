
import java.util.Scanner;
import java.util.Arrays;
import subclass.*;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("\t_________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t_________________________________________");

        line = in.nextLine();

        while (!line.contains("bye")) {
           Task newTask = new Task(line);

            //add inputs into array
            if (!line.contains("list") && !line.contains("done")){
                //inputs[word_count] = line;
                newTask.add_task(new Task(line));
                System.out.println("\t_________________________________________");
                System.out.println("\tadded: " + line);
                System.out.println("\t_________________________________________");
                //word_count++;

            }
                //displaying list
            if (line.contains("list")) {

                newTask.getList();
            }

            if (line.contains("done")) {

                String[] input_items = line.split(" ");
                String[] new_array = new String[1];
                new_array[0] = input_items[1];
                String option = new_array[0];
                int task_option = Integer.parseInt(option);
                newTask.markDone(task_option);
            }

            line = in.nextLine();
        }
        //exit
        System.out.println("\t_________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t_________________________________________");
    }
}
