public class Todo extends Task {
    private boolean type = false;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
