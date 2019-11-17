package main.taskLists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event<T> extends Task {

    private T At;

    public Event(String description, T At) {
        super(description);
        this.At = At;
    }

    public T getAt() {
        return this.At;
    }

    private String convert(LocalDate input) {
        return input.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));
    }

    @Override
    public String toString() {

        try {
            return "[E]" + "[" + this.getStatusIcon() + "] " + super.toString() + " (At: " + convert((LocalDate) At) + ")";
        } catch (Exception e) {
            return "[E]" + "[" + this.getStatusIcon() + "] " + super.toString() + " (At: " + this.At + ")";
        }
    }
}
