package taskclasses;

import date.time.management.DateTime;

public class Event extends Task{

    /**
     * An constructor to construct an Event Task which is inherited from Task class
     * @param description description in String
     * @param Starting Starting time in DateTime type
     * @param Ending Ending time in DateTime type
     */
    public Event(String description, DateTime Starting, DateTime Ending){
        super(description, "E");
        this.Ending = Ending;
        this.Starting = Starting;
    }
}
