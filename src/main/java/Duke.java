
import java.util.Scanner;
import java.util.Arrays;
import subclass.*;

public class Duke {

    public static void main(String[] args) throws DukeException, todoException {
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
                try {
                    Task newTask = new Task(line);
                    //adding exceptions (in progress)
                    if (!line.contains("list") && !line.contains("done") && !line.contains("todo") && !line.contains("delete") && !line.contains("deadline") && !line.contains("event")) {
                        throw new DukeException();
                    }
                    //add inputs into array, add Todo
                    if (!line.contains("list") && !line.contains("done") && !line.contains("delete") && line.contains("todo")) {
                        String input_items = line.substring(line.indexOf(" ") + 1);
                        if (input_items.equals("todo")) {
                            throw new todoException();
                        }
                        newTask.add_task(new Todo(input_items));

                    }
                    //add deadline
                    if (!line.contains("list") && !line.contains("done") && !line.contains("delete") && line.contains("deadline")) {
                        String input_items = line.substring(line.indexOf(" ") + 1);
                        String by = line.substring(line.indexOf("/by") + 4);
                        String input_string = input_items.substring(0, input_items.indexOf("/"));
                        newTask.add_task(new Deadline(input_string, by));

                    }
                    //add event
                    if (!line.contains("list") && !line.contains("done") && line.contains("event")) {
                        String input_items = line.substring(line.indexOf(" ") + 1);
                        String at = line.substring(line.indexOf("/at") + 4);
                        String input_string = input_items.substring(0, input_items.indexOf("/"));
                        newTask.add_task(new Event(input_string, at));

                    }

                    //displaying list
                    if (line.contains("list")) {

                        newTask.getList();
                    }
                    //mark done
                    if (line.contains("done")) {

                        String input_items = line.substring(line.indexOf(" ") + 1);
                        int task_option = Integer.parseInt(input_items);
                        newTask.markDone(task_option);
                    }
                    //delete task
                    if (line.contains("delete")) {

                        String input_items = line.substring(line.indexOf(" ") + 1);
                        int task_option = Integer.parseInt(input_items);
                        newTask.removeTask(task_option);
                    }
                } catch (DukeException e) {

                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("\t_____________________________________________________________");

                } catch (StringIndexOutOfBoundsException e) {

                    System.out.println("\t_______________________________________________________");
                    System.out.println("\t☹ OOPS!!! The description of a deadline/event cannot be empty.");
                    System.out.println("\t_______________________________________________________");

                } catch (todoException e) {

                    System.out.println("\t_______________________________________________________");
                    System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("\t_______________________________________________________");
                }


                line = in.nextLine();

            }

        //exit
        System.out.println("\t_________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t_________________________________________");
    }
}
