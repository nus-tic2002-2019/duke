package duke.tasklist;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    private static Priority taskPriority;
    private static String printTaskPriority;

    public static Priority priorityFromString(String strTaskPriority){
        switch(strTaskPriority){
            case "L":
                taskPriority = Priority.LOW;
                break;
            case "M":
                taskPriority = Priority.MEDIUM;
                break;
            case "H":
                taskPriority = Priority.HIGH;
                break;
        }
        return taskPriority;
    }

    public static String PriorityToString(Priority taskPriority){

        switch(taskPriority){
            case LOW:
                printTaskPriority = "L";
                break;
            case MEDIUM:
                printTaskPriority = "M";
                break;
            case HIGH:
                printTaskPriority = "H";

        }
        return printTaskPriority;
    }

}
