package Task;

public class Todo extends Task {

    protected boolean isToDo;

    public Todo(String description) {

            super(description);
            isToDo = false;
    }

    public void setToDo() {
        this.isToDo = true;
    }

    @Override
    public String toString(){
        return "[T]" + description;
    }
}
