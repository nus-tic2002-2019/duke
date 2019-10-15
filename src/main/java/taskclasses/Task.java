package taskclasses;

public class Task {
    protected String description;
    protected String type;
    protected String Time;
    public boolean isDone = false;

    public Task(){}

    Task(String description, String type){
        this.description = description;
        this.type = type;
        isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? String.valueOf('\u2713') : "\u2718");
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

    public static String Description(String Input){
        String D;
        String[] body = Input.split(" ");

        int D_index = Input.indexOf(" ") + 1;

        if(body[0].equals("todo")){
            D = Input.substring(D_index);
        }
        else{

            int slash_index = Input.length();

            if(body[0].equals("deadline")){
                slash_index = Input.lastIndexOf("by");
            }
            else {
                slash_index = Input.lastIndexOf("at");
            }

            D = Input.substring(D_index, slash_index - 1).trim();
        }

        return D.trim();
    }

    //To get starting time;
    public static String Deadline_time(String Input){
        String ST;

        int ST_index = Input.lastIndexOf("by") + 3;

        ST = Input.substring(ST_index);

        return ST;
    }

    //To get ending time;
    public static String Event_time(String Input){
        String ET;

        int ET_index = Input.lastIndexOf("at");

        if(ET_index == -1){
            ET_index = Input.lastIndexOf("on") + 3;
        }
        else {
            ET_index = ET_index + 3;
        }

        ET = Input.substring(ET_index);

        return ET;
    }
}
