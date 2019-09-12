package Tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> taskList;

    //Getters
    public String getDescription(){
        return getStatusIcon() + this.description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]": "["+"\u2718"+"]"); //return tick or X symbols
    }

    public int getIndex(String input){
        String[] num = input.split("done");
        String part1 = num[0];
        String part2 = num[1];
        return Integer.parseInt(part2);
    }

    public void markAsDone(){
        this.isDone = true;

    }

    public void markAsUndone(){
        this.isDone = false;
    }

}
