import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filepath;
    public File f;

    public Storage(String filepath) {
        try {
            this.f = new File(filepath);
            Scanner scan = new Scanner(f); // create a Scanner using the File as the source
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        // open the file
        System.out.println("full path: " + this.f.getAbsolutePath());
        System.out.println("file exists?: " + this.f.exists());
        System.out.println("Writing to file:");
        // print contents of file
    }

    public void updateFile(ArrayList<Task> tasks) throws IOException {
        // function takes an ArrayList of tasks. Iterate through the tasklist and write to the text file.
        System.out.println("Doing update file");
        try {
            FileWriter fw = new FileWriter(this.filepath);
            fw.write("Sample text to be added to text file");
            fw.close();
            for (int i = 0; i < tasks.size(); i++) {
                tasks.get(i);
            }
        }
        catch(IOException e){
            System.out.println("IO Exception found");
        }
    }
}
