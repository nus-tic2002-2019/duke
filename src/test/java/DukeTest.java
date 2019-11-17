import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest extends Duke{

   /* public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> type = new ArrayList<>();
    public static ArrayList<Boolean> mark = new ArrayList<>();
    public static ArrayList<LocalDate> dateS = new ArrayList<>();*/

    @Test
    public void dummyTest() throws DukeException {

        Duke obj = new Duke();
        Duke.list.add("return book");
        Duke.type.add("T");
        Duke.mark.add(false);
        Duke.dateS.add(LocalDate.parse("2019-12-01"));

        assertEquals("X", Duke.getMark(mark.get(0)));
       // assertEquals("✓", Duke.getMark(true));

        assertEquals("T", Duke.getType("todo"));
        assertEquals("D", Duke.getType("deadline"));
        assertEquals("E", Duke.getType("event"));

        /*LocalDate today = LocalDate.now();
        String org = "T|false|return book|" + today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals("hhjgyuggy", Duke.save());*/
    }

}