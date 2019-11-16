package storage;
import task.Deadline;
import command.DeadlineCommand;
import task.ToDo;
import task.Task;
import task.Event;
import command.EventCommand;
import exception.DukeException;
import ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 *Represents the storage for list of task.
 * */
public class Storage {
    /**
     *Creation of variable path.
     * */
    private String path;
    /**
     *return path according to input.
     * */
    public  Storage(String s){
        this.path=s;
    }
    /**
     *Retrieval of line for loading up saved files.
     * */
    private List<String> getLine() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        File f = new File(path);
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            lines.add(s.nextLine());
        }
        return lines;
    }
    /**
     *Loading up task according to the saved files.
     * */
    private static Task createTask(String line) throws DukeException {
        String type=line.split("]")[0];
        String isDone=line.split("]")[1];
        String detail=line.split("]")[2];
        if(type.contains("T")){
            if(isDone.contains("✘")){
                return new ToDo(detail.substring(1),false);
            }else if(isDone.contains("✓")){
                return new ToDo(detail.substring(1),true);
            }

        }
        if(type.contains("D")) {
            if (isDone.contains("✘")) {
                int dividerPosition = detail.indexOf("do by:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("do by:", "");
                return new Deadline(itemName, DeadlineCommand.convertDeadline(itemName2));
            } else if (isDone.contains("✓")) {
                int dividerPosition = detail.indexOf("do by:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("do by:", "");
                return new Deadline(itemName, DeadlineCommand.convertDeadline(itemName2));
            }
        }
        if(type.contains("E")){
            if (isDone.contains("✘")) {
                int dividerPosition = detail.indexOf("at:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("at:", "");
                return new Event(itemName, EventCommand.convertEvent(itemName2));
            } else if (isDone.contains("✓")) {
                int dividerPosition = detail.indexOf("at:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("at:", "");
                return new Event(itemName, EventCommand.convertEvent(itemName2));
            }
        }

        else{
            throw new DukeException("☹ OOPS!!! Missing type element for CREATE TASK");
        }
        return new ToDo();
    }
    /**
     *Reading and loading up file stated in Duke.
     * */
    private List<Task> getTasksFromFile() throws FileNotFoundException{
        List<Task> loadedTasks = new ArrayList<>();
        Ui a=new Ui();
        try {
            List<String> lines = getLine() ;
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
            }
            System.out.println("File successfully loaded");
        } catch (DukeException e1) {
            System.out.println("☹ OOPS!!! Problem encountered while loading data: " +e1.getMessage());
        }
        return loadedTasks;
    }
    /**
     *Checking if there is any file in the stated path.
     * */
    public List<Task> load(){
        try {
            List<Task> tasks = getTasksFromFile();
            return tasks;
        }catch (FileNotFoundException e) {
            System.out.println("☹ OOPS!!! There is no file in the path: "+e.getMessage());
            List<Task> tasks = new ArrayList();
            return tasks;
        }
    }
    /**
     *Saving any changes to task list.
     * */
    public void save(List<Task> changed){

        try {
            FileWriter fw = new FileWriter(path);
            for (int i = 0; i < changed.size(); i++) {

                String temp = (i+1) + "." + changed.get(i).list() + System.lineSeparator();
                fw.write(temp);
            }
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     *Returns new path indicated.
     * */
    public void changePath(String newPath){
        this.path=newPath;
    }
}
