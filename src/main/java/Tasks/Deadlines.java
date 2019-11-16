package Tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadlines extends Task {


    protected Date date;



    public Deadlines(String description, Date date){
        super(description);
        isDone = false;
        this.date = date;
    }

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
        return "[D]";
    }



    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + printDeadlineDte() + ")";
    }
}
