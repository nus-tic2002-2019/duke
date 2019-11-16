package Tasks;
import Parser.myMethods;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Events extends Task{

    protected Date date;


    /**
     * Constructor for Events
     * @param description
     * @param date
     */
    public Events(String description, Date date){
        super(description);
        isDone = false;
        this.date = date;
    }

//    @Override
//    public String getDescription(){
//        return getStatus() + getStatusIcon() + " " + new myMethods().parseEvents(description) + new myMethods().parseSlash(description);
//    }


    /**
     * To convert Date back to String for Printing
     * @return
     */
    public String printDeadlineDte(){
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm");

        //System.out.println("WHAT IS THIS : " + formatter);

        String strDate = formatter.format(this.date);

        return strDate;
    }

    @Override
    public String getStatus() {
        super.getStatus();
        //System.out.println("[T]");
        return "[E]";
    }

    @Override
    public String toString() {
        return "[E]" +  this.getStatusIcon() + this.description + "(at: " + printDeadlineDte() + " )";
    }
}
