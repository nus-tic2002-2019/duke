package Duke.tasks;

public class Todo extends Task {
    String t;
    public Todo(String description)
    {
        super(description);
    }

    public String getStatusIcon() {

        return ("[T]" + super.getStatusIcon()); //return tick or X symbols
    }

    public String getDescription() {

        return (super.getDescription());
    }

}
