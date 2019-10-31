package storage;

import date.time.management.DateTime;
import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.Todo;
import thrownexceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class StorageFile {

    private Path path;

    /**
     * An default file path which is "data/duke.txt"
     */
    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";

    /**
     * Constructs StorageFile with default file path
     */
    public StorageFile() {
        this.path = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Constructs with input file path
     * @param filePath input file Path in String
     * @throws InvalidStorageFilePathException If the file path input not valid, an error will be throw to user
     * @throws FilePathNotFound If the file path not found, an error will be throw to user
     */
    public StorageFile(String filePath) throws InvalidStorageFilePathException, FilePathNotFound {
        this.path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
        if(!FileExist(path)){
            throw new FilePathNotFound("Storage file not found.");
        }
    }

    /**
     * To check whether the file path input is valid
     * @param filePath file Path in String
     * @return return boolean whether valid
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * To check whether the default file path is exist
     * @return return boolean whether the default file path is exist
     */
    public boolean FileExist(){
        return (Files.exists(this.path));
    }

    /**
     * To check whether the input file path is exist
     * @param path file path in String input
     * @return return boolean whether the file path exist
     */
    private boolean FileExist(Path path){
        return (Files.exists(this.path));
    }

    /**
     * A function to convert the Task into String type which can be store into text file
     * @param task the Task which is going to convert
     * @return return Task in String type
     * @throws EncoderUnknowError If the type is not "E" or "D" or "T", an error will be throw to user
     */
    private static String Encoder(Task task) throws EncoderUnknowError {
        String type = task.getType();
        String description = task.getDescription();
        String starting, ending, deadline_timing;
        boolean status = task.getStatus();


        switch(type){
            case "T":
                return type + "|" + status + "|" + description;
            case "D":
                deadline_timing = task.getDeadline_DateTime_String();
                return type + "|" + status + "|" + description + "|" + deadline_timing;
            case "E":
                starting = task.getStarting_DateTime_String();
                ending = task.getEnding_DateTime_String();
                return type + "|" + status + "|" + description + "|" + starting + "|" + ending;
            default:
            {
                throw new EncoderUnknowError();
            }
        }
    }

    /**
     * A function to convert String information in text file into Task type
     * @param task String task information
     * @return return Task
     * @throws DecoderUnknownError If the String information not clear or correct, an error will be thrown to user
     */
    private static Task Decoder(String task) throws DecoderUnknownError {
        Task task_Vector;
        String[] task_contents = task.split("\\|");
        String type = task_contents[0];
        String description = task_contents[2];
        String deadline_datetime, start, end;
        boolean status = true;
        if(task_contents[1].equals("0")) status = false;

        switch (type){
            case "T":
                task_Vector = new Todo(description);
                break;
            case "D":
                //To get date, time for DataTime creation;
                deadline_datetime = task_contents[3];

                //To  create DateTime for Deadline creation;
                DateTime Deadline_DateTime = new DateTime(deadline_datetime);
                task_Vector = new Deadline(description, Deadline_DateTime);

                break;
            case "E":
                //To get starting and ending time information for event creation;
                start = task_contents[3];
                end = task_contents[4];
                DateTime starting = new DateTime(start);
                DateTime ending = new DateTime(end);
                task_Vector = new Event(description, starting, ending);

                break;
            default:
                throw new DecoderUnknownError();
        }

        task_Vector.UpdateIsDone(status);

        return task_Vector;
    }

    /**
     * To copy converted Task information into Vector<Task> List
     * @return return Vector<Task> List
     * @throws FileNotFoundException If the file not found, the throw an error to user
     * @throws DecoderUnknownError If the decoder function got any error, an error will be sent to user
     */
    public Vector<Task> CopyToVector() throws FileNotFoundException, DecoderUnknownError {
        Vector<Task> List = new Vector<>();

        File f = new File(String.valueOf(this.path));

        Scanner s = new Scanner(f);

        while(s.hasNext()){
            Task task;
            String text = s.nextLine();
            task = Decoder(text);

            List.add(task);
        }

        return List;
    }

    /**
     * The function to transfer all the Task information by using Encoder function from Vector<Task> List to String and store into txt file
     * @param List Vector<Task> List
     * @throws IOException If the FileWrite those kind of function got any error, an error will be thrown to user
     * @throws EncoderUnknowError If Encoder has any error, an error will be thrown to user
     */
    public void TransferToFile(Vector<Task> List) throws IOException, EncoderUnknowError {
        String File_Directory = String.valueOf(this.path);

        //To clear content of existing txt file;
        FileWriter fw = new FileWriter(File_Directory);
        fw.write("");
        fw.close();

        //Start writing Task(s) in Vectoc<Task> List into new txt file;
        FileWriter newfw = new FileWriter(File_Directory, true);
        Task task;
        String S_task;
        int i=0;

        while(i<List.size()){
            task = List.get(i);
            S_task = Encoder(task);
            newfw.write(S_task + System.lineSeparator());

            i++;
        }
        newfw.close();
    }
}