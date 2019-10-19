package storage;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

public class StorageFile {

    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";

    private Path path;

    public StorageFile() {
        this.path = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String filePath) throws InvalidStorageFilePathException, FilePathNotFound {
        this.path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
        if(!FileExist(path)){
            throw new FilePathNotFound("Storage file not found.");
        }
    }

    public static boolean isValidPath(Path filePath) throws InvalidStorageFilePathException {
        return filePath.toString().endsWith(".txt");
    }

    public boolean FileExist(){
        return (Files.exists(this.path));
    }

    public boolean FileExist(Path path){
        return (Files.exists(this.path));
    }

    //Decoder, change String from file, into Task;
    public static String InputStringToFile (Task task) throws IOException {
        String First_W = task.getType();
        String Description=task.getDescription();
        String Timing=task.getTime();
        boolean Status=task.getStatus();
        int s;

        if(Status) s=1;
        else s=0;

        switch(First_W){
            case "T":
                return task.getType() + " | " + s + " | " + Description;
            case "E":
            case "D":
                return task.getType() + " | " + s + " | " + Description + " | " + Timing;
            default:
                throw new IOException();
        }
    }

    //Encoder, change Task into String;
    public static Task StringToTask (String Task_Record) throws IOException {
        String[] Record = Task_Record.split("\\|");
        int size = Record.length;
        String Description = Record[2].trim();
        String Type = Record[0].trim();
        String Status = Record[1].trim();
        String Timing = Record[size-1].trim();
        boolean s;
        s = Status.equals("1");

        switch (Type){
            case "T":
                Task todo = new Todo(Description);
                todo.isDone = s;
                return todo;
            case "E":
                Task event = new Event(Description, Timing);
                event.isDone = s;
                return event;
            case "D":
                Task deadline = new Deadline(Description, Timing);
                deadline.isDone = s;
                return deadline;
            default:
                throw new IOException();
        }
    }

    //Transfer File String Task into Task;
    public Vector<Task> CopyToVector() throws IOException {
        Vector<Task> List = new Vector<>();
        File f = new File(String.valueOf(this.path));

        Scanner s = new Scanner(f);

        while(s.hasNext()){
            Task task = new Task();

            task = StringToTask(s.nextLine());

            List.add(task);
        }

        return List;
    }

    //Transfer Task into File;
    public void TransferToFile(Vector<Task> List) throws IOException {
        String File_Directory = String.valueOf(this.path);

        File oldFile = new File("data/duke.txt");

        //To clear content of existing txt file;
        FileWriter fw = new FileWriter(oldFile);
        fw.write("");
        fw.close();

        //Start writing Task(s) in Vector<Task> List into new txt file;
        FileWriter newfw = new FileWriter(oldFile.getAbsolutePath(), true);
        Task task;
        String S_task;
        int i = 0;

        while(i<List.size()){
            task = List.get(i);
            S_task = InputStringToFile(task);
            newfw.write(S_task + System.lineSeparator());
            i++;
        }

        newfw.close();
    }

    public static class FilePathNotFound extends Exception{
        FilePathNotFound(String message){
            super(message);
        }
    }

    public static class InvalidStorageFilePathException extends Exception{
        InvalidStorageFilePathException(String message){
            super(message);
        }
    }
}
