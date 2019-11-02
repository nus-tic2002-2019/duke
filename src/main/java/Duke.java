import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    //public enum Input {
    //    List, Todo, Deadlines, Events, Delete
    //}

    private static void  OpenFile (String FilePath) throws FileNotFoundException {
        String horizontal_line = ("____________________________________\n");
        // pass the path to the file as a parameter
        File f = new File(FilePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String line ;
        int count = 1;
        while (s.hasNext()) {
            System.out.println(count + ". " +  s.nextLine());
            count ++;
        }
        System.out.println(horizontal_line);
    }
    //Read and Update File Data to Array
    private static int  Update_Arry_to_List (String FilePath, ArrayList my_Arr_List) throws FileNotFoundException {
        // pass the path to the file as a parameter
        File f = new File(FilePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String line ;

        int count = 0;
        while (s.hasNext()) {
            my_Arr_List.add(s.nextLine());
            count ++;
        }
        return count;
    }

    //Update Array to File
    private static void Update_New_Data_to_File(String FilePath, String TempPath, ArrayList my_Arr_List) throws IOException{
        File f = new File(FilePath);
        FileWriter f1 = new FileWriter(FilePath);
        File tem = new File(TempPath);
        FileWriter temp = new FileWriter(TempPath);

       for(int i=0; i< my_Arr_List.size(); i++) {
           temp.write(my_Arr_List.get(i).toString() + System.lineSeparator());
       }
       f1.close();
       temp.close();

       boolean successful = tem.renameTo(f);

    }

    private static void writeToFile(String FilePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FilePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void Print_input (int count, String input) {
        String horizontal_line = ("____________________________________\n");
        System.out.println(horizontal_line + "Got it. I've added this task: ");
        System.out.println(" " + input);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.\n" + horizontal_line);
    }

    private static void delete_item(String FilePath , int del_index, ArrayList myArr_list) throws FileNotFoundException {
        File f = new File(FilePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String line ;
        String horizontal_line = ("____________________________________\n");
        int count = 0;
        while (s.hasNext()) {
           if((count+1) == del_index ) {
               System.out.println(horizontal_line + "Noted. I've removed this task: " + '\n' +'\t' + myArr_list.get(del_index-1)
                       + "\nNow you have " + (myArr_list.size() - 1) + " tasks in the list.\n" + horizontal_line);
               myArr_list.remove(count);
           } else {
               s.nextLine();
           }
            count ++;
        }
        // Update New Array to Fil
    }



    public static void main(String[] args) {
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
        ArrayList <Task> myArr_list = new ArrayList<>();
        Task[] list = new Task[100];
        int count = 0;
        String FilePath = "/Users/laiping/Documents/Duke/todo_record.txt";
        String TempPath =  "/Users/laiping/Documents/Duke/temp.txt";

        try {
            count = Update_Arry_to_List(FilePath, myArr_list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        //String list_Arry[] = {};

       // String[] myArray = new String[100];
        //int arr_index = 0;

       // List<String> myArr_list = new ArrayList<String>();

       // Collection<String> myArr_list = new ArrayList<>();
     /*   for (int i=0; i<numberOfItems; i++ ) {
            System.out.println( myArray[i] );
        } */


        while (!line.equalsIgnoreCase("bye")) {
             if(line.equalsIgnoreCase("list")) {
                 System.out.println(horizontal_line + '\n' + "Here are the tasks in your list:");

                 try {
                     OpenFile ("/Users/laiping/Documents/Duke/todo_record.txt");
                 } catch (FileNotFoundException e) {
                     System.out.println("File not found");
                 }
              }
             else if ((line.toLowerCase().substring(0,4)).equals("done")){
                 try {
                     int position = Integer.parseInt(line.substring(5));
                     list[position - 1].setDone();
                     System.out.println(horizontal_line + "Nice! I've marked this task as done: ");
                     System.out.println('[' + list[position - 1].getStatusIcon() + "] " +
                             list[position - 1].description + '\n' + horizontal_line);
                 } catch (IndexOutOfBoundsException e) {
                     System.out.println( "☹ OOPS!!! The description of a done cannot be empty.");
                 }
             }
             else if (line.toLowerCase().contains("todo")) {
                 try {
                     myArr_list.add(new Todo(line.substring(5))); //new

                     try {
                        // writeToFile(FilePath, list[count].toString()+System.lineSeparator() );
                         writeToFile(FilePath, myArr_list.get(count) + System.lineSeparator() );
                     } catch (IOException e) {
                         System.out.println("Something went wrong: " + e.getMessage());
                     }
                     //Print_input (count, list[count].toString());
                     Print_input (count, myArr_list.get(count).toString());
                     count++;

                 } catch (IndexOutOfBoundsException e) {
                     System.out.println( "☹ OOPS!!! The description of a todo cannot be empty.");
                 }
             }
             else if (line.toLowerCase().contains("deadline")){
                 try {
                     int position = line.indexOf("/");
                     myArr_list.add(new Deadlines(line.substring(9, position - 1), line.substring(position + 4))); //new
                     //list[count] = new Deadlines(line.substring(9, position - 1), line.substring(position + 4));

                     try {
                         writeToFile(FilePath, myArr_list.get(count).toString() + System.lineSeparator() );
                     } catch (IOException e) {
                         System.out.println("Something went wrong: " + e.getMessage());
                     }
                     Print_input (count, myArr_list.get(count).toString());
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
                     Print_input (count, myArr_list.get(count).toString());
                     count++;
                 } catch (IndexOutOfBoundsException e) {
                     System.out.println( "☹ OOPS!!! The description of a event cannot be empty.");
                 }
             } else if(line.toLowerCase().contains("delete")) {
                 int delete_input = Integer.parseInt(line.substring(7)) ;
                 try {
                     delete_item(FilePath, delete_input, myArr_list);
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
