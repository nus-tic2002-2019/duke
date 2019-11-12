public class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String status, String description){
        super(description);
        this.setStatus(status);
    }

    @Override
    public char getTaskType() {
        return 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}