package UI;

import java.io.*;
import java.util.*;

public class Message {
    private Scanner in;
    private PrintStream out;

    public Message () {
        this (System.in, System.out);
    }

    public Message(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /*
    public String readCommand () {


    }
     */

    public void showGreetingMessage () {
        System.out.println("\tHey! Duke here, What can I do for you?");
    }

    public void showExitMessage () {
        System.out.println("\tBye. Hope to see you again soon!");
    }


}
