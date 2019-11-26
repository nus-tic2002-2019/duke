public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructs a new Task and initialise with the specified inputby the user .
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); 
    }

    public String toString(){
        return getStatusIcon() + description;
    }

    public void markAsDone() {
        isDone = true;
    }

}