package main.Commands;


import main.DukeException;

abstract public class Command<T> {

    abstract public void execute(T input) throws DukeException;
}
