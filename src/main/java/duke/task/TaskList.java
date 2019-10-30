package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();


    public TaskList(){
    }

    private Task parseTask(String line){
        String[] load = line.split("\\|");
        Task task;
        switch (load[0]){
            case "T":
                task = new ToDo(load[2]);
                break;
            case "D":
                task = new Deadline(load[2], load[3]);
                break;
            case "E":
                task = new Event(load[2], load[3]);
                break;
            default:
                return null;
        }
        if (load[1].equals("1")){
            task.markAsDone();
        }
        return task;
    }


    public TaskList(List<String> lines){
        for(String line : lines){
            tasks.add(parseTask(line));
        }
    }

    public List<String> ConvertAsLines(){
        List<String> saveIn = new ArrayList<>();
        for (Task task : tasks){
            saveIn.add(getSaveIn(task));
        }
        return saveIn;
    }

    private String getSaveIn(Task task){
        StringJoiner joiner = new StringJoiner("|");
        for (String strLine : task.getList()){
            joiner.add(strLine);
        }
        return joiner.toString();
    }

    public void add(Task task){
        tasks.add(task);
    }

    public Task remove(int index){
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size(){
        return tasks.size();
    }

}
