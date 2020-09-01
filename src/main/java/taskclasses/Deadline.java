package taskclasses;

import date.time.management.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deadline extends Task {

    /**
     * Constructor use to constructs Deadline Task which extends Task class.
     * Has two different para for Task, description and type
     * Has one para for Deadline its own, localDateTime
     * @param description description in String
     * @param localDateTime LocalDateTime in DateTime type
     */
    public Deadline(String description, DateTime localDateTime){
        super(description, "D");
        this.Deadline_timing = localDateTime;
    }
}
