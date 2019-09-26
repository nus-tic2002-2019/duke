public class Todo extends Task {

    public Todo (String description){
        super(description);
        super.isDone = false;
    }
    @Override
    public String getDescription() {
        return "[T]" + "[" + getStatusIcon() + "}" + super.getDescription();
    }

}
