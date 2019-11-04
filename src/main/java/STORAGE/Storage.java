package STORAGE;
import java.io.*;


public class Storage {

    private static final String DEFAULT_NAME = "demo";

    private File file;

    public Storage () throws IOException {
        this(DEFAULT_NAME);
    }
    public Storage (String name) throws IOException {
        String path = "data/taskSheet_" + name + ".txt";
        file = new File(path);
    }

    public File get() {
        return file;
    }

}
