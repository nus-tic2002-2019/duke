//level 7.more oop
package storage;

/**
 * Class of storage for saving task inside hard disk
 */

import task.Deadlines;
import task.Events;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class Storage {

    private static String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * This method save user's task list to the hard disk at "src/main/java/data/Duke.txt"
     * @param t : this is the data structure of user tasks.
     */
    public static void saveFile(ArrayList<Task> t) {
        try {
            FileOutputStream fw = new FileOutputStream(filename);
            for( int i=0; i < t.size(); i++) {
                String s = t.get(i).toString().replace("√","1" ).replace("X","0").
                        replaceAll("\\[","").replaceAll("]","|").replace("(by:","|").
                        replace("(at:","|").replace(")","") + System.lineSeparator();
                byte b[] = s.getBytes();
                fw.write(b);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found! Duke.txt will be created in C:\\Temp folder");

        }
    }

    public static void loadFile(ArrayList<Task> t) {
        try {
            BufferedReader f = new BufferedReader(new FileReader(filename));
            String line;
            LocalDateTime resultDateTime = null;

            while ((line = f.readLine()) != null) {
                String[] line_arr = line.split("\\| ", 0);

                switch (line_arr[0]) {
                    case "T": // Task
                        t.add(new Todo(line_arr[2].trim()));
                        if (line_arr[1].equals("1")) {
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "D": // Deadline
                        //t.add(new Deadlines(line_arr[2].trim(), line_arr[3]));
                        resultDateTime = LocalDateTime.parse(line_arr[3]);
                        t.add(new Deadlines(line_arr[2].trim(), resultDateTime));
                        if (line_arr[1].equals("1")) {
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "E": // Event
                        //t.add(new Events(line_arr[2].trim(), line_arr[3]));
                        resultDateTime = LocalDateTime.parse(line_arr[3]);
                        t.add(new Events(line_arr[2].trim(), resultDateTime));
                        if (line_arr[1].equals("1")) {
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                }
            }


        } catch(IOException e) {
            System.out.println("File not found! Duke.txt will be created in C:\\Temp folder");

        }
    }


}

