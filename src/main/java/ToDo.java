

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public String getStatusIconAndDesc() {
        return "[T][" + super.getStatusIcon() + "] " + super.desc;
    }

}
