package duke.tasklist;

import duke.exception.DukeException;

/**
 * This is the Priority level for individual task
 * There are 3 levels: HIGH, MEDIUM, LOW
 */
public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    private static Priority taskPriority;
    private static String printTaskPriority;
    /**
     * The priorityFromString method takes the String and convert to the equivalent Priority level
     * @param strTaskPriority is the Priority level (H, M or L) in String format
     * @return the equivalent Priority Level: H --> HIGH, M --> MEDIUM, L --> LOW
     */
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
    /**
     * The prioritytoString method takes the Priority object and convert to the equivalent String object
     * @param taskPriority is the Priority level (HIGH, MEDIUM or LOW)
     * @return the equivalent String object for the different priority level: HIGH --> H, MEDIUM --> M, LOW --> L
     */

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
            break;
        }
        return printTaskPriority;
    }

}
