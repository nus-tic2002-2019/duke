package Tasks;

public class toDos extends Task{

    public toDos(String description){
        super(description);
        isDone = false;
    }


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
