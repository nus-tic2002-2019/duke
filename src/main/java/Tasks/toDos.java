package Tasks;

public class toDos extends Task{

    public toDos(String description){
        super(description);
//        this.description = "[T]" +description;
        isDone = false;
    }

//    public String getStatusIcon() {
//        super.getStatusIcon();
//        return (isDone ? getStatus() + "[" + "\u2713" + "]": "["+"\u2718"+"]"); //return tick or X symbols
//    }


    @Override
    public String getStatus() {
        super.getStatus();
        //System.out.println("[T]");
        return "[T]";
    }

    @Override
    public String toString() {
        return "[T]" +  this.getStatusIcon() + this.description;
    }
}
