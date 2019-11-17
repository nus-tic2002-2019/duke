package main.commands;


import main.DukeException;

import java.io.IOException;

abstract public class Command<T> {

    abstract public void execute(T input) throws DukeException, IOException;
}
