package TASK;

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

    public boolean getcompleted () {
        return completed;
    }
    public void setcompleted () {
        completed = true;
        return;
    }


    @Override
    public String toString() {
        String status = "[✗]";
        if (getcompleted()) {
            status = "[✓]";
        }
        return status + " " + content;
    }
}
