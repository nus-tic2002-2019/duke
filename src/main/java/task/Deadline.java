package task;
import java.time.LocalDate;

public class Deadline extends Task {

    LocalDate date;

    /**
     * Constructor of Deadline
     * @param Description
     * @param value
     * @param date
     */
    public Deadline(String Description, int value, LocalDate date){
        super(Description, value);
        this.date = date;
    }

    /**
     * Constructor of Deadline
     * @param Description
     * @param date
     */
    public Deadline(String Description, LocalDate date){
        super(Description, 0);
        this.date = date;
    }

    @Override
    public String toString(){
        return "[D][" + getStatusIcon() + "] " + this.description + " (by: " + this.date + ")";
    }
}
