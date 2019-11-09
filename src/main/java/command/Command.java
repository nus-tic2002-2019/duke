package command;

public interface Command {
    default void run(String content) throws Exception {
    }
}
