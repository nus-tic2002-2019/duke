/**
 *  <h1>TIC2002 - Introduction to Software Engineering Project</h1>
 *  The project aims to build a product named Duke,
 *  a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author yralle.lesly.gimpaya
 * @version 0.1
 * @since 2019-09-11
 */

import java.util.Scanner;

public class Duke {

    private static void WelcomeBanner() {

        String logo = " _______   __    __   __  ___  _______ \n"
                + "|       \\ |  |  |  | |  |/  / |   ____|\n"
                + "|  .--.  ||  |  |  | |  '  /  |  |__   \n"
                + "|  |  |  ||  |  |  | |    <   |   __|\n"
                + "|  '--'  ||  `--'  | |  .  \\  |  |___\n"
                + "|_______/  \\______/  |__|\\__\\ |_______|\n";
        System.out.println(logo);
    }

    public static void line() {
        System.out.println("   ____________________________________________________________");
    }

    public static void main(String[] args) {
        WelcomeBanner();
        DukeBrain Duke = new DukeBrain();

        while(Duke.isAlive()) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            line();
            Duke.converse(input);

        }

    }
}
