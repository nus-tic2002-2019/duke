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

import seedu.duke.data.exception.DukeException;
import seedu.duke.data.task.Deadline;
import seedu.duke.data.task.Event;
import seedu.duke.data.task.Task;
import seedu.duke.data.task.TaskList;
import seedu.duke.data.task.ToDo;

public class Storage{
    private String filePath;
    private File file;
    private Scanner fileReader;

    public Storage(String filePath){
        try{
        this.filePath = filePath;
        this.file = new File(filePath);
        fileReader = new Scanner(file);
        }
        catch (FileNotFoundException e){
            createFileAndDirectory();
        }
    }

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> taskList = new ArrayList<>();
        try{
            if(file.isFile() && file.exists() && file.canRead()){
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
                throw new IOException();
            }
        }
        catch(IOException | NullPointerException e){
            createFileAndDirectory();
        }
        return taskList;
    }

    public void createFileAndDirectory(){
        try {
            if(file.getParentFile().mkdirs()){
            }
            if(file.createNewFile()){
            }
        }
        catch(IOException e){
            new IOException("The file " + file.getAbsolutePath() + " has encountered an error creating.");
        }
    }

    public void saveToFile() throws IOException{
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        String toAdd = "";
        createFileAndDirectory();

        for (int i = 0; i < TaskList.getSize(); ++i) {
            Task task = TaskList.getTask(i);
            String taskClass = "";
            int isDone = 0;
            String description = task.description;
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

            if (task.isDone){
                isDone = 1;
            }
            else {
                isDone = 0;
            }
            if (date.equals("")){
                toAdd += taskClass + " | " + Integer.toString(isDone) + " | " + description + "\n";
            }
            else {
                toAdd += taskClass + " | " + Integer.toString(isDone) + " | " + description + " | " + date + "\n";
            }
        }
        try{
            fileWriter.write(toAdd);
            fileWriter.close();
        }

        catch(IOException e){
            new IOException("The file " + file.getAbsolutePath() + " has encountered an error writing.");
        }
    }

    private LocalDateTime stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        }
        catch (DateTimeParseException e){
            throw new DukeException("The date " + date + " loaded from the file is invalid.");
        }
    }

    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }


}