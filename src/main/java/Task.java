public class Task {
    protected String description;
    protected boolean isDone;
    protected char typeIdt;

    //Constructor
    public Task(String description) {
        this.description = description;
        isDone = false;
        typeIdt = 'A';
    }

    //Getter
    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public char getTypeIdentification() {
        return typeIdt;
    }

    //Setter
    public void setDone() {
        isDone = true;
    }
}