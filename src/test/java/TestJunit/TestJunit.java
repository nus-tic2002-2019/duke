package TestJunit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import exception.DukeException;
import org.junit.Test;
import task.Deadline;
import task.Event;
import task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJunit {
    @Test
    public void ToDo() throws DukeException {

        ToDo todo1 = new ToDo("homework");
        ToDo todo2 = new ToDo("assignment");
        ToDo todo3 = new ToDo("exam");

        assertEquals("[T][✘] homework", todo1.list());
        assertEquals("[T][✘] assignment", todo2.list());
        assertEquals("[T][✘] exam", todo3.list());
    }
    @Test
    public void Deadline() throws DukeException, ParseException {
        String sDate1 = "19-10-2019 14:30:00";
        String sDate2 = "19-11-2019 15:30:00";
        String sDate3 = "19-12-2019 16:30:00";
        Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDate1);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDate2);
        Date date3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDate3);
        Deadline deadline1 = new Deadline("homework ", date1);
        Deadline deadline2 = new Deadline("assignment ", date2);
        Deadline deadline3 = new Deadline("exam ", date3);

        assertEquals("[D][✘] homework do by: Sat Oct 19 14:30:00 SGT 2019", deadline1.list());
        assertEquals("[D][✘] assignment do by: Tue Nov 19 15:30:00 SGT 2019", deadline2.list());
        assertEquals("[D][✘] exam do by: Thu Dec 19 16:30:00 SGT 2019", deadline3.list());

    }

    @Test
    public void Event() throws DukeException, ParseException {
        String sDate1 = "19-10-2019 14:30:00";
        String sDate2 = "19-11-2019 15:30:00";
        String sDate3 = "19-12-2019 16:30:00";
        Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDate1);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDate2);
        Date date3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDate3);
        Event event1 = new Event("concert ", date1);
        Event event2 = new Event("meeting ", date2);
        Event event3 = new Event("movie ", date3);

        assertEquals("[E][✘] concert at: Sat Oct 19 14:30:00 SGT 2019", event1.list());
        assertEquals("[E][✘] meeting at: Tue Nov 19 15:30:00 SGT 2019", event2.list());
        assertEquals("[E][✘] movie at: Thu Dec 19 16:30:00 SGT 2019", event3.list());

    }
}
