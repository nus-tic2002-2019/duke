package tasks;

import java.time.format.DateTimeFormatter;

public class Todo extends Task {
    private boolean type = false;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDate(){
        return "";
    }

    @Override
    public String getTime() {
        return "";
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
