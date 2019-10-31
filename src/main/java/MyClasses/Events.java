package MyClasses;

public class Events extends TaskList {
    private String event_deadline = "not specified";
    private String Type = "E";

    public Events(String Task, String at) {
        super(Task);
        this.event_deadline = at;
    }

    public Events(String Task) {
        super(Task);
    }

    @Override
    public void ListTask(int size) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println(size + 1 + "  . [" + Type + "]" + "[" + temp + "] " + this.taskList + " (at: " + event_deadline + ")");
    }

    @Override
    public void ListTask() {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println("[" + Type + "]" + "[" + temp + "] " + this.taskList + " (at: " + event_deadline + ")");
    }
}
