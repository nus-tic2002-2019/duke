import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    //Declare constant variables
    private static final String CHAR_FALSE = "0";
    private static final String CHAR_TRUE = "1";

    private static final String CHAR_TODO = "T";
    private static final String CHAR_DEADLINE = "D";
    private static final String CHAR_EVENT = "E";

    //Declare variables
    private static Ui currentUi;
    private static String filePath;
    private File currentFile;
    private ArrayList< ArrayList<String> > fileExtract;

    //Constructor
    public Storage(String filePath, Ui currentUi, ArrayList<Task> tasksList) {
        this.currentUi = currentUi;
        this.filePath = filePath;
        currentFile = new File(filePath);

        try {
            fileExtract = readFile();
        } catch (FileNotFoundException e) {
            currentUi.displayErrorFileNotFound();
        }

        appendTaskToArray(fileExtract, tasksList);
    }

    //Getter

    //Function to read lines, requisites for readFile function
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

    //Get array of lines in file
    public ArrayList< ArrayList<String> > readFile() throws FileNotFoundException {
        ArrayList< ArrayList<String> > lineList = new ArrayList<>();

        try {
            Scanner s = new Scanner(currentFile);

            while (s.hasNextLine() ) {
                lineList.add(readLine(s.nextLine() ) );
            }
        } catch (FileNotFoundException e) {
            currentUi.displayErrorFileNotFound();
        }

        return lineList;
    }

    //Setter
    //Append string to file
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    //Write string to file
    public static void writeToFile(ArrayList<Task> tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasksList.size(); i++) {
            fw.write(tasksList.get(i).printToFile() + "\n");
        }

        fw.close();
    }

    //Append task to file
    public static void appendTaskToFile(Task currentTask) {
        try {
            appendToFile(currentTask.printToFile() );
        } catch (IOException e) {
            currentUi.displayErrorIo();
        }
    }

    //Append tasks to array
    public static void appendTaskToArray(ArrayList< ArrayList<String> > fromLineList, ArrayList<Task> toTasksArray) {
        for (int i = 0; i < fromLineList.size(); i++) {
            if (fromLineList.get(i).get(0).equals(CHAR_TODO) ) {
                Todo tempTodo = new Todo(fromLineList.get(i).get(2) );

                if (fromLineList.get(i).get(1).equals(CHAR_FALSE) ) {
                    tempTodo.isDone = false;
                } else if (fromLineList.get(i).get(1).equals(CHAR_TRUE) ) {
                    tempTodo.isDone = true;
                }

                toTasksArray.add(tempTodo);

            } else if (fromLineList.get(i).get(0).equals(CHAR_DEADLINE) ) {
                Deadline tempDeadline = new Deadline(fromLineList.get(i).get(2), fromLineList.get(i).get(3) );

                if (fromLineList.get(i).get(1).equals(CHAR_FALSE) ) {
                    tempDeadline.isDone = false;
                } else if (fromLineList.get(i).get(1).equals(CHAR_TRUE) ) {
                    tempDeadline.isDone = true;
                }

                toTasksArray.add(tempDeadline);

            } else if (fromLineList.get(i).get(0).equals(CHAR_EVENT) ) {
                Event tempEvent = new Event(fromLineList.get(i).get(2), fromLineList.get(i).get(3) );

                if (fromLineList.get(i).get(1).equals(CHAR_FALSE) ) {
                    tempEvent.isDone = false;
                } else if (fromLineList.get(i).get(1).equals(CHAR_TRUE) ) {
                    tempEvent.isDone = true;
                }

                toTasksArray.add(tempEvent);

            }
        }
    }
}
