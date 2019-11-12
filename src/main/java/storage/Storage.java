//level 7.more oop
package storage;

import task.Deadlines;
import task.Events;
import task.Task;
import task.Todo;

import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class Storage {

    private String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public static void saveFile(ArrayList<Task> t) {
        try {
            FileOutputStream fw = new FileOutputStream("src/main/java/data/Duke.txt");
            for( int i=0; i < t.size(); i++) {
                String s = t.get(i).toString().replace("\u2713","1" ).replace("\u2718","0").
                        replaceAll("\\[","").replaceAll("]","|").replace("(by:","|").
                        replace("(at:","|").replace(")","") + System.lineSeparator();
                byte b[] = s.getBytes();
                fw.write(b);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found! A new file - Duke.txt will create");
        }
    }

    public static void LoadFile(ArrayList<Task> t) {
        try {
            BufferedReader f = new BufferedReader(new FileReader("src/main/java/data/Duke.txt"));
            String line;

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
                        t.add(new Deadlines(line_arr[2].trim(), line_arr[3]));
                        if (line_arr[1].equals("1")) {
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "E": // Event
                        t.add(new Events(line_arr[2].trim(), line_arr[3]));
                        if (line_arr[1].equals("1")) {
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                }
            }


        } catch(IOException e){
            System.out.println("unable to read from file");
        }
    }


}




/**


public class Storage {
    private String filename;
    public Storage(String filename) {
        this.filename = filename;
    }


    public static void saveFile(String filePath, String textToAdd, boolean isAppend)  throws IOException {
        FileWriter fw;
        if (isAppend==true)
        {
            fw = new FileWriter(filePath,true);
        }
        else {
            fw = new FileWriter(filePath);

        }
        fw.write(textToAdd);
        fw.write(System.getProperty( "line.separator"));

        fw.close();
    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeTasks()
    {
        try{
            if (taskItems.size() == 0) {
                saveFile(strFilePath, "", false);
            }
            else {
                for (int i = 0; i < taskItems.size(); i++) {
                    saveFile(strFilePath, taskItems.get(i).writeToFile(), i == 0 ? false : true);
                }
            }
        } catch (IOException e)
        {
            System.out.println("File not found! A new file - Duke.txt will create");
        }
    }



    public static void readTasksFromFile()
    {
        //String strFilePath ="data/Duke.txt";
        String [] line_arr;
        String line;
        int i=0;
        try {
            File f = new File(strFilePath); // create a File for the given file path

            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                line = s.nextLine();
                line_arr = line.split(" \\| ");
                i++;
                switch (line_arr[0].toUpperCase()) {
                    case "T": // Task
                        Duke.addTask(new Todo(line_arr[2]), false);
                        if (line_arr[1].equals("1")) {
                            markDone(i, false);
                        }
                        break;
                    case "D": // Deadline
                        Duke.addTask(new Deadlines(line_arr[2], line_arr[3]), false);
                        if (line_arr[1].equals("1")) {
                            markDone(i, false);
                        }
                        break;
                    case "E": // Event
                        Duke.addTask(new Events(line_arr[2], line_arr[3]), false);
                        if (line_arr[1].equals("1")) {
                            markDone(i, false);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("unable to read from file");
        }
    }

}
**/