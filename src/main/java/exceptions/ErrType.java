package exceptions;

/**
 * Represent the class to check the validity of user's command
 */
public class ErrType {

    /**
     * This method convert the task serial number from string to integer type
     * and check for validity including within current task list size.
     * @param s : the task serial number in string type.
     * @param taskSize : the current size of task list.
     * @return the task serial number in integer type.
     *         if serial number is invalid, it will
     *         return -1 as an error flag.
     */
    public static Integer toInteger(String s, Integer taskSize) {
        Integer index; // user's required task serial number as integer type.
        try {
            index = Integer.parseInt(s);
            if (doneRange(index, taskSize)) {
                return index; // return a valid index number
            }
        } catch (NumberFormatException e) {
            System.out.println("\tOops!! Please type a task number.");
        } catch (DukeException e) {
            System.out.println("\tOops!! Task number does not exist.");
        }
        return -1; //to indicate user input an out-of-range number
    }

    /**
     *
     * @param index : user's required task serial number.
     * @param taskSize : current size of task list.
     * @return True when user's required task serial number
     *         is within 1 <= index <= task size of task list.
     * @throws DukeException
     */
    public static Boolean doneRange(Integer index, Integer taskSize) throws DukeException {
        if ( index < 1 || index > taskSize ){
            throw new DukeException();
        }
        return true;
    }

    /**
     * This method checks for the presence of task description.
     * @param s : user's task description with schedule.
     * @return True when there is a task description.
     */
    public static Boolean TaskCheck(String s){
        try{
            String user_task = s.split("/")[0].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOops!! Missing task/event.");
            return false;
        }
        return true;
    }

    /**
     * This method checks for the presence of task schedule.
     * @param s : user's task description with schedule.
     * @return True when there is a task schedule.
     */
    public static Boolean ScheduleCheck(String s){
        try{
            String user_task = s.split("/")[1].replace("by ",""); //suppose to be schedule
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOops!! Missing schedule.");
            return false;
        }
        return true;
    }

    /**
     * This method checks for the presence of time in the schedule.
     * @param s : user's schedule ie yyyy-mm-dd hhmm
     * @return True when there is a possibility of time component.
     */
    public static Boolean timeCheck(String s){
        try{
            String timeString = s.split(" ")[1]; //suppose to be time string
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOnly Date. No Time.");
            return false;
        }
        return true;
    }
}
