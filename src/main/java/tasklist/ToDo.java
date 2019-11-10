package tasklist;

public class ToDo extends Task {
    public String Type = "Todo";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Boolean status) {
        super(description, status);
    }

    @Override
    public String toString() {
        return("[T]" + super.toString());
    }

    @Override
    public String saveFormat(){
        return("T " + super.saveFormat());


    }
}

