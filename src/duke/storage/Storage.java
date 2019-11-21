package duke.storage;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;
import java.io.*;
import java.text.*;
import java.util.*;
import duke.priority.Priority;
/**
 * Interacts with text file used for storing the array list. 
 * Able to save and load.
 */
public class Storage {
    private String filePath;
    /**
     * Remembers fileName for interaction
     * @param fileName takes in file path and remembers it in the class
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }
    /**
     * Save the file to the file path recorded in this class object
     * @param tasks Task List the user is interacting with save the task list to the file path
     */
    public void save(TaskList tasks){
        try (FileWriter writer = new FileWriter(this.filePath)){
            for ( int i = 0; i < tasks.getSize() ; i++ ){
                writer.write(tasks.getTask(i).writeTask() + System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            createFile();
        } catch (IOException ex) {
            Ui.response("☹ OOPS!!! Sorry an error has occured.");
        }
    }
    /**
     * Create new file if file path does not exist
     * @param tasks Task List the user is interacting with save the task list to the file path
     */
    private void createFile(){
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        try {
            FileWriter writer = new FileWriter(file);
        } catch (IOException ex) {
            Ui.response("☹ OOPS!!! Sorry an error has occured.");
        }
    }
    /**
     * Load file from array list
     * @return return task list after loading it
     * @throws java.io.FileNotFoundException throws an exception if file is not found in the file path
     */
    public ArrayList<Task> load() throws FileNotFoundException{
        ArrayList<Task> Tasks;
        try (Scanner s = new Scanner(new File(this.filePath))) {
            s.useDelimiter(System.getProperty("line.separator"));
            Tasks = new ArrayList<>();
            while (s.hasNext()){
                String line = s.next();
                String words[] = line.split(" [|] ");
                if ( words[0].equals("T")) {
                    Tasks.add(new Todo(words[2],Priority.valueOf(words[3])));
                }
                if ( words[0].equals("E")) {
                    try {
                        Ui.convertDate(words[4]);
                        Tasks.add(new Event(words[2],Priority.valueOf(words[3]),words[4]));
                    } catch (ParseException e) {
                        Ui.response("☹ OOPS!!! Please include a valid date description after '/at'.");
                    } catch (DukeException e){
                        Ui.response("☹ OOPS!!! Please include a date description after '/at'.");
                    }
                }
                if ( words[0].equals("D")) {
                    try {
                        Ui.convertDate(words[4]);
                        Tasks.add(new Deadline(words[2],Priority.valueOf(words[3]),words[4]));
                    } catch (ParseException e) {
                        Ui.response("☹ OOPS!!! Please include a valid date description after '/by'.");
                    } catch (DukeException e){
                        Ui.response("☹ OOPS!!! Please include a date description after '/by'.");
                    }
                }
                if ( words[1].equals("1") ){
                    Tasks.get(Tasks.size()-1).markAsDone();
                }
            }
        }
        return Tasks;
    }
}
