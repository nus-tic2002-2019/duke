//level 7.more oop
/**
 *  Todo command for storing Data "Description"
 */

package task;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Todo extends task.Task {

    public Todo(String description) {
        super(description);
        //isDone = false;
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
      //return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }


}

