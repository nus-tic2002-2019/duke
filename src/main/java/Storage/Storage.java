package Storage;

import Command.Command;
import Parser.Parser;
import Tasklist.*;

import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

import Exception.DukeException;
import java.io.IOException;


public class Storage {

    private static String strFilePath = "";

    public Storage (String strFileInput){
        this.strFilePath = strFileInput;
    }

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

    public ArrayList<Task> load() throws IOException
    {
        File f = new File(strFilePath);
        ArrayList<Task> t;

            if (!f.exists()) {
                t = new ArrayList<>();
            }

        t = readTasksFromFile();
        return t;
    }

    public static void save(TaskList tasks)
    {
        try{
            if (tasks.size() == 0) {
                saveFile(strFilePath, "", false);
            }
            else {
                for (int i = 0; i < tasks.size(); i++) {
                    saveFile(strFilePath, tasks.get(i).writeToFile(), i == 0 ? false : true);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Unable to write into file!!");
        }
    }


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
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }
}
