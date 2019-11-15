package tasklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String dayTime;

    public Event(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;
    }

    public Event(String description, Boolean status) {
        super(description,status);
    }

    public Event(String description,Boolean status, String dayTime) {
        super(description,status);
        this.dayTime = dayTime;

    }
    @Override
    public String toString() {
        return("[E]" + super.toString() + " (at: " + this.dayTime + ")");
    }

    @Override
    public String saveFormat() {
        return ("E " + super.saveFormat() + " | " + this.dayTime);
    }


    public boolean findDate(Date dateSearch, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.dayTime.equals("Date not specified")){
            return false;
        }else {
            Date date = dateFormat.parse(this.dayTime);
            if (dateSearch.compareTo(date) == 0 && taskType.equals("event")) {
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public boolean findFromDateRange(Date dateSearch, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.dayTime.equals("Date not specified")){
            return false;
        }else {
            Date date = dateFormat.parse(this.dayTime);
            if ((dateSearch.compareTo(date) == 0 && taskType.equals("event")) || (dateSearch.compareTo(date) < 0 && taskType.equals("event"))) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public boolean findBetweenDateRange(Date fromDate, Date endDate, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.dayTime.equals("Date not specified")){
            return false;
        }else {
            Date date = dateFormat.parse(this.dayTime);
            if (fromDate.compareTo(date) == 0 && taskType.equals("event") || endDate.compareTo(date) == 0 && taskType.equals("event")) {
                return true;
            }else if (fromDate.compareTo(date) < 0 && endDate.compareTo(date) > 0 && taskType.equals("event")){
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public boolean taskType(String taskType){
        if (taskType.equals("events")){
            return true;
        }
        return false;
    }

}
