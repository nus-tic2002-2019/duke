package STORAGE;
import java.io.*;


public class Storage {
    private File f;

    public static final String DEFAULT_FILE_PATH = "data/taskSheet.txt";

    public Storage () throws IOException {
        this(DEFAULT_FILE_PATH);
    }
    public Storage (String path) throws IOException {
        f = new File(path);
        if (f.createNewFile()) {
            System.out.println("\tnew file created!");
        } else {
            System.out.println("\tFile exists!");
        }
    }


}
