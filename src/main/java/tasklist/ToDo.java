package tasklist;

import java.text.ParseException;
import java.util.Date;

public class ToDo extends Task {
//    public String Type = "Todo";

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


    public boolean findDate(Date dateSearch, String taskType) throws ParseException {
        return false;
    }

    public boolean findFromDateRange(Date dateSearch, String taskType) throws ParseException {
        return false;
    }

    public boolean findBetweenDateRange(Date fromDate, Date endDate, String taskType) throws ParseException {
        return false;
    }

    @Override
    public boolean taskType(String taskType){
        if (taskType.equals("todos")){
            return true;
        }
        return false;
    }
}

