package tic2002.storage;

import tic2002.enumerations.Priority;
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

    //Miscellaneous functions
    /**
     * Returns String ArrayList, that contains all elements of a line.
     * Delimited by custom separator.
     * Requisite for readFile function.
     *
     * @param currentLine as input String.
     * @return String ArrayList.
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
    private void completeDone_Priority_AddArray(TaskList currentTasksArray, Task currentTask, String doneStatus, Priority currentPriority) {
        if (doneStatus.equals(CHAR_FALSE) ) {
            currentTask.resetDone();
        } else if (doneStatus.equals(CHAR_TRUE) ) {
            currentTask.setDone();
        }

        currentTask.setTaskPriority(currentPriority);

        currentTasksArray.addTask(currentTask);
    }

    /**
     * Initializes and add tasks into Task ArrayList.
     * Retrieves from saved file.
     * Assume integrity of file to be always good.
     * No checking if file has been modified.
     *
     * @param fromLineList as ArrayList of StringArrayList, retrieved from saved file.
     * @param toTasksArray as Task ArrayList.
     */
    public void appendTaskToArray(ArrayList< ArrayList<String> > fromLineList, TaskList toTasksArray) {
        for (int i = 0; i < fromLineList.size(); i++) {
            String taskType = fromLineList.get(i).get(0);
            String taskCompleteBool = fromLineList.get(i).get(1);
            String taskDateBool = fromLineList.get(i).get(2);
            Priority taskPriority = Priority.valueOf(fromLineList.get(i).get(3) );
            String taskDescription = fromLineList.get(i).get(4);

            if (taskType.equals(CHAR_TODO) ) {
                //Retrieve todo from file
                Todo tempTodo = new Todo(taskDescription);
                completeDone_Priority_AddArray(toTasksArray, tempTodo, taskCompleteBool, taskPriority);

            } else if (taskType.equals(CHAR_DEADLINE) ) {
                //Retrieve deadline from file
                String taskTime = fromLineList.get(i).get(5);

                if (taskDateBool.equals(CHAR_FALSE) ) {
                    Deadline tempDeadline = new Deadline(taskDescription, taskTime);
                    tempDeadline.resetBoolDateTime();
                    completeDone_Priority_AddArray(toTasksArray, tempDeadline, taskCompleteBool, taskPriority);

                } else if (taskDateBool.equals(CHAR_TRUE) ) {
                    LocalDateTime timeConvert = LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
                    Deadline tempDeadline = new Deadline(taskDescription, timeConvert);
                    tempDeadline.setBoolDateTime();
                    completeDone_Priority_AddArray(toTasksArray, tempDeadline, taskCompleteBool, taskPriority);
                }

            } else if (taskType.equals(CHAR_EVENT) ) {
                //Retrieve event from file
                String taskTime = fromLineList.get(i).get(5);

                if (taskDateBool.equals(CHAR_FALSE) ) {
                    Event tempEvent = new Event(taskDescription, taskTime);
                    tempEvent.resetBoolDateTime();
                    completeDone_Priority_AddArray(toTasksArray, tempEvent, taskCompleteBool, taskPriority);

                } else if (taskDateBool.equals(CHAR_TRUE) ) {
                    LocalDateTime timeConvert = LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT) );
                    Event tempEvent = new Event(taskDescription, timeConvert);
                    tempEvent.setBoolDateTime();
                    completeDone_Priority_AddArray(toTasksArray, tempEvent, taskCompleteBool, taskPriority);
                }
            }
        }
    }
}
