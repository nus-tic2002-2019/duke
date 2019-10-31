import java.util.Scanner;

public class Ui {
    private String userInput;

    public Ui(){
        userInput = "";
    }


    public String[] takeUi() {
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        String[] text = new Parser().parseUi(userInput);
        return text;
    }

    public void showWelcome() {
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

    }
}
