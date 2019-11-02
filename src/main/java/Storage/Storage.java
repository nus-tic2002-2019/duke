package Storage;

import Command.Command;
import Parser.Parser;
import Tasklist.Task;
import Tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.DukeException;


public class Storage {

    private static String strFilePath = "";

    /**
     *
     * @param strFileInput
     */
    public Storage (String strFileInput){
        this.strFilePath = strFileInput;
    }

    /**
     *
     * @return
     */
    public ArrayList<Task> load()
    {
        File f = new File(strFilePath);
        ArrayList<Task> t;
        if (!f.exists()){
             t = new ArrayList<>();
        }
        t = readTasksFromFile();
        return t;
    }

    /**
     * Returns an List of Task object.
     *
     * <p>
     * This method read the Task entries from file.
     * path provided to the class. When it fails to read the file it will
     * throw exception.
     *
     * @return the list of Task object
     */
    public static ArrayList<Task> readTasksFromFile()
    {
        TaskList tasks = new TaskList();
        ArrayList<Task> t = new ArrayList<>();

        String line;
        try {
            File f = new File(strFilePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                line = s.nextLine();
                Command c = Parser.parseFile(line);
                c.readfileformat(tasks);
            }
        }
        catch (DukeException | IOException e)
        {
            System.out.println(e.getMessage());
        }
        return tasks.getTaskitems();
    }

    /**
     * <p>
     * This method save the Task entries to the file. Path of the file is provided
     * to the constructor of this object.
     *
     * @return none
     */
    public static void save(TaskList tasks)
    {
        try{
            if (tasks.size() == 0) {
                saveFile(strFilePath, "", false);
            }
            else {
                for (int i = 0; i < tasks.size(); i++) {
                    //System.out.println("Writing .. " + taskitems.get(i).writeToFile());
                    saveFile(strFilePath, tasks.get(i).writeToFile(), i == 0 ? false : true);
                }
            }
        } catch (IOException e)
        {
            System.out.println("unable to write into file!!");
        }
    }

    /**
     * <p>
     * This method write a line of string to a path, and takes in
     * parameter on the file path, one line of string of words, and indication
     * whether to append the line of string to existing file
     *
     * @param filePath an absolute URL giving the base location of the image
     * @param texttoadd one line/string of words to be written to the file.
     * @param isappend to indicate whether to append or overwrite the file.
     * @throws IOException
     */
    public static void saveFile(String filePath, String texttoadd , boolean isappend)  throws IOException
    {
        FileWriter fw;

        if (isappend==true)
        {
            fw = new FileWriter(filePath,true);
        }
        else {
            fw = new FileWriter(filePath);
        }
        fw.write(texttoadd);
        fw.write(System.getProperty( "line.separator"));

        fw.close();
    }

}
