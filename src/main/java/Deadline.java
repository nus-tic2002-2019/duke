
public class Deadline extends Task {
    String t;
    public Deadline(String description, String time)
    {
        super(description);
        t = time;
    }

    public String getStatusIcon() {

        return ("[D]" + super.getStatusIcon()); //return tick or X symbols
    }

    public String getDescription() {

        return (super.getDescription()+" (by: "+t+")");
    }

}
