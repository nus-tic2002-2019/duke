package main.parsers;

@FunctionalInterface
public interface UtilityFunc<T, V> {

    abstract T convert(V input);

}