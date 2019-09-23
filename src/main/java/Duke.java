
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import subclass.*;

public class Duke {

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void loadFile(String filePath) throws FileNotFoundException, ParseException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int num = 1;
        while (s.hasNext()) {
            String line_input = s.nextLine();
            //add todo, if any
            if (line_input.contains("T|")) {
                String input_items = line_input.substring(line_input.lastIndexOf("|") + 1);
                String task_status_num = line_input.substring(line_input.indexOf("|") + 1);
                Task.add_task(new Todo(input_items));
                if (task_status_num.contains("1")){
                    Task.markDone(num);
                }
            }
            //add deadline, if any
            if (line_input.contains("D|")) {
                String input_by = line_input.substring(line_input.lastIndexOf("|") + 1);
                String input_items = line_input.substring(line_input.indexOf("|") + 3, line_input.lastIndexOf("|"));
                String task_status_num = line_input.substring(line_input.indexOf("|") + 1);
                Task.add_task(new Deadline(input_items, input_by));
                if (task_status_num.contains("1")){
                    Task.markDone(num);
                }
            }
            //add event, if any
            if (line_input.contains("E|")) {
                String input_by = line_input.substring(line_input.lastIndexOf("|") + 1);
                String input_items = line_input.substring(line_input.indexOf("|") + 3, line_input.lastIndexOf("|"));
                String task_status_num = line_input.substring(line_input.indexOf("|") + 1);
                Task.add_task(new Event(input_items, input_by));
                if (task_status_num.contains("1")){
                    Task.markDone(num);
                }
            }
            num++;
        }
    }

    public static String toTxt (String outputs) {
        String newOutput = outputs.replace("[", "");
        newOutput = newOutput.replace("]", ",");
        newOutput = newOutput.replace("(", ",");
        newOutput = newOutput.replace(")", "");
        newOutput = newOutput.replace("\u2713", "1");
        newOutput = newOutput.replace("\u2718", "0");
        newOutput = newOutput.replace("by:", "");
        newOutput = newOutput.replace("at:", "");
        newOutput = newOutput.replace(", ", ",");
        newOutput = newOutput.replace("  ,", ",");
        newOutput = newOutput.replace(",", "|");
        newOutput = newOutput.replace(" |", "|");
        return newOutput;
    }

    public static void main(String[] args) throws DukeException, todoException, IOException, ParseException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);
        String file_path = "C:\\Users\\marcus.ng\\Desktop\\m\\m\\NUS\\TIC2002 Introduction to Software Engineering\\duke\\src\\main\\java\\taskList.txt";
        try {
            loadFile(file_path);
        } catch (ParseException e) {
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }


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
                        System.out.println("\t_________________________________________");
                        System.out.println("\tGot it. I've added this task:");
                        System.out.println("\t\t" + newTask.add_task(new Todo(input_items)));
                        //word_count++;
                        System.out.println("\tNow you have " + newTask.word_count + " tasks in list.");
                        System.out.println("\t_________________________________________");

                    }
                    //add deadline
                    if (!line.contains("list") && !line.contains("done") && !line.contains("delete") && line.contains("deadline")) {
                        String input_items = line.substring(line.indexOf(" ") + 1);
                        String by = line.substring(line.indexOf("/by") + 4);
                        String input_string = input_items.substring(0, input_items.indexOf("/")-1);
                        Task tmp = newTask.add_task(new Deadline(input_string, by));
                        System.out.println("\t_________________________________________");
                        System.out.println("\tGot it. I've added this task:");
                        System.out.println("\t\t" + tmp);
                        System.out.println("\tNow you have " + newTask.word_count + " tasks in list.");
                        System.out.println("\t_________________________________________");

                    }
                    //add event
                    if (!line.contains("list") && !line.contains("done") && line.contains("event")) {
                        String input_items = line.substring(line.indexOf(" ") + 1);
                        String at = line.substring(line.indexOf("/at") + 4);
                        String input_string = input_items.substring(0, input_items.indexOf("/")-1);
                        Task tmp = newTask.add_task(new Event(input_string, at));
                        System.out.println("\t_________________________________________");
                        System.out.println("\tGot it. I've added this task:");
                        System.out.println("\t\t" + tmp);
                        System.out.println("\tNow you have " + newTask.word_count + " tasks in list.");
                        System.out.println("\t_________________________________________");

                    }

                    //displaying list
                    if (line.contains("list")) {
                        System.out.println("\t_________________________________________");
                        System.out.println(newTask.getList());
                        System.out.println("\t_________________________________________");
                    }
                    //mark done
                    if (line.contains("done")) {

                        String input_items = line.substring(line.indexOf(" ") + 1);
                        int task_option = Integer.parseInt(input_items);
                        Task tmp = newTask.markDone(task_option);
                        System.out.println("\t_________________________________________");
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t\t" + tmp);
                        System.out.println("\t_________________________________________");
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

                    System.out.println("\t______________________________________________________________");
                    System.out.println("\t☹ OOPS!!! The description of a deadline/event cannot be empty.");
                    System.out.println("\t______________________________________________________________");

                } catch (todoException e) {

                    System.out.println("\t_______________________________________________________");
                    System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("\t_______________________________________________________");

                } catch (IndexOutOfBoundsException e) {

                    System.out.println("\t_______________________________________________________");
                    System.out.println("\t☹ OOPS!!! There is no item.");
                    System.out.println("\t_______________________________________________________");

                } catch (ParseException e) {
                    System.out.println("\t_________________________________________________________");
                    System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("\tMake sure to enter date in format: dd-MMM-yy hh:mm PM/AM");
                    System.out.println("\t_________________________________________________________");

                }

                line = in.nextLine();

            }
        //save Task.txt
        writeToFile(file_path, toTxt(Task.getOutput()));

        //exit
        System.out.println("\t_________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t_________________________________________");
    }
}
