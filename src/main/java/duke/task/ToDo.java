package duke.task;

public class ToDo extends Task {
    protected String task;

    //Constructor
    public ToDo(String input)
    {
        super(input);
    }

    //Accessor
    @Override
    public String printTask()
    {
        return "[T]" + super.printTask();
    }



}
