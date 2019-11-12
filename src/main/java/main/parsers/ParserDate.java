package main.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FunctionalInterface
public interface ParserDate<T,V> {

abstract T convert(V input);

}