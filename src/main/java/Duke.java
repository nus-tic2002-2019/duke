import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    static void CheckLineEmpty(String line)throws DukeCheckLineEmptyException{
        if (line.equals("")){
            throw new DukeCheckLineEmptyException();
        }
    }
    static void CheckWord(String line)throws DukeCheckLineException{
        String keyword = line.split(" ")[0].toLowerCase();
        if (!keyword.equals("list") && !keyword.equals("bye")
        && !keyword.equals("todo") && !keyword.equals("done")
        && !keyword.equals("event") && !keyword.equals("deadline")
                && !keyword.equals("delete")){
            throw new DukeCheckLineException();
        }
    }

    static void CheckDescription(String line)throws DukeException{
        String keyword = line.split(" ")[0].toLowerCase();

        if (keyword.equals("todo") && line.split(" ").length ==1) {
            throw new DukeException();
        }
        if (keyword.equals("event") && line.split(" ").length ==1) {
            throw new DukeException();
        }
        if (keyword.equals("deadline") && line.split(" ").length ==1) {
            throw new DukeException();
        }
        if (keyword.equals("done") && line.split(" ").length ==1) {
            throw new DukeException();
        }
        if (keyword.equals("delete") && line.split(" ").length ==1) {
            throw new DukeException();
        }
    }



    public static void main(String[] args) throws DukeException, DukeCheckLineException, DukeCheckLineEmptyException {
    //public static void main(String[] args)  {
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
            String keyword = line.split(" ")[0].toLowerCase();

            try{
                CheckLineEmpty(line);
                CheckWord(line);
                CheckDescription(line);

                if (line.toLowerCase().equals("list")) {
                    split_line();
                    System.out.println("    Here are the task in your list: ");
                    for (int i = 0; i < item.size(); i++) {
                        System.out.println("    " + (i + 1) + "." + item.get(i));
                    }
                    split_line();
                }
                else if (keyword.equals("done")) {
                    split_line();
                    try {
                        Task markItem = item.get((Integer.parseInt(line.split(" ")[1]) - 1));
                        markItem.markAsDone();
                        System.out.println("    Nice! I've marked this task as done:" + System.lineSeparator() + "      " + markItem);
                    } catch (NumberFormatException e) {
                        System.out.println("    ☹ OOPS!!! This is not a number: " + line.split(" ")[1]);
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("    ☹ OOPS!!! The index out of bound: " + line.split(" ")[1]);
                    }
                    split_line();
                }
                else if (keyword.equals("delete")){
                    split_line();
                    try{
                        Task deleteItem = item.get((Integer.parseInt(line.split(" ")[1]) - 1));
                        item.remove(deleteItem);
                        System.out.println("    Noted. I've removed this task: " + System.lineSeparator() + "    " + deleteItem);
                        System.out.println("    Now you have " + item.size() + " task in the list. ");
                    }catch (NumberFormatException e){
                        System.out.println("    ☹ OOPS!!! This is not a number: " + line.split(" ")[1]);
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("    ☹ OOPS!!! The index out of bound: " + line.split(" ")[1]);
                    }
                    split_line();

                }
                else if (keyword.equals("todo")) {

                    Task todoTask = new Todo(line.replace(keyword + " ", ""));
                    item.add(todoTask);
                    split_line();
                    System.out.println("    Got it. I've added this task: ");
                    System.out.println("     " + todoTask.toString());
                    System.out.println("    Now you have " + item.size() + " task in the list. ");
                    split_line();

                }
                else if (keyword.equals("deadline")) {

                    int position = line.indexOf("/");
                    String time = line.split("/")[1].replace("by ", "");
                    Task deadlineTask = new Deadline(line.substring(9, position - 1), time);
                    item.add(deadlineTask);
                    split_line();
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + deadlineTask.toString());
                    System.out.println("    Noe you have " + item.size() + " task in the list.");
                    split_line();

                }
                else if (keyword.equals("event")) {

                    int position = line.indexOf("/");
                    String time = line.split("/")[1].replace("at ", "");
                    Task eventTask = new Event(line.substring(6, position - 1), time);
                    item.add(eventTask);
                    split_line();
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + eventTask.toString());
                    System.out.println("    Now you have " + item.size() + " task in the list");
                    split_line();

                }
                else if (line.toLowerCase().equals("bye")){
                    split_line();
                    System.out.println("    Bye. Hope to see you again soon!");
                    split_line();
                }

            }
            catch (DukeCheckLineException e){
                split_line();
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                split_line();
            }
            catch (DukeCheckLineEmptyException e){
                split_line();
                System.out.println("    ☹ OOPS!!! Please enter somethings.");
                split_line();
            }
            catch (DukeException e){
                split_line();
                System.out.println("    ☹ OOPS!!! The description of a " + keyword + " cannot be empty.");
                split_line();
            }


        }while(!line.equals("bye"));

    }


    public static void split_line(){
        System.out.println("    ________________________");
    }
}