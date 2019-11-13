public class Utility {
    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false)
                return false;
        }
        return true;
    }

    public static String constructInput(Task task) {
        String taskType = task.getType();
        String input = "";
        if (taskType == "D") {
            input = taskType + ";" + task.getIsDone() + ";" + task.getDesc() + ";" + ((Deadline) task).getDate();
        }
        else if (taskType == "E") {
            input = taskType + ";" + task.getIsDone() + ";" + task.getDesc() + ";" + ((Event) task).getDate();
        }
        else {
            input = taskType + ";" + task.getIsDone() + ";" + task.getDesc() + ";";
        }
        return input;
    }
}
