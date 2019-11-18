package tic2002.storage;

import tic2002.task.Deadline;
import tic2002.task.Event;
import tic2002.task.Task;
import tic2002.task.TaskList;
import tic2002.task.Todo;
import tic2002.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents Storage class.
 * Deals with loading tasks from file and saving tasks back.
 */
public class Storage {
    //Declare constant variables
    private static final String CHAR_FALSE = "0";
    private static final String CHAR_TRUE = "1";
    private static final String CHAR_TODO = "T";
    private static final String CHAR_DEADLINE = "D";
    private static final String CHAR_EVENT = "E";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";

    //Declare variables
    private static Ui currentUi;
    private static String filePath;
    private static File currentFile;
    private ArrayList< ArrayList<String> > fileExtract;

    //Constructor
    public Storage(String filePath, Ui currentUi, TaskList tasksList) {
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
    /**
     * Returns String ArrayList, that contains all elements of a line.
     * Delimited by custom separator.
     * Requisite for readFile function.
     *
     * @param currentLine as input String.
     * @return String.
     */
    private static ArrayList<String> readLine(String currentLine) {
        ArrayList<String> lineElements = new ArrayList<>();

        Scanner s = new Scanner(currentLine);
        s.useDelimiter("[|]");

        while (s.hasNext() ) {
            lineElements.add(s.next() );
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

    /**
     * Returns ArrayList of String ArrayList, that contains all lines in a file.
     *
     * @return ArrayList of String ArrayList.
     * @throws FileNotFoundException if file does not exist.
     */
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
    /**
     * Appends String to file.
     *
     * @param textToAppend as String to input.
     * @throws IOException
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Writes list of Strings to file.
     *
     * @param tasksList as ArrayList of Task.
     * @throws IOException
     */
    public void writeToFile(TaskList tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasksList.getListSize(); i++) {
            fw.write(tasksList.getTask(i).printToFile() + "\n");
        }

        fw.close();
    }

    /**
     * Clears file.
     *
     * @throws IOException
     */
    public void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    //Execute functions
    /**
     * Executes appending Tasks to file, using appendTaskToFile function.
     *
     * @param currentTask
     */
    public void appendTaskToFile(Task currentTask) {
        try {
            appendToFile(currentTask.printToFile() );
        } catch (IOException e) {
            currentUi.displayErrorIo();
        }
    }

    //Sub-function to initialize Done and add to Task ArrayList
    private void initDoneAddArray (TaskList currentTasksArray, Task currentTask, String doneStatus) {
        if (doneStatus.equals(CHAR_FALSE) ) {
            currentTask.resetDone();
        } else if (doneStatus.equals(CHAR_TRUE) ) {
            currentTask.setDone();
        }

        currentTasksArray.addTask(currentTask);
    }

    /**
     * Initializes and add tasks into Task ArrayList.
     * Retrieves from saved file data.
     * Assume integrity of file to be always good.
     * No checking if file has been modified.
     *
     * @param fromLineList as ArrayList of StringArrayList, retrieved from saved file.
     * @param toTasksArray as Task ArrayList.
     */
    public void appendTaskToArray(ArrayList< ArrayList<String> > fromLineList, TaskList toTasksArray) {
        for (int i = 0; i < fromLineList.size(); i++) {
            if (fromLineList.get(i).get(0).equals(CHAR_TODO) ) {
                //Add todo from file
                Todo tempTodo = new Todo(fromLineList.get(i).get(3) );

                if (fromLineList.get(i).get(1).equals(CHAR_FALSE) ) {
                    tempTodo.resetDone();
                } else if (fromLineList.get(i).get(1).equals(CHAR_TRUE) ) {
                    tempTodo.setDone();
                }

                toTasksArray.addTask(tempTodo);

            } else if (fromLineList.get(i).get(0).equals(CHAR_DEADLINE) ) {
                //Add deadline from file
                if (fromLineList.get(i).get(2).equals(CHAR_FALSE) ) {
                    Deadline tempDeadline = new Deadline(fromLineList.get(i).get(3), fromLineList.get(i).get(4) );
                    tempDeadline.resetBoolDateTime();
                    initDoneAddArray(toTasksArray, tempDeadline, fromLineList.get(i).get(1) );

                } else if (fromLineList.get(i).get(2).equals(CHAR_TRUE) ) {
                    LocalDateTime timeConvert = LocalDateTime.parse(fromLineList.get(i).get(4), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
                    Deadline tempDeadline = new Deadline(fromLineList.get(i).get(3), timeConvert);
                    tempDeadline.setBoolDateTime();
                    initDoneAddArray(toTasksArray, tempDeadline, fromLineList.get(i).get(1) );
                }

            } else if (fromLineList.get(i).get(0).equals(CHAR_EVENT) ) {
                //Add event from file
                if (fromLineList.get(i).get(2).equals(CHAR_FALSE) ) {
                    Event tempEvent = new Event(fromLineList.get(i).get(3), fromLineList.get(i).get(4) );
                    tempEvent.resetBoolDateTime();
                    initDoneAddArray(toTasksArray, tempEvent, fromLineList.get(i).get(1) );

                } else if (fromLineList.get(i).get(2).equals(CHAR_TRUE) ) {
                    LocalDateTime timeConvert = LocalDateTime.parse(fromLineList.get(i).get(4), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
                    Event tempEvent = new Event(fromLineList.get(i).get(3), timeConvert);
                    tempEvent.setBoolDateTime();
                    initDoneAddArray(toTasksArray, tempEvent, fromLineList.get(i).get(1) );
                }
            }
        }
    }
}
