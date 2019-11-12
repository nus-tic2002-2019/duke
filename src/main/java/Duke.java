import javax.imageio.stream.FileImageOutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

public class Duke {

    //public enum Input {
    //    List, Todo, Deadlines, Events, Delete
    //}

    static String horizontal_line = ("____________________________________\n");

    //Printing ArrayList from the File -- List Options
    private static void OpenFile (String FilePath) throws FileNotFoundException {
        // pass the path to the file as a parameter
        File f = new File(FilePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String line ;
        int count = 0;
        while (s.hasNextLine()) {
            System.out.println(count+1 + ". " +  s.nextLine());
            count ++;
        }
        System.out.println(horizontal_line);
    }

    //Printout whenever adding new stuff. >> Just printing for display
    private static void Print_input (int count, Task input) {
        System.out.println(horizontal_line + "Got it. I've added this task: ");
        System.out.println(" " + input);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.\n" + horizontal_line);
    }

    //Read and Update From File Data to Array
    private static ArrayList<Task> Update_Arry_to_List (String FilePath) throws IOException, ClassNotFoundException {

        ArrayList<Task> myArr_list = new ArrayList<>();
        File f = new File(FilePath);
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {
            String input = s.nextLine();
            char first_w = input.charAt(1);
            int index_position =0;
            switch (first_w) {
                case 'T' :
                    myArr_list.add(new Todo(input.substring(7)));
                    break;
                case 'E' :
                    index_position = input.indexOf("by");
                    myArr_list.add(new Event(input.substring(7, index_position-2), input.substring(index_position+3)));
                    break;
                case 'D' :
                    index_position = input.indexOf("by");
                    myArr_list.add(new Deadlines (input.substring(7, index_position-2), input.substring(index_position+3)));
                    break;
                default : continue;
            }
        }
        return  myArr_list;
    }

    //Update Array to File after delete some data
    private static void Update_New_Data_to_File(String FilePath, String TempPath, ArrayList my_Arr_List) throws IOException{
        File f = new File(FilePath);
        FileWriter f1 = new FileWriter(FilePath, false);
        File tem = new File(TempPath);
        FileWriter temp = new FileWriter(TempPath, false);

       for(int i=0; i< my_Arr_List.size(); i++) {
           temp.write(my_Arr_List.get(i) + System.lineSeparator());
       }
       f1.close();
       temp.close();

       boolean successful = tem.renameTo(f);
    }

    private static void writeToFile(String FilePath, String textToAdd) throws IOException {
        FileWriter ft= new FileWriter(FilePath, true);
        PrintWriter fw = new PrintWriter(ft);
        fw.print(textToAdd);
        fw.close();
    }

    //Remove items from ArrayList (Not updated to txt File)
    private static ArrayList<Task> delete_item(String FilePath , int del_index, ArrayList myArr_list) throws FileNotFoundException {
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

    private static String set_Task_done(Task myArr_list, int position) throws IOException {
        myArr_list.setDone();
        return (myArr_list.getStatusIcon());
    }



    public static void main(String[] args) throws FileNotFoundException {
        String horizontal_line = ("____________________________________\n");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal_line + "Hello I'm Duke\nWhat can I do for you? \n" + horizontal_line);

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();

        //Variables Definition
        ArrayList <Task> myArr_list = new ArrayList<>();
        String FilePath = "/Users/laiping/Documents/Duke/todo_record.txt";
        String TempPath =  "/Users/laiping/Documents/Duke/temp.txt";
        int count =0;

        try {
            myArr_list = Update_Arry_to_List(FilePath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found");
        }
        count = myArr_list.size();

        while (!line.equalsIgnoreCase("bye")) {
             if(line.equalsIgnoreCase("list")) {
                 System.out.println(horizontal_line + '\n' + "Here are the tasks in your list:");
                 try {
                     OpenFile (FilePath);
                 } catch (FileNotFoundException e) {
                     System.out.println("File not found");
                 }
              }
             else if ((line.toLowerCase().substring(0,4)).equals("done")){
                 try {
                     int position = Integer.parseInt(line.substring(5));
                     myArr_list.get(position-1).setDone();
                     myArr_list.add(myArr_list.get(position-1));
                     myArr_list.remove(position-1);

                     try {
                         Update_New_Data_to_File(FilePath, TempPath, myArr_list);
                     } catch (IOException e) {
                         System.out.println("File not found");
                     }

                     System.out.println(horizontal_line + "Nice! I've marked this task as done: ");
                     //System.out.println(myArr_list.get(position-1));
                    // System.out.println('[' + list[position - 1].getStatusIcon() + "] " +
                     //        list[position - 1].description + '\n' + horizontal_line);
                     System.out.println('[' + myArr_list.get(myArr_list.size()-1).getStatusIcon() + "] " +
                             myArr_list.get(myArr_list.size()-1).description + '\n' + horizontal_line);
                 } catch (IndexOutOfBoundsException e) {
                     System.out.println( "☹ OOPS!!! The description of a done cannot be empty.");
                 }
             }
             else if (line.toLowerCase().contains("todo")) {
                 try {
                     myArr_list.add(new Todo(line.substring(5))); //new

                     try {
                         System.out.println("todo" + myArr_list.get(count));
                         writeToFile(FilePath, myArr_list.get(count) + System.lineSeparator() );
                     } catch (IOException e) {
                         System.out.println("Something went wrong: " + e.getMessage());
                     }
                     Print_input (count, myArr_list.get(count));
                     count++;

                 } catch (IndexOutOfBoundsException e) {
                     System.out.println( "☹ OOPS!!! The description of a todo cannot be empty.");
                 }
             }
             else if (line.toLowerCase().contains("deadline")){
                 try {
                     int position = line.indexOf("/");
                     myArr_list.add(new Deadlines(line.substring(9, position - 1), line.substring(position + 4))); //new
                     //list[0] = new Deadlines(line.substring(9, position - 1), line.substring(position + 4));

                     try {
                         writeToFile(FilePath, myArr_list.get(count).toString() + System.lineSeparator() );
                     } catch (IOException e) {
                         System.out.println("Something went wrong: " + e.getMessage());
                     }
                     //Print_input (count, myArr_list.get(count).toString());
                     Print_input (count, myArr_list.get(count));
                     count++;
                 } catch (IndexOutOfBoundsException e) {
                     System.out.println( "☹ OOPS!!! The description of a deadline cannot be empty.");
                 }

             } else if (line.toLowerCase().contains("event")) {
                 try {
                     int position_slash = line.indexOf("/");
                     int position_time = line.indexOf(" ", position_slash);
                     myArr_list.add(new Event(line.substring(6, position_slash - 1), line.substring(position_slash + 4))); //new

                     try {
                         writeToFile(FilePath, myArr_list.get(count).toString() + System.lineSeparator() );
                     } catch (IOException e) {
                         System.out.println("Something went wrong: " + e.getMessage());
                     }
                     //Print_input (count, myArr_list.get(count).toString());
                     Print_input (count, myArr_list.get(count));
                     count++;
                 } catch (IndexOutOfBoundsException e) {
                     System.out.println( "☹ OOPS!!! The description of a event cannot be empty.");
                 }
             } else if(line.toLowerCase().contains("delete")) {
                 int delete_input = Integer.parseInt(line.substring(7)) ;
                 try {
                     myArr_list = delete_item(FilePath, delete_input, myArr_list);
                     try {
                         Update_New_Data_to_File(FilePath, TempPath, myArr_list);
                     } catch (IOException e) {
                         System.out.println("Something went wrong: " + e.getMessage());
                     }
                 } catch (FileNotFoundException e) {
                     System.out.println("File not found");
                 }

                  /* for(int delete_index = 1; delete_index <= myArr_list.size(); delete_index ++) {
                        if(delete_index == delete_input) {
                            System.out.println(horizontal_line + "Noted. I've removed this task: " + '\n' +'\t' + myArr_list.get(delete_index-1)
                                    + "\nNow you have " + (myArr_list.size() - 1) + " tasks in the list.\n" + horizontal_line);
                            myArr_list.remove(delete_index-1);
                        } */
             } else
             {
                 System.out.println(horizontal_line + "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + horizontal_line);
             }
             line = in.nextLine();
            //System.out.println("After adding elements collection value:"+ myArr_list);
        } System.out.println(horizontal_line + "Bye. Hope to see you again soon!" +'\n'+ horizontal_line);
    }
}

  /* System.out.println(horizontal_line + "Got it. I've added this task: ");
                     System.out.println(" " + list[count].toString());
                     System.out.println("Now you have " + (count + 1) + " tasks in the list.\n" + horizontal_line);
                     */


   /*
                 for (int i = 0; i < myArr_list.size(); i++) {//new
                     System.out.println(i + 1 + "." + myArr_list.get(i));
                     // System.out.println(i + 1 + "." +  list[i].toString());
                 }
                 System.out.println(horizontal_line);
                */
