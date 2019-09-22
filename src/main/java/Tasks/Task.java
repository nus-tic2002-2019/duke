package Tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
   // protected ArrayList<String> taskList = new ArrayList<>();



    //Getters


    public String getDescription(){
        return getStatus() + getStatusIcon() + this.description;
    }

    //Setters
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

    // Additional Methods
//    public void addTasks(String task){
//        taskList.add(task);
//    }

    public void markAsDone(){
        this.isDone = true;

    }

//    public void getStatus(String status){
//        switch (status){
//            case "T" :
//                System.out.println("[T]");
//                break;
//            case "D" :
//                System.out.println("[D]");
//                break;
//            case "E" :
//                System.out.println("[E]");
//                break;
//        }
//    }

    public String getStatus(){return "";};

    public void markAsUndone(){
        this.isDone = false;
    }

}
