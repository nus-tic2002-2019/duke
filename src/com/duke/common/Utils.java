package com.duke.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Utils {


    /**
     * Returns true if every element the given collection are unique by {@link Object#equals(Object)}.
     */
    public static  boolean elementsAreUnique(Collection<?> items){
        final Set<Object> testSet=new HashSet<>();
        for (Object item :items){
            final boolean itemAlreadyExists=!testSet.add(item);// set is not allowed duplicate.
            if(itemAlreadyExists){
                return false;
            }
        }
        return  true;
    }
}
