package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.exception.DukeException;
import seedu.duke.data.task.Deadline;
import seedu.duke.data.task.Event;
import seedu.duke.data.task.Task;
import seedu.duke.data.task.TaskList;
import seedu.duke.data.task.ToDo;

public class Storage{
    private File file;
    private Scanner fileReader;
 
    /** 
     * Constructs a new Storge and initialise with the specified file path for read/write controls.
     * @param filePath  The file path of the file for storing the task list.
     */
    public Storage(String filePath){
        this.file = new File(filePath);
    }
  
    /** 
     * Loads and initialise the task list from the file storage. 
     * @return ArrayList<Task>  The task list stored in the file storage.
     * @throws DukeException    If the file is empty or the file directory does not exist.
     */
    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> taskList = new ArrayList<>();
        try{
            if(file.isFile() && file.exists() && file.canRead()){
                fileReader = new Scanner(file);
                while(fileReader.hasNextLine()){
                    String line = fileReader.nextLine();
                    String[] splitLine = line.split(" \\| ");
                    switch(splitLine[0]){
                        case "E":
                            Event newEvent = new Event(splitLine[2], stringToDate(splitLine[3]));
                            if(splitLine[1].equals("1")){
                                newEvent.setDone();
                            }
                            taskList.add(newEvent);
                            break;
                        case "D":
                            Deadline newDeadline = new Deadline(splitLine[2], stringToDate(splitLine[3]));
                            if(splitLine[1].equals("1")){
                                newDeadline.setDone();
                            }
                            taskList.add(newDeadline);
                            break;
                        case "T":
                            ToDo newTodo = new ToDo(splitLine[2]);
                            if(splitLine[1].equals("1")){
                                newTodo.setDone();
                            }
                            taskList.add(newTodo);
                            }
                }       
            }
            else{
                throw new DukeException("The file is either empty or does not exists. Creating a new task list!");
            }
        } catch(FileNotFoundException | DukeException | NullPointerException e){
            createFileAndDirectory();
        }
        return taskList;
    }

    /**
     * Creates a new file from the file directory specified by the user.
     */
    public void createFileAndDirectory(){
        try {
            if(file.getParentFile().mkdirs()){
            }
            if(file.createNewFile()){
            }
        } catch(IOException e){
            new IOException("The file " + file.getAbsolutePath() + " has encountered an error creating.");
        }
    }
 
    /** 
     * Saves the tasks from the current task list to the file specified by the user.  
     * @throws IOException  If the application is unable to write to the
     */
    public void saveToFile() throws DukeException, IOException{
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        String toAdd = "";
        createFileAndDirectory();

        for (int i = 0; i < TaskList.getSize(); ++i) {
            Task task = TaskList.getTask(i);
            String taskClass = "";
            int isDone = 0;
            String description = task.getDescription();
            String date = "";

            if(task instanceof ToDo){
                taskClass = "T";
            }
            else if(task instanceof Event){
                taskClass = "E";
                date = dateToString(((Event) task).at);
            }
            else if(task instanceof Deadline){
                taskClass = "D";
                date = dateToString(((Deadline) task).by);
            }
            if (task.getStatus()){
                isDone = 1;
            }
            else {
                isDone = 0;
            }
            if (date.isEmpty()){
                toAdd += taskClass + " | " + Integer.toString(isDone) + " | " + description + "\n";
            }
            else {
                toAdd += taskClass + " | " + Integer.toString(isDone) + " | " + description + " | " + date + "\n";
            }
        }
        try{
            fileWriter.write(toAdd);
            fileWriter.close();
        } catch(IOException e){
            throw new DukeException("The file " + file.getAbsolutePath() + " has encountered an error writing.");
        }
    }
    
    /** 
     * Converts the String to a DateTime format.
     * @param date              The date and time specified by the user.
     * @return LocalDateTime    The date and time specified by the user in LocalDateTime format.
     * @throws DukeException    If the date read from the file is in an invalid format.
     */
    private LocalDateTime stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e){
            throw new DukeException("The date " + date + " loaded from the file is invalid.");
        }
    }

    /** 
     * Converts the date and time to String format.
     * @param dateTime  The date and time of the task.
     * @return String   The date and time of the task in String.
     */
    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}