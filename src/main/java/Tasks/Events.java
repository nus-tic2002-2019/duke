package Tasks;
import Parser.myMethods;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Events extends Task{
    protected String at;

    /**
     * Constructor for Events
     * @param description
     * @param at
     */
    public Events(String description, String at){
        super(description);
        isDone = false;
        this.at = at;
    }

    @Override
    public String getStatus() {
        super.getStatus();
        //System.out.println("[T]");
        return "[E]";
    }

    @Override
    public String toString() {
        return "[E]" +  this.getStatusIcon() + this.description + "(at: " + at + " )";
    }
}
