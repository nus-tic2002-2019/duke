package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represent class of saving and loading task list from the hard disk.
 */
public class Storage {

    private String filename;

    public Storage(String filename){
        this.filename = filename;
    }

    /**
     * This method save user's task list to the hard disk.
     * @param t : this is the data structure of user tasks.
     */
    public static void SaveFile(ArrayList<Task> t){
        try {
            FileOutputStream fout = new FileOutputStream("D:\\git\\output.txt");
            for( Integer i=0 ; i<t.size() ; i++){
                String s = t.get(i).toString().replace("\u2713","1" ).replace("\u2718","0").
                        replaceAll("\\[","").replaceAll("]","|").replace("(by:","|").
                        replace("(at:","|").replace(")","") + System.lineSeparator();
                byte b[]= s.getBytes();//converting string into byte array
                fout.write(b);
            }
            fout.close();
        } catch (IOException e) {
            System.out.println("Cannot save file !");
        }
    }

    /**
     * This method load user's task list from hard disk to memory.
     * @param t : this is the data structure of user tasks.
     */
    public static void LoadFile(ArrayList<Task> t){
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\git\\output.txt"));

            LocalDateTime resultDateTime = null;
            String s;

            while ((s = br.readLine()) != null){

                String[] command = s.split("\\|",0);

                switch (command[0]) {
                    case "T":
                        t.add(new Todo(command[2].trim()));
                        if (command[1].equals("1")){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "E":
                        resultDateTime = LocalDateTime.parse(command[3]);
                        t.add(new Event(command[2].trim(),resultDateTime));
                        if (command[1].equals("1")){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "D":
                        resultDateTime = LocalDateTime.parse(command[3]);
                        t.add(new Deadline(command[2].trim(),resultDateTime));
                        if (command[1].equals("1")){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("File not found! Generate new file.");
        }
    }
}
