/*
todo.java
define todo task.
*/
package Duke.tasks;

public class Todo extends Task {
    String t;
    /*
    constructor of todo object
    @param description todo task description
    */
    public Todo(String description)
    {
        super(description);
    }

    /*
    get todo status by return tick or x symbols.
    @return tick or x symbols
    */
    public String getStatusIcon() {

        return ("[T]" + super.getStatusIcon()); //return tick or X symbols
    }

    /*
    get todo description
    @return task description
    */
    public String getDescription() {

        return (super.getDescription());
    }

}
