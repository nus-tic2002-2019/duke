package exceptions;

/**
 * Represent the class to check the validity of user's command
 */
public class ErrType{

    /**
     * This method convert the task serial number from string to integer type
     * and check for validity including within current task list size.
     * @param indexString : the task serial number in string type.
     * @param taskSize : the current size of task list.
     * @return the task serial number in integer type.
     *         if serial number is invalid, it will
     *         return -1 as an error flag.
     */
    public static int toInteger(String indexString, Integer taskSize){
        int indexInteger; // user's required task serial number as integer type.
        try {
            indexInteger = Integer.parseInt(indexString);
            if ( isTaskNumberValid(indexInteger, taskSize) ){
                return indexInteger; // return a valid index number
            }
        } catch (NumberFormatException e){
            System.out.println("\tOops!! Please type a task number.");
        } catch (DukeException e){
            System.out.println("\tOops!! Task number does not exist.");
        }
        indexInteger = -1; //to indicate user input an invalid number
        return indexInteger;
    }

    /**
     *
     * @param index : user's required task serial number.
     * @param taskSize : current size of task list.
     * @return True when user's required task serial number
     *         is within 1 <= index <= task size of task list.
     * @throws DukeException
     */
    public static Boolean isTaskNumberValid(int index, int taskSize) throws DukeException {
        if ( index < 1 || index > taskSize ){
            throw new DukeException();
        }
        return true;
    }

    /**
     * This method checks for the presence of task description.
     * @param user_input : user's task description with schedule.
     * @return True when there is a task description.
     */
    public static Boolean isTask(String user_input){
        try {
            String user_task = user_input.split("/")[0].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOops!! Missing task/event.");
            return false;
        }
        return true;
    }

    /**
     * This method checks for the presence of task schedule.
     * @param user_input : user's task description with schedule.
     * @return True when there is a task schedule.
     */
    public static Boolean isSchedule(String user_input){
        try {
            String user_schedule = user_input.split("/")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOops!! Missing schedule.");
            return false;
        }
        return true;
    }

    /**
     * This method checks for the presence of time in the schedule.
     * @param scheduleString : user's schedule ie yyyy-mm-dd hhmm
     * @return True when there is a possibility of time component.
     */
    public static Boolean isTime(String scheduleString){
        try {
            String user_time = scheduleString.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOops!! Missing time.");
            return false;
        }
        return true;
    }
}
