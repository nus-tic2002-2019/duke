package taskclasses;

public class Task {
    protected String description;
    protected String type;
    protected String Date;
    protected String Time;
    protected String DateTime;
    public boolean isDone = false;

    public Task(){}

    Task(String description, String type){
        this.description = description;
        this.type = type;
        isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? String.valueOf('T') : "F");
    }

    public String getDescription(){
        return description;
    }

    public String getType(){ return type; }

    public String getTime(){
        return Time;
    }

    public boolean getStatus() {
        return isDone;
    }
}
