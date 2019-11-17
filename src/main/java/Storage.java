import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    //Declare variables
    private static String filePath;

    //Declare constant variables
    private static final String ERROR_MSG_FILE_NOT_EXIST = "File not found!\n";

    //Constructor
    public Storage(String filePath) {
        File f = new File(filePath);
        this.filePath = filePath;
        //System.out.println("Full path " + f.getAbsolutePath() );
    }

    //Getter
    public static ArrayList< ArrayList<String> > readFile() {
        ArrayList< ArrayList<String> > lineList = new ArrayList<>();

        File f = new File(filePath);

        try {
            Scanner s = new Scanner(f);

            while (s.hasNextLine() ) {
                lineList.add(readLine(s.nextLine() ) );
            }
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MSG_FILE_NOT_EXIST);
        }

        return lineList;
    }

    //Setter
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    public static void writeToFile(ArrayList<Task> tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasksList.size(); i++) {
            fw.write(tasksList.get(i).printToFile() + "\n");
        }

        fw.close();
    }

    //Function to read lines, for read file
    private static ArrayList<String> readLine(String currentLine) {
        ArrayList<String> lineElements = new ArrayList<>();

        Scanner s = new Scanner(currentLine);
        s.useDelimiter("[|]");

        while (s.hasNext() ) {
            lineElements.add(s.next() );
            lineElements.add(s.next() );
            lineElements.add(s.next() );
            if (s.hasNext() ) {
                lineElements.add(s.next() );
            }
        }

        s.close();

        return lineElements;
    }
}
