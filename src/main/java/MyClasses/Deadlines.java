package MyClasses;

public class Deadlines extends TaskList {
    private String deadline = "not specified";
    private String Type = "Deadline";

    public Deadlines(String Task, String by) {
        super(Task);
        this.deadline = by;
    }

    public Deadlines(String Task) {
        super(Task);
    }

    @Override
    public void ListTask(int size) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println(size + 1 + "  . [" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList + " (by: " + deadline + ")");
    }

    @Override
    public void ListTask() {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println("[" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList + " (by: " + deadline + ")");
    }

    @Override
    public String save() {
        return Type + " | " + this.taskStatus + " | " + this.taskList + " | " + deadline;
    }
}
