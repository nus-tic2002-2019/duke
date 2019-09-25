import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Duke {
    private static Task[] task = new Task[100];
    private static Integer taskNo = 0;
    private static ArrayList<Task> ArrayTask = new ArrayList<Task>();
    private static Integer listNo = 0;

    public static void addTask(Task t) {
        task[taskNo] = t;
        taskNo++;
        ArrayTask.add(t);
        listNo++;
    }

    public static void main(String[] args) {

        Message.welcomeMessage(); // Duke welcome message

        String bye_word = "bye";
        String user_input = "";
        int index = 0;

        String command = ""; // initialise command to loop while() until "bye"

        while (!command.equals(bye_word)) {
            Scanner in = new Scanner(System.in);
            user_input = in.nextLine();
            command = user_input.split(" ")[0].toLowerCase();
            switch (command) {
                case "list":
                    Message.listMessage(ArrayTask, ArrayTask.size());
                    break;

                case "todo":
                    if (ErrType.TaskCheck(user_input)) {
                        addTask(new Todo(user_input.replace("todo", "").trim()));
                        Message.acknowledgeMessage(ArrayTask, ArrayTask.size());
                    }
                    break;

                case "deadline":
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                        addTask(new Deadline(user_input.split("/")[0].replace("deadline ", ""),
                                user_input.split("/")[1].replace("by", "")));
                        Message.acknowledgeMessage(ArrayTask, ArrayTask.size());
                    }
                    break;

                case "event":
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                        addTask(new Event(user_input.split("/")[0].replace("event ", ""),
                                user_input.split("/")[1].replace("at", "")));
                        Message.acknowledgeMessage(ArrayTask, ArrayTask.size());
                    }
                    break;

                case "done":
                    index = ErrType.toInteger(user_input.split(" ")[1], ArrayTask.size()); // with Exceptions handling
                    if (index == -1) {
                        System.out.println("\tPlease key a valid task number.");
                        break;
                    }
                    Message.doneMessage(ArrayTask, index);
                    break;

                case "delete":
                    index = ErrType.toInteger(user_input.split(" ")[1], ArrayTask.size()); // with Exceptions handling
                    if (index == -1) {
                        System.out.println("\tPlease key a valid task number.");
                        break;
                    }
                    Message.deleteMessage(ArrayTask, index);
                    break;

                case "save":
                    try {
                        FileOutputStream fout = new FileOutputStream("D:\\git\\output.txt");
                        //ObjectOutputStream oos = new ObjectOutputStream(fos);
                        //oos.writeObject(ArrayTask.get(1));
                        //oos.close();
                        for( Integer i=0 ; i<ArrayTask.size() ; i++){
                            String s = ArrayTask.get(i).toString().replace("\u2713","1" ).replace("\u2718","0").
                                       replaceAll("\\[","").replaceAll("]","|") + System.lineSeparator();
                            byte b[]= s.getBytes();//converting string into byte array
                            fout.write(b);
                        }
                        fout.close();
                        System.out.println("\tFile saved successfully.");
                    } catch (IOException e) {
                        System.out.println("File not found");
                    } finally {
                        System.out.println("\tPlease continue.");
                    }
                    break;

                case "load":
                    try {
                        FileInputStream  fin = new FileInputStream("D:\\git\\output.txt");
                        FilterInputStream fread = new BufferedInputStream(fin);
                        int k =0;
                        while((k=fread.read())!=-1){
                            System.out.print((char)k);
                        }
                        fin.close();
                        fread.close();
                    } catch (IOException e) {
                        System.out.println("File not found");
                    } finally {
                        System.out.println("\tPrintout is for information only.File cannot merge with existing task.");
                    }
                    break;

                case "bye": // "bye" command will end loop after looping back to while()
                    break;

                default:   // any other command will be considered as error
                    System.out.println("\tOops!! You have key an invalid command.");
                    break;
            }
        }
        Message.byeMessage();  // */
    }
}