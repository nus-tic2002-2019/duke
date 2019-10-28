public class Deadline extends Todo {
    private String date;

    public Deadline(String todo, String date) {
        super(todo);
        setDate(date);
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
        return getTodo() + "(by:" + getDate() + ")";
    }

    public String getType() {
        return "D";
    }
}
