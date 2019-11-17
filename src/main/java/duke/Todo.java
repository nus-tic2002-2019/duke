package duke;

public class Todo extends Task {
    protected String todo;
    protected boolean done;

    public Todo(String todo) {
        setTodo(todo);
        setDone(false);
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTodo() {
        return todo;
    }

    public boolean getDone() {
        return done;
    }

    public String display() {
        return getTodo();
    }

    public String getType() {
        return "T";
    }
}