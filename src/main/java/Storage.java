import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String path;
    public  Storage(String s){
        this.path=s;
    }
    private List<String> getLine() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        File f = new File(path);
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            lines.add(s.nextLine());
        }
        return lines;
    }
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
                return new Deadline(itemName, itemName2);
            } else if (isDone.contains("✓")) {
                int dividerPosition = detail.indexOf("do by:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("do by:", "");
                return new Deadline(itemName, itemName2);
            }
        }
        if(type.contains("E")){
            if (isDone.contains("✘")) {
                int dividerPosition = detail.indexOf("at:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("at:", "");
                return new Event(itemName, itemName2);
            } else if (isDone.contains("✓")) {
                int dividerPosition = detail.indexOf("at:");
                String itemName = detail.substring(0, dividerPosition);
                String itemName1 = detail.substring(dividerPosition,detail.length());
                String itemName2 = itemName1.replace("at:", "");
                return new Event(itemName, itemName2);
            }
        }

        else{
            throw new DukeException("☹ OOPS!!! Missing type element for CREATE TASK");
        }
        return new ToDo();
    }

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
    public void changePath(String newPath){
        this.path=newPath;
    }
}
