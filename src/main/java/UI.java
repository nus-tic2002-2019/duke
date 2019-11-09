import java.util.Scanner;
        import java.io.InputStream;

public class UI{
    private final Scanner in;

    public UI(){
        this(System.in);
    }

    public UI(InputStream in) {
        this.in = new Scanner(in);
    }
    public void showWelcomeMessage(){
        System.out.println(wrapString("Hello! I'm Duke\n\tWhat can I do for you?"));
    }

    public void showGoodbyeMessage(){
        System.out.println(wrapString("Bye. Hope to see you again soon!"));
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String wrapString(String string) {
        return "\t_______________\n\t" + string + "\n\t______________";
    }

    public String readUserInput() {
        String input = in.nextLine();
        while(shouldIgnore(input)) {
            input = in.nextLine();
        }
        return input;
    }

    public void showOutputToUser(String output) {
        System.out.println(wrapString(output));
    }

    public void showError(String errorMessage) {
        System.out.println(wrapString("OOPS!!! " + errorMessage));
    }
}