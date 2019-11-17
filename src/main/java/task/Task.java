package task;

public abstract class Task{
    private static int totalTask = 0;
    private int taskNumber = 0;
    private String content;
    private boolean completed;

    public Task() {
        content = null;
        completed = false;
    }

    public Task(String content) {
        this.content = content;
        completed = false;
        ++ totalTask;
        taskNumber = totalTask;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getContent() {
        return content;
    }

    public boolean getCompleted () {
        return completed;
    }
    public void setCompleted () {
        completed = true;
        return;
    }
    public void setIncompleted () {
        completed = false;
        return;
    }


    @Override
    public String toString() {
        String status = "[✗]";
        if (getCompleted()) {
            status = "[✓]";
        }
        return status + " " + content;
    }
}
