package main.parsers;


/**
 *  This is a functional interface and therefore be used as
 *  the assignment target for a lamda expression or method reference.
 *
 * @param <T> - the Type of the result to the function
 * @param <V> - the Type of the input of the function
 */
@FunctionalInterface
public interface UtilityFunc<T, V> {

    /**
     *  Applies this function to the given argument
     *
     * @param
     * Input is the function argument to be applied on
     *
     * @return
     * is the function result
     */
    abstract T convert(V input);

}