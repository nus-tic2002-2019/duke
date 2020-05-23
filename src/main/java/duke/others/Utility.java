package duke.others;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Other helpful methods used in other classes.
 */
public class Utility {
    /**
     * Checks if the string contains only numeric characters.
     *
     * @param s input string.
     * @return true if all characters are numeric.
     */
    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false)
                return false;
        }
        return true;
    }

    /**
     * Concatenate the task attributes into a single string. Attributes are delimited by ";".
     *
     * @param task task object
     * @return a concatenated string of the task attributes
     */
    public static String constructInput(Task task) {
        String taskType = task.getType();
        String input = "";
        if (taskType == "D") {
            input = taskType + ";" + task.getIsDone() + ";" + task.getDesc() + ";" + ((Deadline) task).getDate() +
                    ";" + task.getDoAfter() + ";" + task.getDoBefore();
        } else if (taskType == "E") {
            input = taskType + ";" + task.getIsDone() + ";" + task.getDesc() + ";" + ((Event) task).getDate() +
                    ";" + task.getDoAfter() + ";" + task.getDoBefore();
        } else {
            input = taskType + ";" + task.getIsDone() + ";" + task.getDesc() + ";" + ";" + task.getDoAfter() +
                    ";" + task.getDoBefore();
        }
        return input;
    }
}
