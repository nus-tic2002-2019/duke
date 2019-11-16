package duke;

public class Deadline extends Todo {
    private String date;

    public Deadline(String todo, String date) throws DukeException {
        super(todo);
        String[] dateTime = date.split(" ");
        try {
            setDate(Parser.formatTime(dateTime[1]) + ", " + Parser.formatTime(dateTime[2]));
        } catch (DukeException error) {
            throw error;
        }
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodo() {
        return todo;
    }

    public String getDate() {
        return date;
    }

    public String display() {
        return getTodo() + "(by: " + getDate() + ")";
    }

    public String getType() {
        return "D";
    }
}
