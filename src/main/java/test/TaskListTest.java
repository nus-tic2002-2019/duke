package test;

import TaskList.TaskList;
import Tasks.toDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void getSizeNAddTask() {
        TaskList t = new TaskList();
        assertEquals(0, t.getSize());
        toDos u = new toDos("read Book");
        t.addTask(u);
        assertEquals(1, t.getSize());
    }

    @Test
    void getTask() {
        TaskList tk = new TaskList();
        toDos u = new toDos("Code Duke");
        tk.addTask(u);
        assertEquals(  "[T]" + "["+"\u2718"+"]"+ "Code Duke" ,tk.getTask(tk.getSize()-1).toString());
        tk.getTask(tk.getSize()-1).markAsDone();
        assertEquals("[T]" + "[" + "\u2713" + "]" + "Code Duke",tk.getTask(tk.getSize()-1).toString());
    }


}