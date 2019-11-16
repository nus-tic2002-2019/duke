import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;


public class Duke {

    static String horizontal_line = ("____________________________________\n");

    public static void main(String[] args) { //throws FileNotFoundException {
        run();
    }

    //public static void main(String[] args) throws FileNotFoundException {
    public static void run() {
        Storage pFile = new Storage();
        Ui display = new Ui();
        Parser user_input = new Parser();
        TaskList update_input = new TaskList();

        //Variables Definition
        ArrayList<Task> myArr_list = new ArrayList<>();
        String FilePath = "/Users/laiping/Documents/Duke/todo_record.txt";
        String TempPath = "/Users/laiping/Documents/Duke/temp.txt";
        int count = 0;

        //First Initializing the Page and loading data from txt file
        display.welcome_note();
        try {
            myArr_list = pFile.load(FilePath);
        } catch (IOException e) {//| ClassNotFoundException e) {
            System.out.println("File not found");
        }


        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        count = myArr_list.size();

        String command = user_input.user_input(line);

        while (command != "bye") {

            switch (command) {

                case "list":
                    display.print_Arry(myArr_list);
                    break;

                case "find" : {
                    display.Print_Find_Result(myArr_list, line.substring(5));

                } break;

                case "done": {
                    try {
                        int position = Integer.parseInt(line.substring(5));
                        // (myArr_list.get(position-1)).setDone();
                        myArr_list.add(myArr_list.get(position - 1));
                        myArr_list.get(myArr_list.size() - 1).setDone();
                        myArr_list.remove(position - 1);

                        try {
                            pFile.Update_Arry_to_List(FilePath, TempPath, myArr_list);
                            //Update_New_Data_to_File(FilePath, TempPath, myArr_list);
                        } catch (IOException e) {
                            System.out.println("File not found");
                        }

                        System.out.println(horizontal_line + "Nice! I've marked this task as done: ");
                        //System.out.println(myArr_list.get(position-1));
                        // System.out.println('[' + list[position - 1].getStatusIcon() + "] " +
                        //        list[position - 1].description + '\n' + horizontal_line);
                        System.out.println('[' + myArr_list.get(myArr_list.size() - 1).getStatusIcon() + "] " +
                                myArr_list.get(myArr_list.size() - 1).description + '\n' + horizontal_line);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
                    }
                } break;

                case "todo": {
                    try {
                        update_input.append_Todo(myArr_list, line, count);
                        display.Print_input(count, myArr_list.get(count));
                        count ++;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } break;

                //deadline return book  (by: 2019-12-01)
                case "deadline": {
                    try {
                       update_input.append_Deadline(myArr_list, line, count);
                       /*
                        try {
                            update_input.append_new_data(FilePath, myArr_list.get(count).toString() + System.lineSeparator());
                            //writeToFile(FilePath, myArr_list.get(count).toString() + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        } */
                        display.Print_input(count, myArr_list.get(count));
                        count++;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } break;

                //event project meeting /at Mon 2-4pm
                case "event": {
                    try {
                        update_input.append_Event(myArr_list, line, count, FilePath);
                        display.Print_input(count, myArr_list.get(count));
                        count++;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                } break;

                case "delete": {
                    int delete_input = Integer.parseInt(line.substring(7));
                    try {
                        //myArr_list = delete_item(FilePath, delete_input, myArr_list);
                        myArr_list = update_input.delete_item(FilePath, delete_input, myArr_list);
                        try {
                            //Update_New_Data_to_File(FilePath, TempPath, myArr_list);
                            pFile.Update_Arry_to_List(FilePath, TempPath, myArr_list);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                    }
                } break;

                default:
                    System.out.println(horizontal_line + "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + horizontal_line);
                    break;
            }

            line = in.nextLine();
            command = user_input.user_input(line);
        }
        System.out.println(horizontal_line + "Bye. Hope to see you again soon!" +'\n'+ horizontal_line);

        try {
            pFile.Update_Arry_to_List(FilePath, TempPath, myArr_list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
