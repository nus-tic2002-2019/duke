package TaskList;

import org.junit.jupiter.api.Test;
import task.Todo;
import tasklist.TaskList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {


    @Test
    public void testTasklist(){
        TaskList t = new TaskList();
        assertEquals(0, t.getSize());
        toDos u = new toDos("read Book");
        t.addTask(u);
        assertEquals(1, t.getSize());
    }


    @Test
    public void testMarkDone(){
        TaskList tk = new TaskList();
        toDos u = new toDos("Code Duke");
        tk.addTask(u);
        assertEquals(t.getTask(tk.getSize()-1).toString());
        tk.getTask(tk.getSize()-1).markAsDone();
        assertEquals(t.getTask(tk.getSize()-1).toString());

    }

    @Test
    public void parseToFileTest(){

    }

    /**
     * Parsing text file into Task
     *
     * @param command stored individual command from the text file
     * @return Task
     */
    public Task parsingTxtFile(String command) throws ParseException {
        String[] tmp = command.split("\\| ");
        String[] words = command.split("\\|");

        Task result = new Task();
        switch(words[0]){
            case "T" :

                result = new toDos(words[2]);
                //     System.out.println("Get here 1 ? " + words[1]);
                String a = "1";

                if(Integer.parseInt(words[1].trim()) == 1){
                    tasklist.add(result);
                    tasklist.get(tasklist.size()-1).markAsDone();
                }else {
                    tasklist.add(result);
                }
                break;
            case "D" :

                SimpleDateFormat frmt = new SimpleDateFormat("E, dd MMM yyyy HH:mm");
                Date dte = frmt.parse(words[3].trim());
                result = new Deadlines(words[2], dte);
                if(Integer.parseInt(words[1].trim()) == 1){
                    tasklist.add(result);
                    tasklist.get(tasklist.size()-1).markAsDone();
                }else {
                    tasklist.add(result);
                }

                break;
            case "E" :
                result = new Events(words[2], words[3]);
                if(Integer.parseInt(words[1].trim()) == 1){

                    tasklist.add(result);

                    tasklist.get(tasklist.size()-1).markAsDone();
                }else {
                    tasklist.add(result);
                }

                break;
            default:
                break;
        }

        return result;
    }
}
