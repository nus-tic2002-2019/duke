import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class TaskList {

    String horizontal_line = ("____________________________________\n");
    public ArrayList<Task> list;

    public void append_new_data (String FilePath, String textToAdd) throws IOException {
        FileWriter ft = new FileWriter(FilePath, true);
        PrintWriter fw = new PrintWriter(ft);
        fw.print(textToAdd);
        fw.close();
    }

    //Remove items from ArrayList (Not updated to txt File)
    public ArrayList<Task> delete_item(String filePath , int del_Index, ArrayList myArr_List) throws FileNotFoundException {

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int count = 0;

        while (s.hasNextLine()) {
            if ((count + 1) == del_Index) {
                System.out.println(horizontal_line + "Noted. I've removed this task: " + '\n' + '\t' + myArr_List.get(del_Index - 1)
                        + "\nNow you have " + (myArr_List.size() - 1) + " tasks in the list.\n" + horizontal_line);
                myArr_List.remove(count);
                break;
            }
            s.nextLine();
            count++;
        }
        return myArr_List;
    }

    /**
     * Add on the Todo Task input by Users into Array List and print out for display
     *
     * @param count  size of Array passed from main function.
     * @param line input by users
     *
     */
    public void append_Todo(ArrayList<Task> myArr_List, String line, int count) {
       // try {
            myArr_List.add(new Todo(line.substring(5))); //new
            System.out.println("todo" + myArr_List.get(count));

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
    public void append_Deadline (ArrayList<Task> myArr_List, String line, int count) {
        int close_Bracket = line.indexOf(')');
        int position = line.indexOf("by");

        myArr_List.add(new Deadlines(line.substring(9, position - 1), (line.substring((position + 4), close_Bracket)))); //new
    }

    /**
     * Add on the Event Task input by Users into Array List and print out for display
     *
     * @param count  size of Array passed from main function.
     * @param line input by users
     *
     */
    public void append_Event (ArrayList<Task> myArr_list, String line, int count, String FilePath) {

        int position_By = line.indexOf("by");
        // int position_time = line.indexOf(" ", position_slash);
        System.out.println(line.substring(6, position_By - 1));
        System.out.println(line.substring(position_By +3));
        myArr_list.add(new Event(line.substring(6, position_By - 1), line.substring(position_By +3))); //new

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
     * @param myArr_List  Array of Task passed over from main program
     * @param line input by users
     *
     */
    public void edit_data (ArrayList<Task> myArr_List, String line) {

        int position = 0;
        int new_Content = line.indexOf(">>");
        int position_By;

        if(line.charAt(6) != ' ') {
            position = Integer.parseInt(line.substring(5,7));
        } else {
            position = Integer.parseInt(line.substring(5,6));
        }

        Task contents = null;
        char taskType =  myArr_List.get(position-1).toString().charAt(1);

        switch (taskType) {

        case 'T' :
            contents = new Todo(line.substring(new_Content + 3));
            break;

        case 'E' :
            position_By = line.indexOf("by");
            contents = new Event(line.substring(new_Content+3, position_By- 1), line.substring(position_By +3));
            break;

        case 'D' :
            position_By = line.indexOf("by");
            int close_bracket = line.indexOf(')');
            contents = new Deadlines(line.substring(new_Content+3, position_By - 1), (line.substring((position_By + 4), close_bracket)));
            break;

        default :
            System.out.println("Invalid Task Type");
            return;

        }

        System.out.println('\n' + horizontal_line + "Item before replace: " + myArr_List.get(position-1));
        myArr_List.set(position-1, contents);
        System.out.println("Item after Replace: " + myArr_List.get(position-1) + '\n' + horizontal_line);
    }

    private static String set_Task_done(Task myArr_list, int position) throws IOException {
        myArr_list.setDone();
        return (myArr_list.getStatusIcon());
    }

}
