package tic2002.task;

/**
 * Represents Todo sub-class, inherited from Task class.
 */
public class Todo extends Task {
    //Constructor
    public Todo(String taskDescription) {
        super(taskDescription);
        typeIdt = CHAR_TODO;
    }
}