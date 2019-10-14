public class Todo extends Task{
    protected String to;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveTask() {
        int isDone;
        if(super.isDone)
            isDone = 1;
        else
            isDone = 0;
        return "T | " + isDone + " | " + super.saveTask();
    }
}

