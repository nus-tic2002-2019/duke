package subclass;

public class Todo extends Task {

    public Todo(String description) throws todoException{
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
