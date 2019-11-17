package tasklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String time;

    /****
     *
     * @param description the task description that the user input
     * @param time the task time that the user input
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /****
     *
     * @param description the task description that the user input
     * @param status check if the task has already marked as done
     */
    public Event(String description, Boolean status) {
        super(description,status);
    }

    /****
     *
     * @param description the task description that the user input
     * @param status check if the task has already marked as done
     * @param time the task time that the user input
     */
    public Event(String description,Boolean status, String time) {
        super(description,status);
        this.time = time;

    }
    @Override
    public String toString() {
        return("[E]" + super.toString() + " (at: " + this.time + ")");
    }

    @Override
    public String saveFormat() {
        return("E " + super.saveFormat() + " | " + this.time);
    }


    /****
     *
     * @param dateSearch the date that user input
     * @param taskType the task type of the object
     * @throws ParseException if date format is not dd MMM yyyy
     */
    @Override
    public boolean findDate(Date dateSearch, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.time.equals("Date not specified")){
            return false;
        } else {
            Date date = dateFormat.parse(this.time);
            if (dateSearch.compareTo(date) == 0 && taskType.equals("event")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /****
     *
     * @param dateSearch the date that user input
     * @param taskType the task type of the object
     * @throws ParseException if date format is not dd MMM yyyy
     */
    @Override
    public boolean findFromDateRange(Date dateSearch, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.time.equals("Date not specified")){
            return false;
        } else {
            Date date = dateFormat.parse(this.time);
            if ((dateSearch.compareTo(date) == 0 && taskType.equals("event")) || (dateSearch.compareTo(date) < 0 && taskType.equals("event"))) {
                return true;
            } else {
                return false;
            }
        }
    }

    /****
     *
     * @param fromDate the start date that user input
     * @param endDate the end date that user input
     * @param taskType the task type of the object
     * @throws ParseException if date format is not dd MMM yyyy
     */
    @Override
    public boolean findBetweenDateRange(Date fromDate, Date endDate, String taskType) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        if (this.time.equals("Date not specified")){
            return false;
        } else {
            Date date = dateFormat.parse(this.time);
            if (fromDate.compareTo(date) == 0 && taskType.equals("event") || endDate.compareTo(date) == 0 && taskType.equals("event")) {
                return true;
            } else if (fromDate.compareTo(date) < 0 && endDate.compareTo(date) > 0 && taskType.equals("event")){
                return true;
            } else {
                return false;
            }
        }
    }

    /****
     *
     * @param taskType the task type of the object
     */
    @Override
    public boolean taskType(String taskType){
        if (taskType.equals("events")){
            return true;
        }
        return false;
    }

}
