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
public class Storage{

    private static String filename;

    public Storage(String filename){
        this.filename = filename;
    }

    /**
     * This method save user's task list to the hard disk.
     * @param t : this is the data structure of user tasks.
     */
    public static void saveFile(ArrayList<Task> t){
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            for( int i=0 ; i<t.size() ; i++ ){
                // convert [T][X]Task/by:2019-12-31T23:59 to T|0|Task|2019-12-31T23:59
                String s = t.get(i).toString().replace("√","1" ).
                                               replace("X","0").
                                               replaceAll("\\[","").
                                               replaceAll("]","|").
                                               replace("(by:","|").
                                               replace("(at:","|").
                                               replace(")","") +
                                               System.lineSeparator();
                byte b[]= s.getBytes();//converting string into byte array
                fout.write(b);
            }
            fout.close();
        } catch (IOException e){
            System.out.println("\tOops!! Cannot save file !");
        }
    }

    /**
     * This method load user's task list from hard disk to memory.
     * @param t : this is the data structure of user tasks.
     */
    public static void loadFile(ArrayList<Task> t){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            LocalDateTime resultDateTime = null;
            String loadString;
            Integer numberOfVerticalBar = 0;

            while ( (loadString = br.readLine()) != null ){

                // Example loadString = D|0|submit Java project|2018-01-30T14:30
                //   command[x] --> x = 0|1|         2         |        3

                String[] command = loadString.split("\\|",0);

                switch ( command[0] ){
                    case "T":
                        numberOfVerticalBar = loadString.indexOf("|",
                                      loadString.indexOf("|",
                                      loadString.indexOf("|")+1)+1);
                        assert numberOfVerticalBar == -1: numberOfVerticalBar;
                        t.add(new Todo(command[2].trim()));
                        if (command[1].equals("1")){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "E":
                        numberOfVerticalBar = loadString.indexOf("|",
                                      loadString.indexOf("|",
                                      loadString.indexOf("|",
                                      loadString.indexOf("|")+1)+1)+1);
                        assert numberOfVerticalBar == -1: numberOfVerticalBar;
                        resultDateTime = LocalDateTime.parse(command[3]);
                        t.add(new Event(command[2].trim(),resultDateTime));
                        if ( command[1].equals("1") ){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "D":
                        numberOfVerticalBar = loadString.indexOf("|",
                                      loadString.indexOf("|",
                                      loadString.indexOf("|",
                                      loadString.indexOf("|")+1)+1)+1);
                        assert numberOfVerticalBar == -1: numberOfVerticalBar;
                        resultDateTime = LocalDateTime.parse(command[3]);
                        t.add(new Deadline(command[2].trim(),resultDateTime));
                        if ( command[1].equals("1") ){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                }
            }
        } catch (IOException e){
            System.out.println("\tOops!! File not found! Generate new file.");
        }
    }
}
