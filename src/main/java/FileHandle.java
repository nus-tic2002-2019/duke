import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandle {
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}

