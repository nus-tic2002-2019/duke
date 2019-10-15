import java.util.Random;

public class DukeResponses extends Duke {

    private boolean alive;

    //Greeting Phrases for Duke (Level 1)
    private String[] Greetings = {
            "   Hi I'm Duke, a Basic Chat bot created for TIC2002",
            "   Hi, How are you?",
            "   Hello, I am Duke. What can I do for you?",
            "   Howdy, I am Duke, I can do lots of things!"
    };
    //Goodbye Phrases for Duke (Level 1)
    private String[] GoodByes = {
            "   GoodBye!",
            "   See you again!",
            "   Smell ya later!",
            "   Bye, Hope to See you again Soon!"
    };

    private String[] Operations = {
            "   Nice! I've marked this task as done: ",
            "   Here are the tasks in your list: ",
            "   Now you have @ tasks in the list."
    };

    public DukeResponses() {
        this.alive = true;
        Random counter = new Random();
        int phrase = counter.nextInt(Greetings.length);
        System.out.println(Greetings[phrase]);
        Generics.line();
    }

    public boolean isAlive() {
        return alive;
    }

    private void GoodBye() {
        this.alive = false;
        Random counter = new Random();
        int phrase = counter.nextInt(GoodByes.length);
        System.out.println("  "+ GoodByes[phrase]);
        Generics.line();
    }

    public void converse(String input) {

        // Intent Classification (Level 1)
        if (input.contains("Bye") || input.contains("bye")) {
            this.GoodBye();
            return;
        }

        // Intent Classifications
        if (input.contains("list") || input.contains("List")) {
            System.out.println(Operations[1]);
            printTasks();
        }
        else if (input.contains("done") || input.contains("Done")) {
            String nevin = input.replaceAll("\\D+","");
            Tasks.get(Integer.parseInt(nevin) - 1).SetAsDone();
            System.out.println(Operations[0]);
            System.out.println(Tasks.get(Integer.parseInt(nevin) - 1));
        }
        else if (input.contains("todo") || input.contains("Todo")) {
            // To be Implemented
            String nevin = input.replaceAll("todo", "");
            Todo a = new Todo(nevin);
            Tasks.add(a);
        }
        else {
            Task a = new Task(input);
            Tasks.add(a);
            System.out.println("   added: " + input);
        }

        Generics.line();
    }
}
