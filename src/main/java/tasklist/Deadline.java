package tasklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    public String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, Boolean status) {
        super(description, status);
    }

    public Deadline(String description, Boolean status, String time) {
        super(description, status);
        this.time = time;
    }


    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.time + ")");
    }

    @Override
    public String saveFormat() {
        return ("D " + super.saveFormat() + " | " + this.time);
    }

    @Override
    public boolean findDate(Date dateSearch, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.time.equals("Date not specified")){
            return false;
        }else {
            Date date = dateFormat.parse(this.time);
            if (dateSearch.compareTo(date) == 0 && taskType.equals("deadline")) {
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public boolean findFromDateRange(Date dateSearch, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.time.equals("Date not specified")){
            return false;
        }else {
            Date date = dateFormat.parse(this.time);
            if ((dateSearch.compareTo(date) == 0 && taskType.equals("deadline")) || (dateSearch.compareTo(date) < 0 && taskType.equals("deadline"))) {
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
        if (this.time.equals("Date not specified")){
            return false;
        }else {
            Date date = dateFormat.parse(this.time);
            if (fromDate.compareTo(date) == 0 && taskType.equals("deadline") || endDate.compareTo(date) == 0 && taskType.equals("deadline")) {
                return true;
            }else if (fromDate.compareTo(date) < 0 && endDate.compareTo(date) > 0 && taskType.equals("deadline")){
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public boolean taskType(String taskType){
        if (taskType.equals("deadlines")){
            return true;
        }
        return false;
    }

}
