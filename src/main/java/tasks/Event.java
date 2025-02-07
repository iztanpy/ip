package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "E |" + super.toString() + " | " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
