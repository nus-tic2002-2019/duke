package duke;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileUtils {
    private static String path;
    private static boolean appendToFile = false;

    public FileUtils(String filePath) {
        path = filePath;
    }

    public FileUtils(String filePath, boolean appendValue) {
        path = filePath;
        appendToFile = appendValue;
    }

    public static void setPath(String filePath) {
        path = filePath;
    }

    public static void setAppend(boolean value) {
        appendToFile = value;
    }

    public static void clearFile() throws IOException {
        FileWriter write = new FileWriter(path, appendToFile);
        PrintWriter print = new PrintWriter(write);
        print.printf("%s", "");
        print.close();
    }

    public static void writeToFile(String data) throws IOException {
        FileWriter write = new FileWriter(path, appendToFile);
        PrintWriter print = new PrintWriter(write);
        print.printf("%s" + "%n", data);
        print.close();
    }
}
