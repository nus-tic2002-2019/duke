package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    protected String fileName;
    protected File taskFile;

    public Storage (String fileName){
        this.fileName = fileName;
        taskFile = new File("fileName");
    }

    public void load() throws FileNotFoundException {
        Scanner loadTask = new Scanner(taskFile);
        //while (loadTask.hasNext())
    }
}
