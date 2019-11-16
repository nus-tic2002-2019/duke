import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileUtils {
    private String path;
    private boolean appendToFile = false;

    public FileUtils(String filePath) {
        path = filePath;
    }

    public FileUtils(String filePath, boolean appendValue) {
        path = filePath;
        appendToFile = appendValue;
    }

    public void setAppend(boolean value) {
        appendToFile = value;
    }

    public void clearFile() throws IOException {
        FileWriter write = new FileWriter(path, appendToFile);
        PrintWriter print = new PrintWriter(write);
        print.printf("%s", "");
        print.close();
    }

    public void writeToFile(String data) throws IOException {
        FileWriter write = new FileWriter(path, appendToFile);
        PrintWriter print = new PrintWriter(write);
        print.printf("%s" + "%n", data);
        print.close();
    }
}
