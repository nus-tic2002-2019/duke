import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    String horizontal_line = ("____________________________________\n");
    public ArrayList<Task> list;
/*
    public TaskList () {
        this.list = new ArrayList<Task>();
    } */

    public void append_new_data (String FilePath, String textToAdd) throws IOException {
        FileWriter ft = new FileWriter(FilePath, true);
        PrintWriter fw = new PrintWriter(ft);
        fw.print(textToAdd);
        fw.close();
    }

    //Remove items from ArrayList (Not updated to txt File)
    public ArrayList<Task> delete_item(String FilePath , int del_index, ArrayList myArr_list) throws FileNotFoundException {
        File f = new File(FilePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        //String line ;

        int count = 0;
        while (s.hasNextLine()) {
            if ((count + 1) == del_index) {
                System.out.println(horizontal_line + "Noted. I've removed this task: " + '\n' + '\t' + myArr_list.get(del_index - 1)
                        + "\nNow you have " + (myArr_list.size() - 1) + " tasks in the list.\n" + horizontal_line);
                myArr_list.remove(count);
                break;
            } s.nextLine();
            count++;
        }
        return myArr_list;
    }

    /**
     * Add on the Todo Task input by Users into Array List and print out for display
     *
     * @param count  size of Array passed from main function.
     * @param line input by users
     *
     */
    public void append_Todo(ArrayList<Task> myArr_list, String line, int count) {
       // try {
            myArr_list.add(new Todo(line.substring(5))); //new
            System.out.println("todo" + myArr_list.get(count));

        //} catch (IndexOutOfBoundsException e) {
         //   System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        //}
    }

    /**
     * Add on the Deadlines Task input by Users into Array List and print out for display
     *
     * @param count  size of Array passed from main function.
     * @param line input by users
     *
     */
    public void append_Deadline (ArrayList<Task> myArr_list, String line, int count) {
        int close_bracket = line.indexOf(')');
        int position = line.indexOf("by");

        myArr_list.add(new Deadlines(line.substring(9, position - 1), (line.substring((position + 4), close_bracket)))); //new
    }

    /**
     * Add on the Event Task input by Users into Array List and print out for display
     *
     * @param count  size of Array passed from main function.
     * @param line input by users
     *
     */
    public void append_Event (ArrayList<Task> myArr_list, String line, int count, String FilePath) {
        //int position_slash = line.indexOf("/");
        //int position_time = line.indexOf(" ", position_slash);

        int position_slash = line.indexOf("by");
       // int position_time = line.indexOf(" ", position_slash);
        System.out.println(line.substring(6, position_slash - 1));
        System.out.println(line.substring(position_slash +3));
        myArr_list.add(new Event(line.substring(6, position_slash - 1), line.substring(position_slash +3))); //new

       // try {
       //     append_new_data(FilePath, myArr_list.get(count).toString() + System.lineSeparator());
       //     //writeToFile(FilePath, myArr_list.get(count).toString() + System.lineSeparator());
       // } catch (IOException e) {
       //     System.out.println("Something went wrong: " + e.getMessage());
       // }
    }

    /**
     * Edit content of the existing Task in Array List
     * Input format : edit 10 >> new content
     *
     * @param myArr_list  Array of Task passed over from main program
     * @param line input by users
     *
     */
    public void edit_data (ArrayList<Task> myArr_list, String line) {

        int position = Integer.parseInt(line.substring(5,7));
        int new_content = line.indexOf(">>");
        int position_by;
        Task contents = null;
        char task_type =  myArr_list.get(position-1).toString().charAt(1);
        System.out.println(task_type);

        switch (task_type) {
            case 'T' :
                contents = new Todo(line.substring(new_content + 3));
                break;
            case 'E' :
                position_by = line.indexOf("by");
                contents = new Event(line.substring(new_content+3, position_by- 1), line.substring(position_by +3));
                break;
            case 'D' :
                position_by = line.indexOf("by");
                int close_bracket = line.indexOf(')');
                contents = new Deadlines(line.substring(new_content+3, position_by - 1), (line.substring((position_by + 4), close_bracket)));
                break;
            default :
                System.out.println("Invalid Task Type");
                return;
        }

        System.out.println('\n' + horizontal_line + "Item before replace: " + myArr_list.get(position-1));
        myArr_list.set(position-1, contents);
        System.out.println("Item after Replace: " + myArr_list.get(position-1) + '\n' + horizontal_line);
    }

    private static String set_Task_done(Task myArr_list, int position) throws IOException {
        myArr_list.setDone();
        return (myArr_list.getStatusIcon());
    }

}
