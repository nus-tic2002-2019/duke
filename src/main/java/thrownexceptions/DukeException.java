package thrownexceptions;

public class DukeException extends Exception {
    private static String First_Word;

    public DukeException(String first_word) {
        First_Word = first_word;
    }

    public DukeException(){}

    public static String getFirst_Word(){
        return First_Word;
    }
}
