package main.Commands;


abstract public class Command<T> {

    abstract public void execute(T input);
}
