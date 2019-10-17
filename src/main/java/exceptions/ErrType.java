package exceptions;

public class ErrType {
    public static Integer toInteger(String s, Integer taskNo) {
        Integer index;
        try {
            index = Integer.parseInt(s);
            if (doneRange(index, taskNo)) {
                return index; // return a valid index number
            }
        } catch (NumberFormatException e) {
            System.out.println("\tOops!! Please type a task number.");
        } catch (DukeException e) {
            System.out.println("\tOops!! task.Task number does not exist.");
        }
        return -1; //to indicate user input an out-of-range number
    }

    // done command task number must be from 1 to taskNo only otherwise throw Exception.
    public static Boolean doneRange(Integer index, Integer taskNo) throws DukeException {
        if ( index < 1 || index > taskNo ){
            throw new DukeException();
        }
        return true;
    }

    public static Boolean TaskCheck(String s){
        // to catch commands with no task description
        try{
            String user_task = s.split("/")[0].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOops!! Missing task/event.");
            return false;
        }
        return true;
    }
    public static Boolean ScheduleCheck(String s){
        // to catch task/event with no schedule
        try{
            String user_task = s.split("/")[1].replace("by ","");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\tOops!! Missing schedule.");
            return false;
        }
        return true;
    }

    // user input command / not use
    public static String command(String in){
        String[] s = in.split(" ");
        return s[0].toLowerCase();
    }
    // user input task / not use
    public static String userTask(String in){
        String[] s = in.split("/");
        return s[0].toLowerCase();
    }

}
