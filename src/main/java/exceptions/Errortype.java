//level 7.more oop
/**
 * Types of error message prompt to user whenever unregistered command is input into Duke
 */

package exceptions;

public class Errortype {
    //public static Integer toInteger(String s, Integer taskNo) {
    public static int toInteger(String indexString, Integer taskSize) {
        int indexInteger;
        try {
            indexInteger = Integer.parseInt(indexString);
            //if (doneRange(index, taskNo)) {
            if (isTaskNumberValid(indexInteger, taskSize)) {
                return indexInteger;
            }
        } catch (NumberFormatException e) {
            System.out.println("Oops!! Please type a task number.");
        } catch (exceptions.DukeException e) {
            System.out.println("Oops!! Task number does not exist.");
        }
        indexInteger = -1;
        return indexInteger;
    }


    public static Boolean isTaskNumberValid(int index, int taskSize) throws exceptions.DukeException {
        if ( index < 1 || index > taskSize ){
            throw new exceptions.DukeException();
        }
        return true;
    }


    public static Boolean isTask(String taskString) {
        // for no task description
        try {
            String user_task = taskString.split("/")[0].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Oops!! Missing task/event.");
            return false;
        }
        return true;
    }


    public static Boolean isSchedule(String taskString) {
        // for no schedule
        try{
            String user_schedule = taskString.split("/")[1].replace("by ","");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Oops!! Missing schedule.");
            return false;
        }
        return true;
    }


    public static Boolean isTime(String scheduleString){
        try{
            String user_time = scheduleString.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Oops!! Missing time.");
            return false;
        }
        return true;
    }

}
